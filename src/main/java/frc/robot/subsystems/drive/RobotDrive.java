// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.drive;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;

public class RobotDrive extends SubsystemBase {
    private CANSparkMax leftDriveMotor;
    private CANSparkMax rightDriveMotor;
    private DifferentialDrive m_bot;


    public void Drive(double left, double right) {
        leftDriveMotor = new CANSparkMax(Constants.driveBase.neoMotor1, MotorType.kBrushless);
        rightDriveMotor = new CANSparkMax(Constants.driveBase.neoMotor2, MotorType.kBrushless);

        m_bot = new DifferentialDrive(leftDriveMotor, rightDriveMotor);

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
