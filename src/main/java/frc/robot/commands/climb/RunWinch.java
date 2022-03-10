// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;

import java.util.function.DoubleSupplier;

/** An example command that uses an example subsystem. */
public class RunWinch extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final Climber m_climber;

    private final DoubleSupplier m_fowSpeed;
    private final DoubleSupplier m_override;
    private final DoubleSupplier m_slow;

    /**
     * Raises the robot's climber (telescoping).
     *
     * @param climber The robot's climber (telescoping)
     */
    public RunWinch(Climber climber, DoubleSupplier speed, DoubleSupplier override, DoubleSupplier slow) {
        m_climber = climber;
        m_fowSpeed = speed;
        m_override = override;
        m_slow = slow;
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
        //m_climber.runWinch(m_fowSpeed.getAsDouble());
        double speed = m_fowSpeed.getAsDouble();
        if (m_slow.getAsDouble() >= 0.5) {
            speed /= 2;
        }

        m_climber.runWinchSafely(speed);



        if (m_override.getAsDouble() >= 0.5) {
            m_climber.runWinch(speed);
        } else {
            m_climber.runWinchSafely(speed);
        }


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