package frc.robot.commands.prototypes.shooters;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;
import frc.robot.subsystems.prototypes.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


import java.util.Set;

public class ShootBallsHooded extends CommandBase {
    private final HoodedShooter m_hoodedShooter;

    public ShootBallsHooded(HoodedShooter hoodedShooter) {
        m_hoodedShooter = hoodedShooter;
        addRequirements(hoodedShooter);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        m_hoodedShooter.shootBalls(SmartDashboard.getNumber("HoodedShooterSpeed", 0));
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
    }
}
