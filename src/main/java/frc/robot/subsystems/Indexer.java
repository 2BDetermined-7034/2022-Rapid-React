// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Indexer extends SubsystemBase {
    private final CANSparkMax m_indexer1;

    public Indexer() {
        m_indexer1 = new CANSparkMax(Constants.indexer.indexerNEO1, CANSparkMaxLowLevel.MotorType.kBrushless);
    }

    public void runIndexerMotor(double speed) {
        m_indexer1.set(speed);
    }
}

