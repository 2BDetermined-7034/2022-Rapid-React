// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.intake;
import frc.robot.Constants;
import frc.robot.subsystems.intake.*;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class RunIntakeMotors extends CommandBase {
    private final CargoIntake m_intake;
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})


    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public RunIntakeMotors(CargoIntake intakeMotor) {
        m_intake = intakeMotor;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(intakeMotor);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {}

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        double speed = Constants.intake.speed;
        m_intake.mmmRunMotor(-speed);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_intake.mmmRunMotor(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}