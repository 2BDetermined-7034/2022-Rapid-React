package frc.robot.commands.indexer;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AnalogSensor;
import frc.robot.subsystems.Indexer;

import java.util.function.DoubleSupplier;

public class IndexerControl extends CommandBase {

private final Indexer m_indexer; // NeoIndexer
private final DoubleSupplier m_speed;
private final AnalogSensor m_sensor;
    public RunIndexer(Indexer indexer, DoubleSupplier speed, AnalogSensor colorSensor) {
        this.m_indexer = indexer;
        this.m_speed = speed;
        this.m_sensor = colorSensor;
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
        if(m_sensor.sensorBoolean0()) {
            m_indexer.setIndexer1(0);
        }

        if(m_sensor.sensorBoolean1_2()) {
            m_indexer.setSpeed(0);

            return true;
        }

        return false;
    }

    @Override
    public void end(boolean interrupted) {
        m_indexer.setSpeed(0);
    }
}
