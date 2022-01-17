// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class LegoClimb extends SubsystemBase {
    private CANSparkMax driverNeo;
    private CANSparkMax winchNeo;

    /**
     * Creates a new TeleClimb
     */
    public LegoClimb() {
        winchNeo = new CANSparkMax(Constants.climb.winchMotorID, CANSparkMaxLowLevel.MotorType.kBrushless);

        //Putting smartdashboard stuff
        SmartDashboard.putNumber("TeleClimberWinchSpeed", 0);
    }

    public void runWinch(double speed){
        winchNeo.set(speed);
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
