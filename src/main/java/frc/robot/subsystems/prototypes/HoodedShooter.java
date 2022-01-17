// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.prototypes;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class HoodedShooter extends SubsystemBase {
    private final WPI_TalonSRX m_motor1;
    private final WPI_TalonSRX m_motor2;
    private final WPI_TalonSRX m_motor3;

    public SpeedControllerGroup speedControl;
    public HoodedShooter() {
        m_motor1 = new WPI_TalonSRX(Constants.shooter.talonSRX1);
        m_motor2 = new WPI_TalonSRX(Constants.shooter.talonSRX2);
        m_motor3 = new WPI_TalonSRX(Constants.shooter.talonSRX3);
        m_motor1.setNeutralMode(NeutralMode.Coast);
        m_motor2.setNeutralMode(NeutralMode.Coast);
        m_motor3.setNeutralMode(NeutralMode.Coast);
        speedControl = new SpeedControllerGroup(m_motor1, m_motor2, m_motor3);
        SmartDashboard.putNumber("Shooter Speed", -0.7);
    }

    public void shootBalls(double speed){
        speedControl.set(speed);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
