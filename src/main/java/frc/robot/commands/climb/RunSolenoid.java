package frc.robot.commands.climb;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;


public class RunSolenoid extends CommandBase {
    Climber m_climber;

    /**
     * Runs the climber's solenoid to push the climber forwards or backwards.
     * @param climber The climber subsystem.
     */
    public RunSolenoid(Climber climber) {
        m_climber = climber;
        // each subsystem used by the command must be passed into the
        // addRequirements() method (which takes a vararg of Subsystem)
        addRequirements();
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        m_climber.toggleSolenoid();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
