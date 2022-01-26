package frc.robot.subsystems.intake;


import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class CargoIntake extends SubsystemBase {
    private final WPI_TalonSRX cargoMotor;
    private final Solenoid m_solenoid;


    public CargoIntake() {
        SmartDashboard.putNumber("Intake Speed", 0.5);
        this.m_solenoid = new Solenoid(Constants.intake.solenoid);
        this.cargoMotor = new WPI_TalonSRX(Constants.intake.intakeTalon);
    }

    public void mmmRunMotor(double speed) {
        cargoMotor.set(speed);
    }

    public void setSolenoid(boolean thing) {
        m_solenoid.set(thing);
    }


}