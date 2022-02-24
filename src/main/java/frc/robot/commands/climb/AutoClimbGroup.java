package frc.robot.commands.climb;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.subsystems.*;

import java.util.function.BooleanSupplier;

public class AutoClimbGroup extends SequentialCommandGroup {
    Climber m_climber;
    BooleanSupplier m_end;
    public AutoClimbGroup(Climber climber, BooleanSupplier end) {
        m_climber = climber;
        m_end = end;
        addCommands(
                new RunWinchPID(m_climber, 0),
                new RunWinchPID(m_climber, Constants.climb.extendedValue / 2),
                new RunSolenoid(m_climber,false),
                new RunWinchPID(m_climber, Constants.climb.extendedValue),
                new RunSolenoid(m_climber, true),
                new RunWinchPID(m_climber, Constants.climb.extendedValue / 2),
                new WaitCommand(5),
                new RunWinchPID(m_climber, 0),
                new RunWinchPID(m_climber, Constants.climb.extendedValue / 2),
                new RunSolenoid(m_climber, false),
                new RunWinchPID(m_climber, Constants.climb.extendedValue),
                new RunSolenoid(m_climber, true),
                new RunWinchPID(m_climber, Constants.climb.extendedValue / 2),
                new WaitCommand(5),
                new RunWinchPID(m_climber, 0)
        );
    }
    @Override
    public boolean isFinished(){
        return m_end.getAsBoolean();
    }

}