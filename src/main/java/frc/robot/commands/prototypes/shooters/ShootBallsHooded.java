package frc.robot.commands.prototypes.shooters;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.prototypes.*;

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
        double speed = SmartDashboard.getNumber("Shooter Speed", 0);
        double topSpeed = SmartDashboard.getNumber("top speed", 0);
        //double speed = -Constants.shooter.speed;
        m_hoodedShooter.RunTopMotor(topSpeed);
        m_hoodedShooter.shootBalls(speed);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        m_hoodedShooter.RunTopMotor(0);
        m_hoodedShooter.shootBalls(0); // Resets the motor to 0 speed
    }
}
