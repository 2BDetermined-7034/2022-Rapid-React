// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.*;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
    private final CANSparkMax m_winchNeo;
    private final Solenoid m_solenoid;
    private final CANEncoder m_encoder;
    private final CANPIDController m_pid;

    /**
     * Creates a new TeleClimb
     */
    public Climber() {
        m_winchNeo = new CANSparkMax(Constants.climb.winchMotorID, CANSparkMaxLowLevel.MotorType.kBrushless);
        m_winchNeo.setIdleMode(CANSparkMax.IdleMode.kBrake);

        m_solenoid = new Solenoid(Constants.climb.solenoidID);

        m_pid = new CANPIDController(m_winchNeo);

        m_encoder = m_winchNeo.getEncoder();
        m_encoder.setPosition(0);

        m_pid.setP(Constants.climb.kP);
        m_pid.setI(Constants.climb.kI);
        m_pid.setD(Constants.climb.kD);
    }

    public void runWinch(double speed){
        m_winchNeo.set(speed);
    }
    public void setSolenoid(boolean broken) {
        m_solenoid.set(broken);
    }

    public CANError setWinchPos(double angle){
        return(m_pid.setReference(angle, ControlType.kPosition));
    }

    public void resetEncoder(){
        m_encoder.setPosition(0);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        // For testing purposes, put the encoder's value to SmartDashboard
        SmartDashboard.putNumber("Encoder Value", m_encoder.getPosition());
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }
}
