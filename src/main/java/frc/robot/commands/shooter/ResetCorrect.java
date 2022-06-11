package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;


public class ResetCorrect extends CommandBase {

    public ResetCorrect() {

        addRequirements();
    }

    @Override
    public void initialize() {
        SmartDashboard.putNumber("ad", 0);
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
