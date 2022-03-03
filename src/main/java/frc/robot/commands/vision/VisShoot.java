package frc.robot.commands.vision;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.commands.sensor.SensorOverride;
import frc.robot.subsystems.*;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;


public class VisShoot extends CommandBase {

    private final Drive m_dt;
    private final LimeLight m_ll;
    //private final Shooter m_shoot;

    private BooleanSupplier m_vis;
    private BooleanSupplier m_interrupt;
    private DoubleSupplier m_drive;

    private final AnalogSensor analogSensor;
    private final Indexer m_indexer;
    private final Shooter m_shooter;

    private boolean tapeDetected;

    private int tapeTimer;

    private double errorY;
    private double errorX;
    private double targetY;
    private double targetX_L;
    private double targetX_R;
    private double distance;

    public VisShoot(Drive dt, LimeLight ll, AnalogSensor analogSensor, Indexer indexer, Shooter shooter, BooleanSupplier useVision, BooleanSupplier interrupt, DoubleSupplier drive) {
        m_dt = dt;
        m_ll = ll;
        //m_shoot = shooter;
        m_vis = useVision;
        m_interrupt = interrupt;
        m_drive = drive;

        this.analogSensor = analogSensor;
        m_indexer = indexer;
        m_shooter = shooter;


        addRequirements(dt);
        //addRequirements(shooter);
        addRequirements(ll);
    }

    // Called just before this Command runs the first time
    @Override
    public void initialize() {
        tapeDetected = false;
        tapeTimer = 0;
        //distance = m_ll.getEstimatedDistance();
        targetY = 45;
        //targetX_L = m_dt.getPositionL();
        //targetX_R = m_dt.getPositionR();
        errorY = 0;
        errorX = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        m_ll.setLights(true);
        boolean vis = m_vis.getAsBoolean();
        tapeDetected = m_ll.getDetected();
        double drive = m_drive.getAsDouble();
        boolean liteMode = true;

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

            //targetX_L = m_dt.getPositionL() + visX;
            //targetX_R = m_dt.getPositionR() + visX;
            //targetY = .8*Math.toDegrees(Math.atan(.7*(distance+1)));
            targetY = 90-(visY+Constants.vision.Vis_LLAngle);

            //add .6 to errorX to help reach actual target
            errorX = visX > 0 ? visX + .9 : visX - .9;
            //errorY = m_shoot.getPivotPosition() - targetY;

            double n = 3;
            //double distance = 0.0256*Constants.vision.VisY_distanceConstant/visA;

        }

        m_dt.arcadeDrive(drive, -errorX/Constants.vision.pGain);

        double llY = m_ll.getYAngle();

        // equation
        double visSpeed = -1 * (5.21 + (.00695 * llY) + (.00146 * Math.pow(llY, 2)) + (.00034 * Math.pow(llY, 3)));

        m_shooter.setSpeed(visSpeed);
        if (Math.abs(m_shooter.getVoltage() - visSpeed) <= Constants.shooter.shooterRange) {
            new SensorOverride(analogSensor);
            m_indexer.setSpeed(Constants.indexer.speed);
        }



    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        //if (errorX < 1.6) return true;

        return m_interrupt.getAsBoolean();
    /*
    (tapeTimer >= Constants.Vis_TimerConfidence)
    && (Math.abs(errorX) < Constants.VisX_Tol)
    && (Math.abs(errorY) < Constants.VisY_Tol)
    && (m_shoot.getPivotVelocity() < Constants.VisX_VTol)
    && (m_dt.getAbsoluteVelocity() < Constants.VisY_VTol);
    */
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
        m_shooter.setSpeed(0);
        m_indexer.setSpeed(0);
        //m_dt.setGear(Constants.HIGH_GEAR);
    }
}