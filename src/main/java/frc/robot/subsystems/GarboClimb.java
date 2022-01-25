// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.*;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class GarboClimb extends SubsystemBase {
    private CANSparkMax driverNeo;
    private CANSparkMax winchNeo;
    private Solenoid m_brake;

    /**
     * Creates a new TeleClimb
     */
    public GarboClimb() {
        winchNeo = new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless);
        winchNeo.setIdleMode(CANSparkMax.IdleMode.kBrake);

        m_brake = new Solenoid(Constants.climb.solenoidID);


        //Putting smartdashboard stuff
        SmartDashboard.putNumber("TeleClimberWinchSpeed", 0);
    }

    public void runWinch(double speed){
        winchNeo.set(speed);
    }
    public void setBrake(boolean broken) {
        m_brake.set(broken);
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