package frc.robot.commands.climb;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;


public class ResetWinchEncoder extends CommandBase {
    private final Climber m_climber;

    public ResetWinchEncoder(Climber climber) {
        m_climber = climber;
        // each subsystem used by the command must be passed into the
        // addRequirements() method (which takes a vararg of Subsystem)
        addRequirements(m_climber);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        m_climber.resetEncoder();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
