// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LegoClimb;

/** An example command that uses an example subsystem. */
public class LegoSolenoid extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final LegoClimb m_legoClimb;
    private boolean m_erected;

    /**
     * Raises the robot's climber (telescoping).
     *
     * @param legoClimb The robot's climber (telescoping)
     */
    public LegoSolenoid(LegoClimb legoClimb) {
        m_legoClimb = legoClimb;
        m_erected = false;
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
        m_legoClimb.setBrake(m_erected);
        m_erected = !m_erected;
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
