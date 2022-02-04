package frc.robot.commands.climb;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.function.BooleanSupplier;

public class HookExtendo extends CommandBase {
    public final Climber m_climber;
    public final boolean m_direction;
    public final boolean m_stopIt;

    public HookExtendo(Climber climber, boolean direction, BooleanSupplier stopIt) {
        m_climber = climber;
        m_direction = direction;
        m_stopIt = stopIt.getAsBoolean();

        addRequirements(m_climber);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        if(m_direction){
            m_climber.setWinchPosition(SmartDashboard.getNumber("ExtendedValue", 0));
        }else{
            m_climber.setWinchPosition(0);
        }
    }

    @Override
    public boolean isFinished() {
        return(m_stopIt);
    }

    @Override
    public void end(boolean interrupted) {
    }
}

