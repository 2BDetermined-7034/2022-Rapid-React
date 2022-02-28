// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.commands.sensor.SensorOverride;
import frc.robot.commands.vision.VisAlign;
import frc.robot.commands.vision.VisShoot;
import frc.robot.subsystems.*;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;


public class TimedShooter extends CommandBase {
    private final Drive m_drive;
    private final LimeLight m_ll;
    private final AnalogSensor analogSensor;


    private final Shooter m_shooter;
    private final Indexer m_index;
    private final Timer timer = new Timer();
    private final double totalTime;

    public TimedShooter(Drive drive, LimeLight limelight, AnalogSensor colorSensor, Indexer indexer, Shooter shooter, double time) {
        this.m_drive = drive;
        this.m_ll = limelight;
        this.analogSensor = colorSensor;
        this.m_index = indexer;
        this.m_shooter = shooter;
        this.totalTime = time;

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

        SmartDashboard.putNumber("Nut", 69);
        m_ll.setLights(true);

        double llY = m_ll.getYAngle();

        double visX = m_ll.getXAngle() + Constants.vision.VisX_Offset;

        double errorX = visX > 0 ? visX + .9 : visX - .9;
        m_drive.arcadeDrive(0, -errorX/15);


        double visSpeed = -1 * (5.21 + (.00695 * llY) + (.00146 * Math.pow(llY, 2)) + (.00034 * Math.pow(llY, 3)));

        m_shooter.setSpeed(visSpeed);

        if (errorX <= 1.8) {
            if (Math.abs(m_shooter.getVoltage() - visSpeed) <= Constants.shooter.shooterRange) {
                new SensorOverride(analogSensor);
                m_index.setSpeed(Constants.indexer.speed);
            }
        }


        /*
        if (tapeTimer >= Constants.vision.Vis_TimerConfidence) {

            double errorX = visX > 0 ? visX + .9 : visX - .9;
            m_drive.arcadeDrive(0, errorX);

            double visSpeed = -1 * (5.21 + (.00695 * llY) + (.00146 * Math.pow(llY, 2)) + (.00034 * Math.pow(llY, 3)));

            m_shooter.setSpeed(visSpeed);

            if (errorX < 1.7) {
                if (Math.abs(m_shooter.getVoltage() - visSpeed) <= Constants.shooter.shooterRange) {
                    new SensorOverride(analogSensor);
                    m_index.setSpeed(Constants.indexer.speed);
                }
            }

         */



    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_shooter.setSpeed(0);
        m_index.setSpeed(0);
        m_drive.stop();
        m_drive.setAutoEnabled(false);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return timer.get() > totalTime;
    }
}
