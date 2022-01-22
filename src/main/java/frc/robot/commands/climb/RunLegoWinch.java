// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;

/** An example command that uses an example subsystem. */
public class RunLegoWinch extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final LegoClimb m_legoClimb;

    private double m_speed;

    /**
     * Raises the robot's climber (telescoping).
     *
     * @param legoClimb The robot's climber (telescoping)
     */
    public RunLegoWinch(LegoClimb legoClimb, double speed) {
        m_legoClimb = legoClimb;
        m_speed = speed;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(m_legoClimb);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        //m_legoClimb.runWinch(SmartDashboard.getNumber("TeleClimberWinchSpeed", 0));
        m_legoClimb.runWinch(m_speed);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_legoClimb.runWinch(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
