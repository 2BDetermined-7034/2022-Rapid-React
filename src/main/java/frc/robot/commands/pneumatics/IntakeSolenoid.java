package frc.robot.commands.pneumatics;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.intake.CargoIntake;

/** An example command that uses an example subsystem. */
public class IntakeSolenoid extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final CargoIntake m_intake;
    private boolean m_boner;

    /**
     *
     * @param intake The robot's intake
     * @param boner Either true or false, or controller input.
     */
    public IntakeSolenoid(CargoIntake intake, BooleanSupplier boner) {
        this.m_intake = intake;
        this.m_boner = boner.getAsBoolean();
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
        SmartDashboard.putBoolean("Intake out", m_boner);
        m_intake.setSolenoid(m_boner);
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