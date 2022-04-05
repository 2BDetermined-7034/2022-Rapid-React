package frc.robot.commands.auto;

import com.pathplanner.lib.PathPlanner;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryParameterizer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drive;


public class SetOdometry extends CommandBase {
    private final Drive m_drive;
    private final Trajectory m_path;

    public SetOdometry(Drive drive, String path) {
        this.m_drive = drive;
        Trajectory m_trajectory;
        try {
            m_trajectory = PathPlanner.loadPath(path, Constants.motion.maxVelocity, Constants.motion.maxAcceleration, false);
        } catch (TrajectoryParameterizer.TrajectoryGenerationException exception) {
            m_trajectory = new Trajectory();
            DriverStation.reportError("Failed to load trajectory", false);
        }
        this.m_path = m_trajectory;
        addRequirements(this.m_drive);
    }

    @Override
    public void initialize() {
        m_drive.setRobotPos(m_path.getInitialPose());
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
