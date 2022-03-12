package frc.robot.commands.auto;


import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import frc.robot.commands.drive.MotionProfileCommand;
import frc.robot.commands.indexer.RunIndexer;
import frc.robot.commands.intake.RunIntakeMotors;
import frc.robot.subsystems.DigitalSensor;
import frc.robot.subsystems.CargoIntake;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Indexer;

public class IntakePath extends ParallelRaceGroup {
    DigitalSensor sensor;


    public IntakePath(Drive m_drive, Indexer m_indexer, DigitalSensor m_sensor, CargoIntake m_cargoIntake, String path, boolean inverted, double intakeSpeed, double indexerSpeed) {
        super(new MotionProfileCommand(m_drive, path, inverted), new RunIndexer(m_indexer,() -> indexerSpeed, m_sensor), new RunIntakeMotors(m_cargoIntake, () -> intakeSpeed, m_sensor));
        sensor = m_sensor;
    }

}