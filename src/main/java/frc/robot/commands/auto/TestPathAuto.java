package frc.robot.commands.auto;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drive.MotionProfileCommand;
import frc.robot.commands.intake.RunIntakeMotors;
import frc.robot.subsystems.Drive;


public class TestPathAuto extends SequentialCommandGroup {

    public TestPathAuto(Drive m_drive) {
        addCommands(
            new MotionProfileCommand(m_drive,"New Path", false)
        );
    }
}