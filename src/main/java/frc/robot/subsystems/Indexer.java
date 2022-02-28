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

public class Indexer extends SubsystemBase {
    private final CANSparkMax m_indexer1;
    private final CANSparkMax m_indexer2;
    private double m_speed;

    public Indexer() {
        m_indexer1 = new CANSparkMax(Constants.indexer.indexerMotor1, CANSparkMaxLowLevel.MotorType.kBrushless);
        m_indexer2 = new CANSparkMax(Constants.indexer.indexerMotor2, CANSparkMaxLowLevel.MotorType.kBrushless);
        m_indexer1.setIdleMode(CANSparkMax.IdleMode.kBrake);
        m_indexer2.setIdleMode(CANSparkMax.IdleMode.kBrake);

    }

    /**
     *
     * @param speed The speed of the motor / Double 0-1
     */
    public void setSpeed(double speed) {
        m_indexer1.set(-speed);
        m_indexer2.set(-speed);
    }

    /**
     *
     * @param speed The speed of a single motor / Double 0-1
     */
    public void setIndexer1(double speed) {
        m_indexer1.set(speed);
    }

    /**
     *
     * @param speed The speed of a single motor / Double 0-1
     */
    public void setIndexer2(double speed) {
        m_indexer2.set(-speed);
    }


    public void debug() {
        SmartDashboard.putNumber("Indexer speed", m_speed);
    }
}

