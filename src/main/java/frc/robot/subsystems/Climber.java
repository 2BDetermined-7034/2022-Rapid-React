// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
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

        m_solenoid = new DoubleSolenoid(Constants.pneumatics.climber,Constants.climb.solenoidForwardID, Constants.climb.solenoidBackID);
        m_solenoid.set(Value.kForward);
        resetEncoder();

        m_winchTalon.setNeutralMode(NeutralMode.Brake);
        m_winchTalon.config_kP(0, 1);
        //m_winchTalon.config_kI()
    }

    public void runWinch(double speed){
        m_winchTalon.set(speed);
    }


    public void runWinchSafely(double speed){
        if ((getEncoderPosition() <= 0 && speed < 0) || (getEncoderPosition() >= Constants.climb.maxPos && speed > 0)) speed = 0;

        m_winchTalon.set(speed);

    }



    public void setSolenoid(boolean broken) {
        m_solenoid.set(broken ? Value.kForward : Value.kReverse);
    }

    public void toggleSolenoid(){
        if(m_solenoid.get().equals(Value.kForward)){
            m_solenoid.set(Value.kReverse);
        }else{
            m_solenoid.set(Value.kForward);
        }
    }

    public void setWinchPosition(double angle){
        m_winchTalon.set(TalonFXControlMode.Position, angle);
    }

    public void resetEncoder(){
        setEncoder(0);
    }

    public void setEncoder(double pos) {
        m_winchTalon.setSelectedSensorPosition(pos);
    }

    public double getEncoderPosition(){
        return m_winchTalon.getSelectedSensorPosition();
    }

    @Override
    public void periodic() {
        String test;
        if(m_solenoid.get() == DoubleSolenoid.Value.kForward){
            test = "Forward";
        } else {
            test = "Backward";
        }
        SmartDashboard.putString("Arm state: ", test);

        SmartDashboard.putNumber("Climb Winch Position", m_winchTalon.getSelectedSensorPosition());
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }
}