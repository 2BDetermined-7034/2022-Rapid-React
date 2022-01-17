package frc.robot.subsystems.drive;


import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drive extends SubsystemBase {

    private final CANSparkMax m_left, m_left2, m_left3, m_right, m_right2,  m_right3;
    private final RelativeEncoder m_leftEnc, m_rightEnc;

    private final Solenoid m_shifter;

    private final AHRS m_gyro;

    public Drive() {
        m_left = new CANSparkMax(Constants.driveBase.driveL1ID, CANSparkMaxLowLevel.MotorType.kBrushless);
        m_left2 = new CANSparkMax(Constants.driveBase.driveL2ID, CANSparkMaxLowLevel.MotorType.kBrushless);
        m_left3 = new CANSparkMax(Constants.driveBase.driveL3ID, CANSparkMaxLowLevel.MotorType.kBrushless);
        m_right = new CANSparkMax(Constants.driveBase.driveR1ID, CANSparkMaxLowLevel.MotorType.kBrushless);
        m_right2 = new CANSparkMax(Constants.driveBase.driveR2ID, CANSparkMaxLowLevel.MotorType.kBrushless);
        m_right3 = new CANSparkMax(Constants.driveBase.driveR3ID, CANSparkMaxLowLevel.MotorType.kBrushless);

        m_leftEnc = m_left.getEncoder();
        m_rightEnc = m_right.getEncoder();

        m_gyro = new AHRS(SPI.Port.kMXP);
        m_shifter = new Solenoid(Constants.driveBase.shifterID);
    }

    public void set(){

    }

    /**
     * Method to move the drivebase to a setpoint
     * @param x distance away perpendicular to the robot's starting pos (meters)
     * @param y distance away parallel to the robot's starting pos (meters)
     */
    public void driveToPoint(double x, double y){

    }

    /**
     * Method to get the heading of the drivebase
     * @return heading 0-360 (in degrees)
     */
    public double getHeading(){
        return m_gyro.getYaw();
    }


    @Override
    public void periodic() {

    }
}

