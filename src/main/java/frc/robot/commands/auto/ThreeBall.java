package frc.robot.commands.auto;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.drive.MotionProfileCommand;
import frc.robot.commands.intake.Solenoid;
import frc.robot.subsystems.*;


public class ThreeBall extends SequentialCommandGroup {

    public ThreeBall(Drive m_drive, LimeLight ll, Shooter m_shooter, Indexer m_indexer, DigitalSensor m_analogSensor, CargoIntake m_intake) {
        addCommands(
                new Solenoid(m_intake, true),
                new IntakePath(m_drive, m_indexer,m_analogSensor, m_intake, "3ball1", true, Constants.intake.speed, Constants.indexer.speed),
                new TimedShooter(m_drive, ll, m_analogSensor, m_indexer, m_shooter, 2.5),
                new MotionProfileCommand(m_drive, "3ball2", false),
                new Solenoid(m_intake, true),
                new IntakePath(m_drive, m_indexer, m_analogSensor, m_intake, "3ball3", true, Constants.intake.speed, Constants.indexer.speed),
                new TimedShooter(m_drive, ll, m_analogSensor, m_indexer, m_shooter, 2.5)
        );
    }
}