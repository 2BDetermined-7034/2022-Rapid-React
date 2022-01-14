package frc.robot.subsystems.prototypes;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CargoIntake extends SubsystemBase {
private final WPI_TalonSRX cargoMotor;


    public CargoIntake() {
        cargoMotor = new WPI_TalonSRX(Constants.cargoIntakeMotorID1);
    }

    public void mmmRunMotor(double speed) {
        cargoMotor.set(speed);
    }

}

