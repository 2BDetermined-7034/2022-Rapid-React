package frc.robot.commands.indexer;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DigitalSensor;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class RunIndexer extends CommandBase {

private final Indexer m_indexer; // NeoIndexer
private final Shooter m_shooter;
private final DoubleSupplier m_speed;
private final DigitalSensor m_sensor;
private final BooleanSupplier m_stop;
    public RunIndexer(Indexer indexer, Shooter m_shooter, DoubleSupplier speed, DigitalSensor colorSensor, BooleanSupplier stop) {
        this.m_indexer = indexer;
        this.m_shooter = m_shooter;
        this.m_speed = speed;
        this.m_sensor = colorSensor;
        this.m_stop = stop;
        addRequirements(m_indexer);
    }

    /**
     * The initial subroutine of a command.  Called once when the command is initially scheduled.
     */
    @Override
    public void initialize() {

    }

    /**
     * The main body of a command.  Called repeatedly while the command is scheduled.
     * (That is, it is called repeatedly until {@link #isFinished()}) returns true.)
     */
    @Override
    public void execute() {
        SmartDashboard.putBoolean("Stop", m_stop.getAsBoolean());
        if (m_stop.getAsBoolean()){
            m_indexer.setSpeed(0);
        } else if(m_sensor.getTopSensor()) {
            m_indexer.setIndexer1(0);
            m_shooter.setSpeed(Constants.shooter.passiveOneSpeed);
            m_indexer.setIndexer2(m_speed.getAsDouble());
        } else {
            m_indexer.setSpeed(m_speed.getAsDouble());
        }



        m_sensor.debug();
    }

    /**
     * <p>
     * Returns whether this command has finished. Once a command finishes -- indicated by
     * this method returning true -- the scheduler will call its {@link #end(boolean)} method.
     * </p><p>
     * Returning false will result in the command never ending automatically. It may still be
     * cancelled manually or interrupted by another command. Hard coding this command to always
     * return true will result in the command executing once and finishing immediately. It is
     * recommended to use * {@link edu.wpi.first.wpilibj2.command.InstantCommand InstantCommand}
     * for such an operation.
     * </p>
     *
     * @return whether this command has finished.
     */
    @Override
    public boolean isFinished() {


        if(m_sensor.sensorBoolean1_2()) {
            m_indexer.setSpeed(0);
            m_shooter.setSpeed(Constants.shooter.passiveFullSpeed);
            return true;
        }


        return false;
    }

    @Override
    public void end(boolean interrupted) {
        m_indexer.setSpeed(0);
    }
}
