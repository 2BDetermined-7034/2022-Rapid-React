package frc.robot.commands.climb;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.indexer.RunIndexer;
import frc.robot.subsystems.*;

import java.util.function.BooleanSupplier;

public class AutoClimbGroup extends SequentialCommandGroup {
    //Climber m_climber;
    //BooleanSupplier m_end;
    //CargoIntake m_cargoIntake;
    public AutoClimbGroup(Climber m_climber, BooleanSupplier end, CargoIntake intake) {
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

        //addRequirements(m_climber, m_cargoIntake);
    }
    /*
    @Override
    public boolean isFinished(){
        //return m_end.getAsBoolean();
        return false;
    }

    @Override
    public void initialize(){
        //m_cargoIntake.setSolenoid(true);
    }
     */
}