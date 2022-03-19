package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.*;

import edu.wpi.first.math.MatBuilder;
import edu.wpi.first.math.Nat;
import edu.wpi.first.math.estimator.DifferentialDrivePoseEstimator;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SPI;
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

    private final DoubleSolenoid m_shifter;
    private final AHRS m_gyro;

    private final DifferentialDrive m_differentialDrive;

    //private final DifferentialDrivePoseEstimator m_locationManager;
    private final DifferentialDriveOdometry m_odometry;
    DifferentialDriveKinematics m_kinematics;

    private double leftSpeed;
    private double rightSpeed;

    private double leftOffset;
    private double rightOffset;

    public Drive() {

        m_left = new CANSparkMax(Constants.driveBase.driveL1ID, CANSparkMaxLowLevel.MotorType.kBrushless);
        m_left2 = new CANSparkMax(Constants.driveBase.driveL2ID, CANSparkMaxLowLevel.MotorType.kBrushless);
        m_left3 = new CANSparkMax(Constants.driveBase.driveL3ID, CANSparkMaxLowLevel.MotorType.kBrushless);

        m_right = new CANSparkMax(Constants.driveBase.driveR1ID, CANSparkMaxLowLevel.MotorType.kBrushless);
        m_right2 = new CANSparkMax(Constants.driveBase.driveR2ID, CANSparkMaxLowLevel.MotorType.kBrushless);
        m_right3 = new CANSparkMax(Constants.driveBase.driveR3ID, CANSparkMaxLowLevel.MotorType.kBrushless);

        m_left.setInverted(false);
        m_left2.setInverted(false);
        m_left3.setInverted(false);

        m_right.setInverted(true);
        m_right2.setInverted(true);
        m_right3.setInverted(true);

        m_left2.follow(m_left);
        m_left3.follow(m_left);

        m_right2.follow(m_right);
        m_right3.follow(m_right);

        m_differentialDrive = new DifferentialDrive(m_left, m_right);
        m_kinematics = new DifferentialDriveKinematics(Constants.driveBase.width);

        m_right.setIdleMode(CANSparkMax.IdleMode.kCoast);
        m_right2.setIdleMode(CANSparkMax.IdleMode.kBrake);
        m_right3.setIdleMode(CANSparkMax.IdleMode.kCoast);

        m_left.setIdleMode(CANSparkMax.IdleMode.kCoast);
        m_left2.setIdleMode(CANSparkMax.IdleMode.kBrake);
        m_left3.setIdleMode(CANSparkMax.IdleMode.kCoast);

        m_leftEnc = m_left.getEncoder();
        m_rightEnc = m_right.getEncoder();

        m_gyro = new AHRS(SPI.Port.kMXP);
        m_shifter = new DoubleSolenoid(Constants.pneumatics.shifter, Constants.driveBase.rightShifterID, Constants.driveBase.leftShifterID);

        /*
        m_locationManager = new DifferentialDrivePoseEstimator(Rotation2d.fromDegrees(-m_gyro.getYaw()), new Pose2d(),
                new MatBuilder<>(Nat.N5(), Nat.N1()).fill(0.02, 0.02, 0.01, 0.02, 0.02), // State measurement standard deviations. X, Y, theta.
                new MatBuilder<>(Nat.N3(), Nat.N1()).fill(0.02, 0.02, 0.01), // Local measurement standard deviations. Left encoder, right encoder, gyro.
                new MatBuilder<>(Nat.N3(), Nat.N1()).fill(0.1, 0.1, 0.01) // Global measurement standard deviations. X, Y, and theta.
        );
        */
        resetEncoders();
        m_odometry = new DifferentialDriveOdometry(getCurrentAngle());
        m_gyro.reset();
    }

    /**
     * Method to get robot position using odometry
     * @return the position of the robot in a pose2d
     */
    public Pose2d getRobotPos(){
        //return m_locationManager.getEstimatedPosition();
        return m_odometry.getPoseMeters();
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
        return m_rightEnc.getPosition() - rightOffset;
    }

    /**
     * Method to get right encoder position
     * @return distance (meters?)
     */
    public double getLeftEncoderPosition(){
        return m_leftEnc.getPosition() - leftOffset;
    }

    public Rotation2d getCurrentAngle(){
        return Rotation2d.fromDegrees(m_gyro.getYaw() + Constants.driveBase.gyroOffset);
    }

    public void resetEncoders() {
        rightOffset = m_rightEnc.getPosition();
        leftOffset = m_leftEnc.getPosition();
    }
    /**
     * Change the pose of the robot, this should be called at the start of every path
     * @param startingPose the robot's position
     */
    public void setRobotPos(Pose2d startingPose){
        resetEncoders();
        m_odometry.resetPosition(startingPose, getCurrentAngle());
        //m_locationManager.resetPosition(startingPose, Rotation2d.fromDegrees(-m_gyro.getYaw()));
    }

    public DifferentialDriveKinematics get_kinematics() {
        return m_kinematics;
    }


    /**
     * Method to set gear on the shifter
     * @param gear high is false, low is true
     */
    public void shift(boolean gear){
        if (gear) {
            SmartDashboard.putString("Gear", "High");
            m_shifter.set(DoubleSolenoid.Value.kForward);
        } else {
            SmartDashboard.putString("Gear", "High");
            m_shifter.set(DoubleSolenoid.Value.kReverse);
        }
        setEncoderRatio(gear);
    }

    /**
     * Sets encoder ratios based on gear
     * @param gear high is false, low is true
     */
    public void setEncoderRatio(boolean gear){
        if (gear == Constants.driveBase.HIGH_GEAR){
            SmartDashboard.putString("Gear", "High");
            m_leftEnc.setPositionConversionFactor(Constants.driveBase.highRatio * Constants.driveBase.wheelRatio);
            m_rightEnc.setPositionConversionFactor(Constants.driveBase.highRatio * Constants.driveBase.wheelRatio);

            m_leftEnc.setVelocityConversionFactor(Constants.driveBase.highRatio * Constants.driveBase.wheelRatio);
            m_rightEnc.setVelocityConversionFactor(Constants.driveBase.highRatio * Constants.driveBase.wheelRatio);
        } else {
            SmartDashboard.putString("Gear", "Low");
            m_leftEnc.setPositionConversionFactor(Constants.driveBase.lowRatio * Constants.driveBase.wheelRatio);
            m_rightEnc.setPositionConversionFactor(Constants.driveBase.lowRatio * Constants.driveBase.wheelRatio);

            m_leftEnc.setVelocityConversionFactor(Constants.driveBase.lowRatio * Constants.driveBase.wheelRatio);
            m_rightEnc.setVelocityConversionFactor(Constants.driveBase.lowRatio * Constants.driveBase.wheelRatio);
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
        m_differentialDrive.arcadeDrive(-xSpeed, -zRotation);
    }

    /**
     * Kinematic utility motion drive method
     * @param chassisSpeeds the chassis speed of drive
     */
    public void voltageDrive(ChassisSpeeds chassisSpeeds){
        DifferentialDriveWheelSpeeds wheelSpeeds = m_kinematics.toWheelSpeeds(chassisSpeeds);
        m_left.setVoltage(wheelSpeeds.leftMetersPerSecond);
        m_right.setVoltage(wheelSpeeds.rightMetersPerSecond);
        m_differentialDrive.feed();
    }

    /**
     * Controls the left and right sides of the drive directly with voltages.
     *
     * @param leftVolts the commanded left output
     * @param rightVolts the commanded right output
     */
    public void tankDriveVolts(double leftVolts, double rightVolts) {
        SmartDashboard.putNumber("Left", -leftVolts);
        SmartDashboard.putNumber("Right", -rightVolts);
        m_left.setVoltage(leftVolts);
        m_right.setVoltage(rightVolts);
        m_differentialDrive.feed();
    }

    public void resetNavx(){
        m_gyro.reset();
    }


    /**
     * Simple function to print drive values
     */
    public void debug(){
        SmartDashboard.putNumber("NavX", getCurrentAngle().getDegrees());
        SmartDashboard.putNumber("Right Encoder", getRightEncoderPosition());
        SmartDashboard.putNumber("Left Encoder", getLeftEncoderPosition());
        SmartDashboard.putNumber("Odometry X pos", getRobotPos().getX());
        SmartDashboard.putNumber("Odometry Y pos", getRobotPos().getY());
        SmartDashboard.putNumber("Right Velocity", getWheelVelocity().rightMetersPerSecond);
        SmartDashboard.putNumber("Left Velocity", getWheelVelocity().leftMetersPerSecond);
    }

    /**
     * Set drive speeds to zero
     */
    public void stop(){
        m_right.setVoltage(0);
        m_left.setVoltage(0);
    }

    @Override
    public void periodic() {
        m_odometry.update(getCurrentAngle(), getLeftEncoderPosition(), getRightEncoderPosition());
        //m_locationManager.update(Rotation2d.fromDegrees(-m_gyro.getYaw()), getWheelVelocity(), m_leftEnc.getPosition(), m_rightEnc.getPosition());
        debug();
    }
}
