package frc.robot.subsystems.drive;


import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drive extends SubsystemBase {

    private final CANSparkMax m_left;
    private final CANSparkMax m_left2;
    private final CANSparkMax m_left3;
    private final CANSparkMax m_right;
    private final CANSparkMax m_right2;
    private final CANSparkMax m_right3;
    private final RelativeEncoder m_leftEnc;
    private final RelativeEncoder m_rightEnc;

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

    public void driveToPoint(double x, double y){

    }

    public double getHeading(){
        return m_gyro.getYaw();
    }


    @Override
    public void periodic() {

    }
}

