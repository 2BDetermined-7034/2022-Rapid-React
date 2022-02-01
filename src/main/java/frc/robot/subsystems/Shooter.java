// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Shooter extends SubsystemBase {
  /** Creates a new Shooter subsystem */

    public CANSparkMax Motor;
    public CANSparkMax Motor2;
    public Double m_speed;

    public MotorControllerGroup Motors;

    public Shooter() {
        // TODO Might need to have these run separate depending on how the motors/wheels are positioned
        Motor = new CANSparkMax(Constants.shooter.leftShooterNEO, CANSparkMaxLowLevel.MotorType.kBrushless);
        Motor2 = new CANSparkMax(Constants.shooter.rightShooterNEO, CANSparkMaxLowLevel.MotorType.kBrushless);

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
      this.m_speed = speed;
      Motor.set(speed);
      Motor2.set(-speed);
  }


  public void debug() {
      SmartDashboard.putNumber("Shooter Speed", m_speed);
  }
}
