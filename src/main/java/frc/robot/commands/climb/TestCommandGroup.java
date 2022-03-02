package frc.robot.commands.climb;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Climber;
import frc.robot.Constants;

public class TestCommandGroup extends SequentialCommandGroup {
    public TestCommandGroup(Climber climber) {
        // TODO: Add your sequential commands in the super() call, e.g.
        //           super(new OpenClawCommand(), new MoveArmCommand());
        super(new RunWinchPID(climber, Constants.climb.maxPos / 2.0), new WaitCommand(5));

        addRequirements(climber);
    }
}