package frc.robot.commands.auto;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.intake.Solenoid;
import frc.robot.subsystems.*;


public class SixBall extends SequentialCommandGroup {

    public SixBall(Drive m_drive, LimeLight ll, Shooter m_shooter, Indexer m_indexer, DigitalSensor m_analogSensor, CargoIntake m_intake) {

        addCommands(
                new SetOdometry(m_drive, "2ball1"),
                new Solenoid(m_intake, true),
                new FollowPath(m_drive, "2ball1", false).getRamseteCommand(),
                new TimedShooter(m_drive, ll, m_analogSensor, m_indexer, m_shooter, Constants.shooter.shootTime),
                new Solenoid(m_intake, true),
                new IntakePath(m_drive, m_indexer,m_shooter, m_analogSensor, m_intake, "5ball2", false, Constants.intake.speed, Constants.indexer.speed),
                new TimedShooter(m_drive, ll, m_analogSensor, m_indexer, m_shooter, 1.7),
                new IntakePath(m_drive, m_indexer,m_shooter, m_analogSensor, m_intake, "5ball3", false, Constants.intake.speed, Constants.indexer.speed),
                new TimedIntake(m_indexer,m_analogSensor, m_intake, 1.8),
                new IntakePath(m_drive, m_indexer,m_shooter, m_analogSensor, m_intake, "5ball4", true, Constants.intake.speed, Constants.indexer.speed),
                new TimedShooter(m_drive, ll, m_analogSensor, m_indexer, m_shooter, Constants.shooter.shootTime)
            );
    }
}