// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TeleClimb;

/** An example command that uses an example subsystem. */
public class RunWinch extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final TeleClimb m_teleClimb;

    /**
     * Raises the robot's climber (telescoping).
     *
     * @param teleClimb The robot's climber (telescoping)
     */
    public RunWinch(TeleClimb teleClimb) {
        m_teleClimb = teleClimb;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(m_teleClimb);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_teleClimb.runWinch(SmartDashboard.getNumber("TeleClimberWinchSpeed", 0));
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_teleClimb.runWinch(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
