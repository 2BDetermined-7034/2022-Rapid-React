package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;


public class SolenoidToggle extends CommandBase {
    CargoIntake m_intake;
    boolean m_direction = false;


    public SolenoidToggle(CargoIntake intake) {
        m_intake = intake;

        // each subsystem used by the command must be passed into the
        // addRequirements() method (which takes a vararg of Subsystem)
        addRequirements(m_intake);
    }

    @Override
    public void initialize() {
        m_direction = !m_direction;
    }

    @Override
    public void execute() {
        m_intake.setSolenoid(m_direction);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
