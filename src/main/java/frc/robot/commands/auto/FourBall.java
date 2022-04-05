package frc.robot.commands.auto;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.intake.Solenoid;
import frc.robot.subsystems.*;


public class FourBall extends SequentialCommandGroup {

    public FourBall(Drive m_drive, LimeLight ll, Shooter m_shooter, Indexer m_indexer, DigitalSensor m_analogSensor, CargoIntake m_intake) {

        addCommands(
                new TwoBallBot(m_drive, ll, m_shooter, m_indexer, m_analogSensor, m_intake),
                new Solenoid(m_intake, true),
                new IntakePath(m_drive, m_indexer, m_shooter, m_analogSensor, m_intake, "4ball2", true, Constants.intake.speed, Constants.indexer.speed),
                new TimedShooter(m_drive, ll, m_analogSensor, m_indexer, m_shooter, Constants.shooter.shootTime)
        );
    }
}