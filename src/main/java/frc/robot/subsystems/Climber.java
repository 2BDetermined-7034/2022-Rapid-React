// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.*;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Climber extends SubsystemBase {
    private final DoubleSolenoid m_solenoid;
    private final WPI_TalonFX m_winchTalon;
    //private final Encoder m_encoder;

    /**
     * Creates a new TeleClimb
     */
    public Climber() {
        m_winchTalon = new WPI_TalonFX(Constants.climb.winchMotorID);

        m_solenoid = new DoubleSolenoid(Constants.climb.solenoidForwardID, Constants.climb.solenoidBackID);

        //TEMP SMARTDASHBOARD PUTNUMBER
        SmartDashboard.putNumber("ExtendedValue", Constants.climb.extendedValue);
    }

    public void runWinch(double speed){
        m_winchTalon.set(speed);
    }
    public void setSolenoid(boolean broken) {
        m_solenoid.set(broken ? Value.kForward : Value.kReverse);
    }
    public void toggleSolenoid(){
        m_solenoid.toggle();
    }

    //public CANError setWinchPos(double angle){
    //    return(m_pid.setReference(angle, ControlType.kPosition));
    //}

    //public void resetEncoder(){
    //    m_encoder.setPosition(0);
    //}

    //public void setEncoder(double pos) {
    //    m_encoder.setPosition(pos);
    //}

    @Override
    public void periodic() {
        String test = "";
        if(m_solenoid.get() == DoubleSolenoid.Value.kForward){
            test = "Forward";
        }else{
            test = "Backward";
        }

        // This method will be called once per scheduler run
        // For testing purposes, put the encoder's value to SmartDashboard
        //SmartDashboard.putNumber("Encoder Value", m_encoder.getPosition());
        SmartDashboard.putString("Solenoid State", test);
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }
}
