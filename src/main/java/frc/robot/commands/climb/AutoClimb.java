package frc.robot.commands.climb;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.*;

import java.util.function.BooleanSupplier;

public class AutoClimb extends CommandBase {
    Climber m_climber;

    BooleanSupplier m_end;

    public AutoClimb(Climber climber, BooleanSupplier endBoolean) {
        m_climber = climber;
        m_end = endBoolean;
        // each subsystem used by the command must be passed into the
        // addRequirements() method (which takes a vararg of Subsystem)
        addRequirements(m_climber);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        m_climber.setWinchPosition(Constants.climb.extendedValue);

    }

    @Override
    public boolean isFinished() {
        return m_end.getAsBoolean();
    }

    @Override
    public void end(boolean interrupted) {

    }
}
