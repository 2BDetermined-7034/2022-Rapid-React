package frc.robot.subsystems;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class CargoIntake extends SubsystemBase {
    private final WPI_TalonSRX cargoMotor;


    public CargoIntake() {
        SmartDashboard.putNumber("Intake Speed", .5);
        cargoMotor = new WPI_TalonSRX(Constants.intake.intakeTalon);
    }

    public void mmmRunMotor(double speed) {
        cargoMotor.set(speed);
    }


}