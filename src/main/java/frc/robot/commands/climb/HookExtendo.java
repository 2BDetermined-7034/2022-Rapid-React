package frc.robot.commands.climb;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class HookExtendo extends CommandBase {
    public final Climber m_climber;
    public final boolean m_direction;

    public HookExtendo(Climber climber, boolean direction) {
        m_climber = climber;
        m_direction = direction;

        addRequirements(m_climber);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        if(m_direction){
            m_climber.setWinchPos(SmartDashboard.getNumber("ExtendedValue", 0));
        }else{
            m_climber.setWinchPos(0);
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
    }
}

