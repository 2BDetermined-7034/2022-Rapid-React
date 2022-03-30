package frc.robot.commands.auto;

import com.pathplanner.lib.PathPlanner;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryParameterizer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Drive;

public class FollowPath {

    private final RamseteCommand ramseteCommand;
    private final Drive m_drive;
    private final Trajectory m_path;
    public FollowPath(Drive m_drive, String path, boolean reversed) {
        this.m_drive = m_drive;
        Trajectory m_trajectory;
        try {
            m_trajectory = PathPlanner.loadPath(path, Constants.motion.maxVelocity, Constants.motion.maxAcceleration, reversed);
        } catch (TrajectoryParameterizer.TrajectoryGenerationException exception) {
            m_trajectory = new Trajectory();
            DriverStation.reportError("Failed to load trajectory", false);
        }
        this.m_path = m_trajectory;
        ramseteCommand = new RamseteCommand(
                m_trajectory,
                m_drive::getRobotPos,
                new RamseteController(Constants.motion.b, Constants.motion.zeta),
                new SimpleMotorFeedforward(
                        Constants.motion.ksVolts,
                        Constants.motion.kvVoltSecondsPerMeter,
                        Constants.motion.kaVoltSecondsSquaredPerMeter),
                m_drive.get_kinematics(),
                m_drive::getWheelVelocity,
                new PIDController(Constants.motion.kPDriveVel, 0, 0),
                new PIDController(Constants.motion.kPDriveVel, 0, 0),
                // RamseteCommand passes volts to the callback
                m_drive::tankDriveVolts,
                m_drive);

        // Reset odometry to the starting pose of the trajectory.

    }

    public RamseteCommand getRamseteCommand() {

        m_drive.shift(Constants.driveBase.HIGH_GEAR);
        m_drive.setRobotPos(m_path.getInitialPose());
        return ramseteCommand;
    }
}
