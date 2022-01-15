// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drive;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drive.bDrive;

import java.util.function.DoubleSupplier;

/** An example command that uses an example subsystem. */
public class DriveCommand extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final bDrive bDriveBase;
    private final DoubleSupplier gPadX;
    private final DoubleSupplier gpadY;

    public DriveCommand(bDrive bDriveBase, DoubleSupplier gamepadX, DoubleSupplier gamepadY) {
        this.bDriveBase = bDriveBase;
        this.gPadX = gamepadX;
        this.gpadY = gamepadY;

        addRequirements(bDriveBase);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {}

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        double gPadAxisX = gPadX.getAsDouble();
        double gPadAxisY = gpadY.getAsDouble();

       // bDriveBase.Drive();

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
