package frc.robot.commands.climb;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;


public class RunSolenoid extends CommandBase {
    Climber m_climber;
    boolean m_dir;

    /**
     * Runs the climber's solenoid to push the climber forwards or backwards.
     * @param climber The climber subsystem.
     * @param direction Whether to run the solenoid forward or backwards.
     */
    public RunSolenoid(Climber climber, boolean direction) {
        m_climber = climber;
        m_dir = direction;
        // each subsystem used by the command must be passed into the
        // addRequirements() method (which takes a vararg of Subsystem)
        addRequirements();
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        m_climber.setSolenoid(m_dir);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
