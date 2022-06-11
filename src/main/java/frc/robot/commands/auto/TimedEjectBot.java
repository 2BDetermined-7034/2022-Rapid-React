// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.sensor.SensorOverride;
import frc.robot.subsystems.CargoIntake;
import frc.robot.subsystems.DigitalSensor;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;


public class TimedEjectBot extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final DigitalSensor analogSensor;

    private final Shooter m_shooter;
    private final Indexer m_index;
    private final CargoIntake m_intake;

    private final Timer timer = new Timer();
    private final double totalTime;

    /**
     * Creates a new RunShooter.
     *
     * @param subsystem The subsystem used by this command.
     */
    public TimedEjectBot(Shooter subsystem, Indexer indexer, CargoIntake intake, DigitalSensor sensor, double time) {
        this.m_index = indexer;
        this.analogSensor = sensor;
        this.m_intake = intake;
        this.totalTime = time;
        m_shooter = subsystem;

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
        new SensorOverride(analogSensor);

        m_index.setSpeed(-0.9);
        m_intake.setSpeed(-0.25);
        m_shooter.setSpeed(3);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_index.setSpeed(0);
        m_intake.setSpeed(0);
        m_shooter.setSpeed(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return timer.get() > totalTime;
    }
}
