package frc.robot.subsystems.intake;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Solenoid;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import java.util.function.BooleanSupplier;

public class CargoIntake extends SubsystemBase {
    private final WPI_TalonSRX cargoMotor;
    private final Solenoid breake;

    public CargoIntake() {
        breake = new Solenoid(Constants.intake.solenoid);
        cargoMotor = new WPI_TalonSRX(Constants.intake.intakeTalon);
    }

    public void setBrake(Boolean thing) {
        breake.set(thing);
    }

    public void mmmRunMotor(double speed) {
        cargoMotor.set(speed);
    }

}
