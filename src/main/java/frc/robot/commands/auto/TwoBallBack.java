package frc.robot.commands.auto;


import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.intake.Solenoid;
import frc.robot.subsystems.*;


public class TwoBallBack extends SequentialCommandGroup {


    Trajectory t2ball1;
    public TwoBallBack(Drive m_drive, LimeLight ll, Shooter m_shooter, Indexer m_indexer, DigitalSensor m_analogSensor, CargoIntake m_intake) {

        addCommands(
                new SetOdometry(m_drive, "2ball1"),
                new Solenoid(m_intake, true),
                new FollowPath(m_drive, "2ball1", false).getRamseteCommand(),
                new TimedShooter(m_drive, ll, m_analogSensor, m_indexer, m_shooter, Constants.shooter.shootTime)
        );
    }
}