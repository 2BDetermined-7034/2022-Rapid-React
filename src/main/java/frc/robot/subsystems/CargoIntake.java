package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class CargoIntake extends SubsystemBase {
    private final CANSparkMax cargoMotor;
    private final CANSparkMax cargoMotor2;
    private final DoubleSolenoid m_solenoid;
    private Double m_speed;

    public CargoIntake() {
        this.m_solenoid = new DoubleSolenoid(Constants.pneumatics.intake, Constants.intake.solenoidForward, Constants.intake.solenoidReverse);
        this.cargoMotor = new CANSparkMax(Constants.intake.intakeMotor, CANSparkMaxLowLevel.MotorType.kBrushless);
        this.cargoMotor2 = new CANSparkMax(Constants.intake.intakeMotor2, CANSparkMaxLowLevel.MotorType.kBrushless);
        m_speed = 0.0;
    }
    public void periodic() {
        cargoMotor.set(m_speed);
        cargoMotor2.set(-m_speed);
    }
    /**
     * @param speed The speed you want to move the motor at.
     */
    public void setSpeed(double speed) {
        this.m_speed = speed;
    }

    /**
     * @param thing Boolean - either true (out) or false (in).
     */
    public void setSolenoid(boolean thing) {
        if (thing) {
            m_solenoid.set(DoubleSolenoid.Value.kReverse);
        } else {
            m_solenoid.set(DoubleSolenoid.Value.kForward);
        }
    }
}