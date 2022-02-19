package frc.robot.commands.climb;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;


public class RunSolenoidToggle extends CommandBase {
    Climber m_climber;
    boolean m_direction = false;


    public RunSolenoidToggle(Climber climber) {
        m_climber = climber;

        // each subsystem used by the command must be passed into the
        // addRequirements() method (which takes a vararg of Subsystem)
        addRequirements(m_climber);
    }

    @Override
    public void initialize() {
        m_direction = !m_direction;
    }

    @Override
    public void execute() {
        m_climber.setSolenoid(m_direction);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
