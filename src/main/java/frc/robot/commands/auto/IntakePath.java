package frc.robot.commands.auto;


import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import frc.robot.commands.drive.MotionProfileCommand;
import frc.robot.commands.indexer.RunIndexer;
import frc.robot.commands.intake.RunIntakeMotors;
import frc.robot.subsystems.*;

public class IntakePath extends ParallelDeadlineGroup {
    DigitalSensor sensor;

    public IntakePath(Drive m_drive, Indexer m_indexer, Shooter m_shooter, DigitalSensor m_sensor, CargoIntake m_cargoIntake, String path, boolean inverted, double intakeSpeed, double indexerSpeed) {
        super(new FollowPath(m_drive, path, inverted).getRamseteCommand(), new RunIndexer(m_indexer, m_shooter, () -> indexerSpeed, m_sensor, () ->false), new RunIntakeMotors(m_cargoIntake, () -> intakeSpeed, m_sensor, () -> false));
        sensor = m_sensor;
    }

}