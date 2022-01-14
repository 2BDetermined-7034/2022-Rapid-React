// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.prototypes;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class HoodedShooter extends SubsystemBase {
    private final WPI_TalonSRX m_motor1;
    //private final WPI_TalonSRX m_motor2;
    //private final SpeedControllerGroup m_bothMotors;

    public HoodedShooter() {
        m_motor1 = new WPI_TalonSRX(Constants.hoodedShooterID1);
    //    m_motor2 = new WPI_TalonSRX(Constants.hoodedShooterID2);
    //    m_bothMotors = new SpeedControllerGroup(m_motor1, m_motor2);
    }

    public void shootBalls(double speed){
        //m_bothMotors.set(speed);
        m_motor1.set(speed);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
