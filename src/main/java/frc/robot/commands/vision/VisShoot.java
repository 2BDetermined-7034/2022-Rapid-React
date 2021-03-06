package frc.robot.commands.vision;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.commands.sensor.SensorOverride;
import frc.robot.subsystems.*;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;


public class VisShoot extends CommandBase {

    private final Drive m_dt;
    private final LimeLight m_ll;

    private BooleanSupplier m_vis;
    private BooleanSupplier m_interrupt;
    private DoubleSupplier m_drive;

    private final DigitalSensor analogSensor;
    private final Indexer m_indexer;
    private final Shooter m_shooter;

    private boolean tapeDetected;

    private int tapeTimer;

    private double errorX;

    public VisShoot(Drive dt, LimeLight ll, DigitalSensor analogSensor, Indexer indexer, Shooter shooter, BooleanSupplier useVision, BooleanSupplier interrupt, DoubleSupplier drive) {
        m_dt = dt;
        m_ll = ll;
        m_vis = useVision;
        m_interrupt = interrupt;
        m_drive = drive;

        this.analogSensor = analogSensor;
        m_indexer = indexer;
        m_shooter = shooter;


        addRequirements(dt);
        addRequirements(ll);
    }

    // Called just before this Command runs the first time
    @Override
    public void initialize() {
        tapeDetected = false;
        tapeTimer = 0;
        errorX = 0;

    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        m_ll.setLights(true);
        boolean vis = m_vis.getAsBoolean();
        tapeDetected = m_ll.getDetected();
        double drive = m_drive.getAsDouble();

        if(tapeDetected) {
            tapeTimer++;
        }
        else {
            tapeTimer = 0;
        }

        if (tapeTimer >= Constants.vision.Vis_TimerConfidence && vis) {
            double visX = m_ll.getXAngle() + Constants.vision.VisX_Offset;
            //add .6 to errorX to help reach actual target
            errorX = visX > 0 ? visX + .9 : visX - .9;
        }
        if (tapeDetected) {
            m_dt.arcadeDrive(0, -errorX / Constants.vision.pGain);
        } else {
            m_dt.arcadeDrive(0, 0.90);
        }

        double llY = m_ll.getYAngle();
        SmartDashboard.putNumber("lly", llY);
        double visSpeed;

        // equation
        /*
        if(llY<6.2){visSpeed = -5.13;}
        else if(llY<10){visSpeed = -5.145;}
        else{
            visSpeed = -26.4 + 5.55*llY + -0.538*Math.pow(llY, 2) + 0.023*Math.pow(llY, 3) + -3.78E-04*Math.pow(llY, 4);
        }

         */
        //OG
        //visSpeed = -1 * (5.205 + (.00695 * llY) + (.00146 * Math.pow(llY, 2)) + (.00034 * Math.pow(llY, 3)) + SmartDashboard.getNumber("ad", 0));

        //Auto
        visSpeed = -1 * (5.1 + (.00695 * llY) + (.00146 * Math.pow(llY, 2)) + (.00034 * Math.pow(llY, 3)) + SmartDashboard.getNumber("ad", 0));


        //double visSpeed = -1 * (5.16 + (.00605 * llY) + (.00146 * Math.pow(llY, 2)) + (.000348 * Math.pow(llY, 3)));
        //double visSpeed = SmartDashboard.getNumber("Bruh", 0);

        m_shooter.setSpeed(visSpeed);
        if (Math.abs(m_shooter.getVoltage() - visSpeed) <= Constants.shooter.shooterRange && tapeDetected) {
            new SensorOverride(analogSensor);
            m_indexer.setSpeed(Constants.indexer.speed);
        }



    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return m_interrupt.getAsBoolean();
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
        m_shooter.setSpeed(Constants.shooter.passivePostSpeed);
        m_indexer.setSpeed(0);
        m_ll.setLights(false);
    }
}