package frc.robot.subsystems.drive;


import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.*;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drive extends SubsystemBase {

    private final CANSparkMax m_left, m_left2, m_left3, m_right, m_right2,  m_right3;
    private final CANEncoder m_leftEnc, m_rightEnc;
    private final CANPIDController m_leftPID, m_rightPID;

    private final Solenoid m_shifter;

    private final AHRS m_gyro;

    private final DifferentialDrive m_differentialDrive;

    private final DifferentialDriveOdometry m_encoderOdometry;
    DifferentialDriveKinematics m_kinematics;

    public Drive(double startX, double startY) {
        m_left = new CANSparkMax(Constants.driveBase.driveL1ID, CANSparkMaxLowLevel.MotorType.kBrushless);
        m_left2 = new CANSparkMax(Constants.driveBase.driveL2ID, CANSparkMaxLowLevel.MotorType.kBrushless);
        m_left3 = new CANSparkMax(Constants.driveBase.driveL3ID, CANSparkMaxLowLevel.MotorType.kBrushless);
        m_right = new CANSparkMax(Constants.driveBase.driveR1ID, CANSparkMaxLowLevel.MotorType.kBrushless);
        m_right2 = new CANSparkMax(Constants.driveBase.driveR2ID, CANSparkMaxLowLevel.MotorType.kBrushless);
        m_right3 = new CANSparkMax(Constants.driveBase.driveR3ID, CANSparkMaxLowLevel.MotorType.kBrushless);

        m_left2.follow(m_left);
        m_left3.follow(m_left);

        m_right2.follow(m_right);
        m_right3.follow(m_right);

        m_differentialDrive = new DifferentialDrive(m_left, m_right);
        m_kinematics = new DifferentialDriveKinematics(Constants.driveBase.width);

        m_right.setIdleMode(CANSparkMax.IdleMode.kBrake);
        m_right2.setIdleMode(CANSparkMax.IdleMode.kBrake);
        m_right3.setIdleMode(CANSparkMax.IdleMode.kBrake);

        m_left.setIdleMode(CANSparkMax.IdleMode.kBrake);
        m_left2.setIdleMode(CANSparkMax.IdleMode.kBrake);
        m_left3.setIdleMode(CANSparkMax.IdleMode.kBrake);

        m_leftEnc = m_left.getEncoder();
        m_rightEnc = m_right.getEncoder();

        m_leftPID = m_left.getPIDController();
        m_rightPID = m_right.getPIDController();
        m_leftPID.setP(0.1);
        m_rightPID.setP(0.1);

        m_gyro = new AHRS(SPI.Port.kMXP);
        m_shifter = new Solenoid(Constants.driveBase.shifterID);

        shift(Constants.driveBase.LOW_GEAR);

        m_encoderOdometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(-m_gyro.getYaw()), new Pose2d(startX, startY, new Rotation2d()));
    }

    /**
     * Stolen then modified from WPILib
     * @param xSpeed forward/backwards speed in range -1 to 1
     * @param zRotation sideways rotation in speed -1 to 1
     */
    public void arcadeDrive(double xSpeed, double zRotation){
        xSpeed *= Constants.driveBase.xSpeed;
        zRotation *= Constants.driveBase.xRot;
        m_differentialDrive.arcadeDrive(xSpeed, zRotation);
    }

    public void kinoDrive(ChassisSpeeds chassisSpeeds){
        DifferentialDriveWheelSpeeds wheelSpeeds = m_kinematics.toWheelSpeeds(chassisSpeeds);
        double leftVelocity = wheelSpeeds.leftMetersPerSecond;
        double rightVelocity = wheelSpeeds.rightMetersPerSecond;
        m_differentialDrive.tankDrive(leftVelocity, rightVelocity);
    }

    public void setPosition(double position){
        m_rightPID.setReference(position, ControlType.kPosition);
        m_leftPID.setReference(position, ControlType.kPosition);
    }
    /**
     * A simple function that returns the NavX value scoped
     * @return NavX in range -180 to 180
     */
    public double getCurrentAngle(){
        double ang = m_gyro.getYaw();
        if (ang > 180) ang -= 360;
        //flip yaw
        ang *= -1;

        return ang;
    }

    /**
     * Simple function to print drive values
     */
    public void debug(){
        SmartDashboard.putNumber("NavX", getCurrentAngle());
        SmartDashboard.putNumber("Right Encoder", getRightEncoderPosition());
        SmartDashboard.putNumber("Left Encoder", getLeftEncoderPosition());
        SmartDashboard.putNumber("Odometry X", m_encoderOdometry.getPoseMeters().getX());
        SmartDashboard.putNumber("Odometry Y", m_encoderOdometry.getPoseMeters().getY());
    }

    /**
     * Method to set gear on the shifter
     * @param gear high is false, low is true
     */
    public void shift(boolean gear){
        m_shifter.set(gear);
        setEncoderRatio(gear);
    }

    /**
     * Sets encoder ratios based on gear
     * @param gear high is false, low is true
     */
    public void setEncoderRatio(boolean gear){
        if (gear == Constants.driveBase.HIGH_GEAR){
            m_leftEnc.setPositionConversionFactor(Constants.driveBase.highRatio);
            m_rightEnc.setPositionConversionFactor(Constants.driveBase.highRatio);
        } else {
            m_leftEnc.setPositionConversionFactor(Constants.driveBase.lowRatio);
            m_rightEnc.setPositionConversionFactor(Constants.driveBase.lowRatio);
        }
    }

    /**
     * Method to get robot position
     * @return the position of the robot in a pose2d
     */
    public Pose2d getRobotPos(){
        return m_encoderOdometry.getPoseMeters();
    }

    /**
     * Method to get right encoder position
     * @return distance (meters?)
     */
    public double getRightEncoderPosition(){
        return m_rightEnc.getPosition();
    }
    /**
     * Method to get right encoder position
     * @return distance (meters?)
     */
    public double getLeftEncoderPosition(){
        return m_leftEnc.getPosition();
    }


    @Override
    public void periodic() {
        m_encoderOdometry.update(Rotation2d.fromDegrees(-m_gyro.getYaw()), m_leftEnc.getPosition(), m_rightEnc.getPosition());
        debug();
    }
}
