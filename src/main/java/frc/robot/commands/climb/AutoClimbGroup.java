package frc.robot.commands.climb;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.indexer.RunIndexer;
import frc.robot.subsystems.*;

import java.util.function.BooleanSupplier;

public class AutoClimbGroup extends SequentialCommandGroup {
    Climber m_climber;
    BooleanSupplier m_end;
    CargoIntake m_cargoIntake;
    public AutoClimbGroup(Climber climber, BooleanSupplier end, CargoIntake intake) {
        m_climber = climber;
        m_end = end;
        m_cargoIntake = intake;
        addCommands(
                new RunWinchPID(m_climber, 0),
                new RunWinchPID(m_climber, Constants.climb.extendedValue / 2.0),
                new RunSolenoid(m_climber),
                new RunWinchPID(m_climber, Constants.climb.extendedValue),
                new RunSolenoid(m_climber),
                new RunWinchPID(m_climber, Constants.climb.extendedValue / 2.0),
                new WaitCommand(5),
                new RunWinchPID(m_climber, 0),
                new RunWinchPID(m_climber, Constants.climb.extendedValue / 2.0),
                new RunSolenoid(m_climber),
                new RunWinchPID(m_climber, Constants.climb.extendedValue),
                new RunSolenoid(m_climber),
                new RunWinchPID(m_climber, Constants.climb.extendedValue / 2.0),
                new WaitCommand(5),
                new RunWinchPID(m_climber, 0),
                new RunWinchPID(m_climber, Constants.climb.extendedValue / 4.0)
        );
    }
    @Override
    public boolean isFinished(){
        return m_end.getAsBoolean();
    }

    @Override
    public void initialize(){
        m_cargoIntake.setSolenoid(true);
    }
}