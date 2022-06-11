package frc.robot.commands.auto;


import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.intake.Solenoid;
import frc.robot.subsystems.*;


public class DefensiveTwoBall extends SequentialCommandGroup {

    public DefensiveTwoBall(Drive m_drive, LimeLight ll, Shooter m_shooter, Indexer m_indexer, DigitalSensor m_analogSensor, CargoIntake m_intake) {

        addCommands(
                new SetOdometry(m_drive, "2ballTop"),
                new Solenoid(m_intake, true),
                new IntakePath(m_drive, m_indexer, m_shooter, m_analogSensor, m_intake, "2ballTop", false, Constants.intake.speed, Constants.indexer.speed),
                new TimedShooter(m_drive, ll, m_analogSensor, m_indexer, m_shooter, Constants.shooter.shootTime),
                new IntakePath(m_drive, m_indexer, m_shooter, m_analogSensor, m_intake, "2ballD1", false, Constants.intake.speed, Constants.indexer.speed),
                new TimedEjectBot(m_shooter, m_indexer, m_intake, m_analogSensor, 2)
        );
    }
}