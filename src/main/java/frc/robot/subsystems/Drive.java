package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.*;

import edu.wpi.first.math.MatBuilder;
import edu.wpi.first.math.Nat;
import edu.wpi.first.math.estimator.DifferentialDrivePoseEstimator;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drive extends SubsystemBase {

    private final CANSparkMax m_left, m_left2, m_left3, m_right, m_right2,  m_right3;
    private final RelativeEncoder m_leftEnc, m_rightEnc;
    private final SparkMaxPIDController m_leftPID, m_rightPID;

    private final Solenoid m_shifter;

    private final AHRS m_gyro;

    private final DifferentialDrive m_differentialDrive;

    private DifferentialDrivePoseEstimator m_locationManager;
    DifferentialDriveKinematics m_kinematics;

    public Drive() {
        m_left = new CANSparkMax(Constants.driveBase.driveL1ID, CANSparkMaxLowLevel.MotorType.kBrushless);
        m_left2 = new CANSparkMax(Constants.driveBase.driveL2ID, CANSparkMaxLowLevel.MotorType.kBrushless);
        m_left3 = new CANSparkMax(Constants.driveBase.driveL3ID, CANSparkMaxLowLevel.MotorType.kBrushless);

        m_left.setInverted(true);
        m_left2.setInverted(true);
        m_left3.setInverted(true);

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

        m_leftPID.setFF(Constants.driveBase.leftFF);
        m_leftPID.setP(Constants.driveBase.leftP);
        m_leftPID.setI(Constants.driveBase.leftI);
        m_leftPID.setD(Constants.driveBase.leftD);

        m_rightPID.setFF(Constants.driveBase.rightFF);
        m_rightPID.setP(Constants.driveBase.rightP);
        m_rightPID.setI(Constants.driveBase.rightI);
        m_rightPID.setD(Constants.driveBase.rightD);

        m_gyro = new AHRS(SPI.Port.kMXP);
        m_shifter = new Solenoid(Constants.pneumatics.shifter, Constants.driveBase.shifterID);

        shift(Constants.driveBase.LOW_GEAR);

        m_locationManager = new DifferentialDrivePoseEstimator(Rotation2d.fromDegrees(-m_gyro.getYaw()), new Pose2d(),
                new MatBuilder<>(Nat.N5(), Nat.N1()).fill(0.02, 0.02, 0.01, 0.02, 0.02), // State measurement standard deviations. X, Y, theta.
                new MatBuilder<>(Nat.N3(), Nat.N1()).fill(0.02, 0.02, 0.01), // Local measurement standard deviations. Left encoder, right encoder, gyro.
                new MatBuilder<>(Nat.N3(), Nat.N1()).fill(0.1, 0.1, 0.01) // Global measurement standard deviations. X, Y, and theta.
        );
    }

    /**
     * Method to get robot position using odometry
     * @return the position of the robot in a pose2d
     */
    public Pose2d getRobotPos(){
        return m_locationManager.getEstimatedPosition();
    }

    /**
     * Method to get the wheel speeds of the robot, averaged
     * @return wheel speed object
     */
    public DifferentialDriveWheelSpeeds getWheelVelocity(){
        return new DifferentialDriveWheelSpeeds(m_leftEnc.getVelocity(), m_rightEnc.getVelocity());
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

    /**
     * A simple function that returns the NavX value scoped
     * @return NavX in range 0-360 (degrees)
     */
    public double getCurrentAngle(){
        return m_gyro.getYaw();
    }

    /**
     * Change the pose of the robot, this should be called at the start of every path
     * @param startingPose the robot's position
     */
    public void setRobotPos(Pose2d startingPose){
        m_locationManager.resetPosition(startingPose, Rotation2d.fromDegrees(-m_gyro.getYaw()));
    }

    /**
     * Set desired encoder position and use a PID loop to get there
     * @param position (meters?)
     */
    public void setPosition(double position){
        m_rightPID.setReference(position, CANSparkMax.ControlType.kPosition);
        m_leftPID.setReference(position, CANSparkMax.ControlType.kPosition);
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
            m_leftEnc.setPositionConversionFactor(Constants.driveBase.highRatio * Constants.driveBase.wheelMeterRatio);
            m_rightEnc.setPositionConversionFactor(Constants.driveBase.highRatio * Constants.driveBase.wheelMeterRatio);
            m_leftEnc.setVelocityConversionFactor(Constants.driveBase.highRatio * Constants.driveBase.wheelMeterRatio);
            m_rightEnc.setVelocityConversionFactor(Constants.driveBase.highRatio * Constants.driveBase.wheelMeterRatio);

        } else {
            m_leftEnc.setPositionConversionFactor(Constants.driveBase.lowRatio * Constants.driveBase.wheelMeterRatio);
            m_rightEnc.setPositionConversionFactor(Constants.driveBase.lowRatio * Constants.driveBase.wheelMeterRatio);

            m_leftEnc.setVelocityConversionFactor(Constants.driveBase.lowRatio * Constants.driveBase.wheelMeterRatio);
            m_rightEnc.setVelocityConversionFactor(Constants.driveBase.lowRatio * Constants.driveBase.wheelMeterRatio);
        }
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

    public void setVelocity(double velocity){
        SmartDashboard.putNumber("Vel", velocity);
        m_rightPID.setReference(velocity, CANSparkMax.ControlType.kVelocity);
        m_leftPID.setReference(velocity, CANSparkMax.ControlType.kVelocity);
    }

    /**
     * Kinematic utility motion drive method
     * @param chassisSpeeds the chassis speed of drive
     */
    public void kumDrive(ChassisSpeeds chassisSpeeds){
        DifferentialDriveWheelSpeeds wheelSpeeds = m_kinematics.toWheelSpeeds(chassisSpeeds);
        double leftVelocity = wheelSpeeds.leftMetersPerSecond;
        double rightVelocity = wheelSpeeds.rightMetersPerSecond;

        m_left.set(leftVelocity);
        m_right.set(rightVelocity);
    }

    /**
     * Simple function to print drive values
     */
    public void debug(){
        SmartDashboard.putNumber("NavX", getCurrentAngle());
        SmartDashboard.putNumber("Right Encoder", getRightEncoderPosition());
        SmartDashboard.putNumber("Left Encoder", getLeftEncoderPosition());
        SmartDashboard.putNumber("Odometry X", m_locationManager.getEstimatedPosition().getX());
        SmartDashboard.putNumber("Odometry Y", m_locationManager.getEstimatedPosition().getY());
        SmartDashboard.putNumber("Right Velocity", getWheelVelocity().rightMetersPerSecond);
        SmartDashboard.putNumber("Left Velocity", getWheelVelocity().leftMetersPerSecond);
    }

    /**
     * Set drive speeds to zero
     */
    public void stop(){
        m_differentialDrive.arcadeDrive(0, 0);
    }

    @Override
    public void periodic() {
        m_locationManager.update(Rotation2d.fromDegrees(-m_gyro.getYaw()), getWheelVelocity(), m_leftEnc.getPosition(), m_rightEnc.getPosition());
        debug();
    }
}
