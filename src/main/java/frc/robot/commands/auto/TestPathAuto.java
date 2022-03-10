package frc.robot.commands.auto;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drive.MotionProfileCommand;
import frc.robot.commands.intake.RunIntakeMotors;
import frc.robot.commands.intake.Solenoid;
import frc.robot.commands.intake.SolenoidToggle;
import frc.robot.subsystems.AnalogSensor;
import frc.robot.subsystems.CargoIntake;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Indexer;


public class TestPathAuto extends SequentialCommandGroup {

    public TestPathAuto(Drive m_drive, Indexer m_indexer, AnalogSensor m_analogSensor, CargoIntake m_intake) {
        addCommands(
                new MotionProfileCommand(m_drive,"5ball", false),
                new Solenoid(m_intake, true),
                new IntakePath(m_drive, m_indexer, m_analogSensor, m_intake, "5ball", false, 0.5, 0.5)
        );
    }
}