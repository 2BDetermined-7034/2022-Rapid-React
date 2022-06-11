// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.commands.sensor.SensorOverride;
import frc.robot.subsystems.*;


public class TimedShooterVoltage extends CommandBase {
    private final Drive m_drive;
    private final DigitalSensor analogSensor;

    private final Shooter m_shooter;
    private final Indexer m_index;
    private final Timer timer = new Timer();
    private final double shooterSpeed;
    private final double totalTime;

    public TimedShooterVoltage(Drive drive, DigitalSensor colorSensor, Indexer indexer, Shooter shooter, double power, double time) {
        this.m_drive = drive;
        this.analogSensor = colorSensor;
        this.m_index = indexer;
        this.m_shooter = shooter;
        this.totalTime = time;
        this.shooterSpeed = power;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(m_shooter);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        timer.reset();
        timer.start();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_shooter.setSpeed(shooterSpeed);
        if (Math.abs(m_shooter.getVoltage() - shooterSpeed) <= Constants.shooter.shooterRange) {
            new SensorOverride(analogSensor);
            m_index.setSpeed(Constants.indexer.speed);
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_shooter.setSpeed(0);
        m_index.setSpeed(0);
        m_drive.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return timer.get() > totalTime;
    }
}
