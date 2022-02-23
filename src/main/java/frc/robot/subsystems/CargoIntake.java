package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class CargoIntake extends SubsystemBase {
    private final CANSparkMax cargoMotor;
    private final DoubleSolenoid m_solenoid;
    private Double m_speed;

    public CargoIntake() {
        this.m_solenoid = new DoubleSolenoid(Constants.pneumatics.intake, Constants.intake.solenoidForward, Constants.intake.solenoidReverse);
        this.cargoMotor = new CANSparkMax(Constants.intake.intakeMotor, CANSparkMaxLowLevel.MotorType.kBrushless);
        m_speed = 0.0;
    }

    /**
     * @param speed The speed you want to move the motor at.
     */
    public void setSpeed(double speed) {
        cargoMotor.set(speed);
    }

    /**
     * @param thing Boolean - either true (out) or false (in).
     */
    public void setSolenoid(boolean thing) {
        if (thing) {
            m_solenoid.set(DoubleSolenoid.Value.kForward);
        } else {
            m_solenoid.set(DoubleSolenoid.Value.kReverse);
        }
    }
}