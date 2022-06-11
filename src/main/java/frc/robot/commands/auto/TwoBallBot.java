package frc.robot.commands.auto;


import com.pathplanner.lib.PathPlanner;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryParameterizer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.intake.Solenoid;
import frc.robot.subsystems.*;


public class TwoBallBot extends SequentialCommandGroup {


    Trajectory t2ball1;
    public TwoBallBot(Drive m_drive, LimeLight ll, Shooter m_shooter, Indexer m_indexer, DigitalSensor m_analogSensor, CargoIntake m_intake) {

        addCommands(
                new SetOdometry(m_drive, "2ball1"),
                new Solenoid(m_intake, true),
                new IntakePath(m_drive, m_indexer,m_shooter, m_analogSensor, m_intake, "2ball1", false, Constants.intake.speed, Constants.indexer.speed),
                new TimedShooter(m_drive, ll, m_analogSensor, m_indexer, m_shooter, Constants.shooter.shootTime)
        );
    }
}