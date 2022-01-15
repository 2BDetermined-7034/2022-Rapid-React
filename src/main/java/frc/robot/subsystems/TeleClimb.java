// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class TeleClimb extends SubsystemBase {
    private final WPI_TalonSRX driverMotor;
    private final WPI_TalonSRX winchMotor;

    /**
     * Creates a new TeleClimb
     */
    public TeleClimb() {
        driverMotor = new WPI_TalonSRX(Constants.climb.driverMotorID);
        winchMotor = new WPI_TalonSRX(Constants.climb.winchMotorID);

        //Putting smartdashboard stuff
        SmartDashboard.putNumber("TeleClimberRaiseSpeed", 0);
        SmartDashboard.putNumber("TeleClimberWinchSpeed", 0);
    }

    public void runDriver(double speed){
        driverMotor.set(speed);
    }

    public void runWinch(double speed){
        winchMotor.set(speed);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }
}
