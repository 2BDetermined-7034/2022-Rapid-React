// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Indexer extends SubsystemBase {
    private final WPI_TalonSRX m_indexer1;
    private double m_speed;

    public Indexer() {
        m_indexer1 = new WPI_TalonSRX(Constants.indexer.indexerTalon);
    }

    public void runIndexerMotor(double speed) {
        this.m_speed = speed;
        m_indexer1.set(-speed);
    }

    public void debug() {
        SmartDashboard.putNumber("Indexer speed", m_speed);
    }
}

