// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.commands.sensor.SensorOverride;
import frc.robot.subsystems.CargoIntake;
import frc.robot.subsystems.DigitalSensor;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;


public class TimedIntake extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

    private final Indexer m_indexer;
    private final CargoIntake m_intake;
    private final DigitalSensor m_sensor;

    private final Timer timer = new Timer();
    private final double totalTime;

    /**
     * Creates a new RunShooter.
     */
    public TimedIntake(Indexer indexer, DigitalSensor sensor, CargoIntake intake, double time) {
        this.m_indexer = indexer;
        this.m_intake = intake;
        this.m_sensor = sensor;
        this.totalTime = time;

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
        if(m_sensor.getTopSensor()) {
            m_indexer.setIndexer1(0);
        } else {
            m_indexer.setSpeed(Constants.indexer.speed);

        }
        m_intake.setSpeed(Constants.intake.speed);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_indexer.setSpeed(0);
        m_intake.setSpeed(0);
        m_intake.setSolenoid(true);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {

        if(m_sensor.sensorBoolean1_2()) {
            m_indexer.setSpeed(0);
            return true;
        }

        return timer.get() > totalTime;
    }
}
