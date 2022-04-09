package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;


public class AddCorrect extends CommandBase {

    public AddCorrect() {

        addRequirements();
    }

    @Override
    public void initialize() {
        double ad = SmartDashboard.getNumber("ad", 0);
        SmartDashboard.putNumber("ad", ad += Constants.shooter.shooterInc);
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
