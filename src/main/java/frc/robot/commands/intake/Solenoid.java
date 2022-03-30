package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.CargoIntake;


/** An example command that uses an example subsystem. */
public class Solenoid extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final CargoIntake m_intake;
    private final boolean m_hasAir;

    /**
     *
     * @param intake The robot's intake
     */
    public Solenoid(CargoIntake intake, Boolean air) {
        this.m_intake = intake;
        this.m_hasAir = air;
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
        m_intake.setSolenoid(!m_hasAir);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {

    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return true;
    }
}