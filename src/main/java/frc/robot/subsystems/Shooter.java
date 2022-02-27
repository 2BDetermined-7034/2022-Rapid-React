// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter subsystem */

    private final CANSparkMax m_motor;
    private final CANSparkMax m_motor2;
    private final MotorControllerGroup m_controlGroup;
    private double m_speeds;

    public Shooter() {
        m_motor = new CANSparkMax(Constants.shooter.leftShooterNEO, CANSparkMaxLowLevel.MotorType.kBrushless);
        m_motor2 = new CANSparkMax(Constants.shooter.rightShooterNEO, CANSparkMaxLowLevel.MotorType.kBrushless);
        m_controlGroup = new MotorControllerGroup(m_motor, m_motor2);

        m_motor.setIdleMode(IdleMode.kCoast);
        m_motor2.setIdleMode(IdleMode.kCoast);

        m_speeds = 0;
    }

  @Override
  public void periodic() {

    m_motor.setVoltage(m_speeds);
    m_motor2.setVoltage(m_speeds);
      //m_motor.set(-SmartDashboard.getNumber("Shooter Speed", 0));
      //m_motor.set(-SmartDashboard.getNumber("Shooter Speed", 0));
  }

  /**
   * Method for command
   * @param speed to be taken from Vision
   */
  public void setSpeed(double speed) {
      m_speeds = speed;
  }

  public double getVoltage() {
      return m_motor.getAppliedOutput() * m_motor.getBusVoltage();
  }


  public void debug() {
      SmartDashboard.putNumber("Shooter Speed", m_speeds);
  }
}
