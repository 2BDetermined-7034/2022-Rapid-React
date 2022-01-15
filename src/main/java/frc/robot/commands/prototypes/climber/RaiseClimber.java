package frc.robot.commands.prototypes.climber;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.prototypes.ClimbPrototype1;


public class RaiseClimber extends CommandBase {
    private final ClimbPrototype1 m_climbPrototype1;

    public RaiseClimber(ClimbPrototype1 climbPrototype1) {
        m_climbPrototype1 = climbPrototype1;

        // Put smartdashboard values for easy adjustment while prototyping

        // each subsystem used by the command must be passed into the
        // addRequirements() method (which takes a vararg of Subsystem)
        addRequirements(m_climbPrototype1);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
