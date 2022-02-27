package frc.robot.commands.vision;

import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Shortcuts;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.commands.sensor.SensorOverride;
import frc.robot.subsystems.AnalogSensor;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.Shooter;

public class AlignAutoShoot extends CommandBase {
    private final Drive m_dt;
    private final LimeLight m_ll;
    private BooleanSupplier m_vis;
    private BooleanSupplier m_interrupt;
    private DoubleSupplier m_drive;
    private boolean tapeDetected;
    private int tapeTimer;
    private double errorY;
    private double errorX;
    private double targetY;
    private double targetX_L;
    private double targetX_R;
    private double distance;
    private final AnalogSensor analogSensor;
    private final Indexer m_indexer;
    private final Shooter m_shooter;

    private final Timer timer = new Timer();
    private double llY;

    public AlignAutoShoot(Drive drive, LimeLight limelight, AnalogSensor colorSensor, Indexer indexer, Shooter shooter) {

        this.m_dt = drive;
        this.m_ll = limelight;
        this.analogSensor = colorSensor;
        this.m_indexer = indexer;
        this.m_shooter = shooter;

        // each subsystem used by the command must be passed into the
        // addRequirements() method (which takes a vararg of Subsystem)
        addRequirements(m_dt, m_ll, analogSensor, m_indexer, m_shooter);
    }

    @Override
    public void initialize() {

        tapeDetected = false;
        tapeTimer = 0;
        targetY = 45;
        errorY = 0;
        errorX = 0;

        timer.reset();
        timer.start();

    }

    @Override
    public void execute() {
        m_ll.setLights(true);
        boolean vis = m_vis.getAsBoolean();
        boolean liteMode = true;
        double drive = m_drive.getAsDouble();
        //set the variable to Y-axis
        llY = m_ll.getYAngle();
        tapeDetected = m_ll.getDetected();

        // Vision Align

        if(tapeDetected) {
            tapeTimer++;
        }
        else {
            tapeTimer = 0;
        }

        if (tapeTimer >= Constants.vision.Vis_TimerConfidence && vis) {
            double visY = m_ll.getYAngle();
            double visX = m_ll.getXAngle() + Constants.vision.VisX_Offset;
            double visA = m_ll.getArea();
            distance = m_ll.getEstimatedDistance();

            targetY = 90-(visY+Constants.vision.Vis_LLAngle);
            errorX = visX > 0 ? visX + .9 : visX - .9;

            double n = 3;

        }

        if(liteMode) {
            m_dt.arcadeDrive(drive, -errorX/15);
        }

        // Auto Shoot

        // equation
        double visSpeed = -1*(5.21+(.00695*llY)+(.00146*Math.pow(llY, 2))+(.00034*Math.pow(llY, 3)));


        if(timer.get() > 1.5) {
            new SensorOverride(analogSensor);
            SmartDashboard.putNumber("Shooter Vis Speed", visSpeed);
            SmartDashboard.putNumber("Shooter Voltage", m_shooter.getVoltage());
            SmartDashboard.putBoolean("Is shooter speed equal", m_shooter.getVoltage() == visSpeed);

            m_indexer.setSpeed(Constants.indexer.speed + .1);
        }
        /* Here's the thing, I have no clue if this works or will work.
            while(m_shooter.getMotor() == visSpeed) {
                m_indexer.setSpeed(Constants.indexer.speed + 1);
            }
             */

        //plug into equation
        m_shooter.setSpeed(visSpeed);

    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        m_shooter.setSpeed(0);
        m_indexer.setSpeed(0);
    }
}
