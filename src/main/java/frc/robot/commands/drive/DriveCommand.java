// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drive;


import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drive.RobotDrive;

import java.util.function.DoubleSupplier;

/** An example command that uses an example subsystem. */
public class DriveCommand extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final RobotDrive robotDriveBase;
    private final DoubleSupplier gPadX;
    private final DoubleSupplier gpadY;

    public DriveCommand(RobotDrive robotDriveBase, DoubleSupplier gamepadX, DoubleSupplier gamepadY) {
        this.robotDriveBase = robotDriveBase;
        this.gPadX = gamepadX;
        this.gpadY = gamepadY;

        addRequirements(robotDriveBase);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {}

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        double gPadAxisX = gPadX.getAsDouble();
        double gPadAxisY = gpadY.getAsDouble();

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {

    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
