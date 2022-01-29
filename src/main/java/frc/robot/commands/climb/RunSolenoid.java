// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.climb;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

/** An example command that uses an example subsystem. */
public class RunSolenoid extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final Climber m_climber;
    private BooleanSupplier m_erected;

    /**
     * Raises the robot's climber (telescoping).
     *
     * @param climber The robot's climber (telescoping)
     */
    public RunSolenoid(Climber climber, BooleanSupplier erected) {
        m_climber = climber;
        m_erected = erected;
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
        SmartDashboard.putBoolean("test", m_erected.getAsBoolean());
        m_climber.setSolenoid(m_erected.getAsBoolean());
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
