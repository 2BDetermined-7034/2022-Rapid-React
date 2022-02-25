package frc.robot.commands.vision;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.commands.sensor.SensorOverride;
import frc.robot.subsystems.AnalogSensor;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.Shooter;

import java.util.function.DoubleSupplier;


public class AutoShoot extends CommandBase {
    private final AnalogSensor analogSensor;
    private final Indexer m_indexer;
    private final LimeLight m_ll;
    private final Shooter m_shooter;
    private final Timer timer = new Timer();

    private double llY;

    public AutoShoot(AnalogSensor analogSensor, Indexer indexer, LimeLight limeLight, Shooter shooter) {
        this.analogSensor = analogSensor;
        m_indexer = indexer;
        m_ll = limeLight;
        m_shooter = shooter;
        // each subsystem used by the command must be passed into the
        // addRequirements() method (which takes a vararg of Subsystem)
        addRequirements(this.analogSensor, m_indexer, m_ll, m_shooter);
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        if(timer.get() > 1.5) {
            new SensorOverride(analogSensor);
            SmartDashboard.putNumber("Shooter Value?", m_shooter.getMotor());

            m_indexer.setSpeed(Constants.indexer.speed);
        }
        /* Here's the thing, I have no clue if this works or will work.
            while(m_shooter.getMotor() == -1*(5.21+(.00695*llY)+(.00146*Math.pow(llY, 2))+(.00034*Math.pow(llY, 3)))) {
                m_indexer.setSpeed(Constants.indexer.speed);
            }
             */
        //set the variable to Y-axis
        llY = m_ll.getYAngle();
        //plug into equation
        m_shooter.setSpeed(-1*(5.21+(.00695*llY)+(.00146*Math.pow(llY, 2))+(.00034*Math.pow(llY, 3))));
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        m_shooter.setSpeed(0);
        m_indexer.setSpeed(0);
    }
}
