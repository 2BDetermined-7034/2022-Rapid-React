package frc.robot.subsystems;


import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class CargoIntake extends SubsystemBase {
    private final WPI_TalonSRX cargoMotor;
    private final DoubleSolenoid m_solenoid;


    public CargoIntake() {
        SmartDashboard.putNumber("Intake Speed", 0.5);
        this.m_solenoid = new DoubleSolenoid(Constants.pneumatics.intake, Constants.intake.solenoidForward, Constants.intake.solenoidReverse);
        this.cargoMotor = new WPI_TalonSRX(Constants.intake.intakeTalon);
    }

    /**
     * @param speed The speed you want to move the motor at.
     */
    public void mmmRunMotor(double speed) {
        cargoMotor.set(speed);
    }

    /**
     * @param thing Boolean - either true (out) or false (in).
     */
    public void setSolenoid(boolean thing) {
        SmartDashboard.putBoolean("Intake Down", thing);
        if (thing) {
            m_solenoid.set(Value.kForward);
        } else {
            m_solenoid.set(Value.kReverse);
        }
    }
}