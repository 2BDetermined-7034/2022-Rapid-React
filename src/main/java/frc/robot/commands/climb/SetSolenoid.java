package frc.robot.commands.climb;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;


public class SetSolenoid extends CommandBase {
    Climber m_climber;
    boolean m_direction;


    public SetSolenoid(Climber climber, boolean dir) {
        m_climber = climber;
        m_direction = dir;

        // each subsystem used by the command must be passed into the
        // addRequirements() method (which takes a vararg of Subsystem)
        addRequirements(m_climber);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        m_climber.setSolenoid(m_direction);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
