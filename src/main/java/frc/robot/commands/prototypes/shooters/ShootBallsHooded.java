package frc.robot.commands.prototypes.shooters;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.controllers.GPad;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import frc.robot.subsystems.prototypes.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShootBallsHooded extends CommandBase {
    private final HoodedShooter m_hoodedShooter;
    private final GPad m_gpad;


    public ShootBallsHooded(HoodedShooter hoodedShooter, GPad controller) {
        m_hoodedShooter = hoodedShooter;
        m_gpad = controller;
        addRequirements(hoodedShooter);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {

        double speed = SmartDashboard.getNumber("Shooter Speed", 0);
        double topSpeed = SmartDashboard.getNumber("top speed", 0);
        m_gpad.setRumble(RumbleType.kRightRumble, speed * 2);
        m_gpad.setRumble(RumbleType.kLeftRumble, topSpeed * 2);
        //double speed = -Constants.shooter.speed;
        m_hoodedShooter.runTopMotor(topSpeed);
        m_hoodedShooter.shootBalls(speed);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        m_gpad.setRumble(RumbleType.kRightRumble, 0);
        m_gpad.setRumble(RumbleType.kLeftRumble, 0);
        m_hoodedShooter.runTopMotor(0);
        m_hoodedShooter.shootBalls(0); // Resets the motor to 0 speed
    }
}
