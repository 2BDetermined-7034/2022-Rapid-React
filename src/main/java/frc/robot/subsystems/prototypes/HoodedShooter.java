// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.prototypes;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class HoodedShooter extends SubsystemBase {
    private final WPI_TalonSRX m_motor1;
    private final WPI_TalonSRX m_motor2;

    public HoodedShooter() {
        m_motor1 = new WPI_TalonSRX(Constants.shooter.talonSRX1);
        m_motor2 = new WPI_TalonSRX(Constants.shooter.talonSRX2);
        m_motor1.setNeutralMode(NeutralMode.Coast);
        m_motor2.setNeutralMode(NeutralMode.Coast);
        SmartDashboard.putNumber("HoodedShooterSpeed", 0.7);
        SmartDashboard.putNumber("HoodedShooterSpeed2", 0.7);
    }

    public void shootBalls(double speed, double speed2){
        m_motor1.set(speed);
        m_motor2.set(speed2);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
