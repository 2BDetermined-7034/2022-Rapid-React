// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.drive;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;

public class RobotDrive extends SubsystemBase {
    private CANSparkMax left1DriveMotor;
    private CANSparkMax left2DriveMotor;
    private CANSparkMax left3DriveMotor;
    private CANSparkMax right1DriveMotor;
    private CANSparkMax right2DriveMotor;
    private CANSparkMax right3DriveMotor;
    private DifferentialDrive m_bot;

    SpeedControllerGroup leftMotor;
    SpeedControllerGroup rightMotor;

    public void Drive(Double left, Double right) {

        left1DriveMotor = new CANSparkMax(Constants.driveBase.neoMotorLeft1, MotorType.kBrushless);
        left2DriveMotor = new CANSparkMax(Constants.driveBase.neoMotorLeft2, MotorType.kBrushless);
        left3DriveMotor = new CANSparkMax(Constants.driveBase.neoMotorLeft3, MotorType.kBrushless);
        leftMotor = new SpeedControllerGroup(left1DriveMotor, left2DriveMotor, left3DriveMotor);

        right1DriveMotor = new CANSparkMax(Constants.driveBase.neoMotorRight4, MotorType.kBrushless);
        right2DriveMotor = new CANSparkMax(Constants.driveBase.neoMotorRight5, MotorType.kBrushless);
        right3DriveMotor = new CANSparkMax(Constants.driveBase.neoMotorRight6, MotorType.kBrushless);
        rightMotor = new SpeedControllerGroup(right1DriveMotor, right2DriveMotor, right3DriveMotor);

        m_bot = new DifferentialDrive(leftMotor, rightMotor);

        m_bot.tankDrive(left, right);


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
