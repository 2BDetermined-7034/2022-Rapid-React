// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Shooter extends SubsystemBase {
  /** Creates a new Shooter subsystem */

    public WPI_TalonSRX Motor;

    public Shooter() {
        Motor = new WPI_TalonSRX(Constants.shooter.shooterTalonID);
        Motor.setNeutralMode(NeutralMode.Brake);
    }

  @Override
  public void periodic() {
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  /**
   * Method for command
   * @param speed to be taken from Vision
   */
  public void goBrr(double speed) {
      Motor.set(speed * Constants.shooter.shooterMultiplier);
  }
}
