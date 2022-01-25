package frc.robot.commands.intake;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.intake.CargoIntake;

/** An example command that uses an example subsystem. */
public class RunIntakeSolenoid extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final CargoIntake m_intake;
    private boolean m_erected;

    /**
     * Raises the robot's climber (telescoping).
     *
     * @param intake The robot's climber (telescoping)
     */
    public RunIntakeSolenoid(CargoIntake intake, BooleanSupplier erected) {
        m_intake = intake;
        m_erected = erected.getAsBoolean();
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(m_intake);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_intake.setSolenoid(m_erected);
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