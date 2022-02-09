// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class NeoIndexer extends SubsystemBase {
    private double m_speed;
    private final CANSparkMax m_indexMotor;

    public NeoIndexer() {

        m_indexMotor = new CANSparkMax(Constants.indexer.indexerTalon, CANSparkMaxLowLevel.MotorType.kBrushless);

    }

    public void setSpeed(double speed) {
        m_speed = speed;
        m_indexMotor.set(speed);
    }

    public void debug() {
        SmartDashboard.putNumber("Indexer speed", m_speed);
    }
}

