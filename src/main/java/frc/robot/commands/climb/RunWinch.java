// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.climb;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;

/** An example command that uses an example subsystem. */
public class RunWinch extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final Climber m_climber;

    private final double m_speed;

    /**
     * Raises the robot's climber (telescoping).
     *
     * @param climber The robot's climber (telescoping)
     */
    public RunWinch(Climber climber, double speed) {
        m_climber = climber;
        m_speed = speed;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(m_climber);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        //m_legoClimb.runWinch(SmartDashboard.getNumber("TeleClimberWinchSpeed", 0));
        m_climber.runWinch(m_speed);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_climber.runWinch(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}