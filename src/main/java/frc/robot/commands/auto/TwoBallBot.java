package frc.robot.commands.auto;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.drive.MotionProfileCommand;
import frc.robot.commands.intake.Solenoid;
import frc.robot.subsystems.*;


public class TwoBallBot extends SequentialCommandGroup {

    public TwoBallBot(Drive m_drive, LimeLight ll, Shooter m_shooter, Indexer m_indexer, AnalogSensor m_analogSensor, CargoIntake m_intake) {
        addCommands(
                new Solenoid(m_intake, true),
                new IntakePath(m_drive, m_indexer,m_analogSensor, m_intake, "2ballIntake", true, Constants.intake.speed, Constants.indexer.speed),
                new TimedShooter(m_drive, ll, m_analogSensor, m_indexer, m_shooter, 2.5)
        );
    }
}