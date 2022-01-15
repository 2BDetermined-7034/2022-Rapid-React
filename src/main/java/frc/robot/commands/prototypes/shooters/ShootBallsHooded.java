package frc.robot.commands.prototypes.shooters;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;
import frc.robot.subsystems.prototypes.*;
import frc.robot.Constants;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
        double speed = SmartDashboard.getNumber("HoodedShooterSpeed", 0);
        //double speed = Constants.hoodedShooterSpeed;
        m_hoodedShooter.shootBalls(speed);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        m_hoodedShooter.shootBalls(0); // Resets the motor to 0 speed
    }
}
