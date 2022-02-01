package frc.robot.commands.drive;

import com.pathplanner.lib.PathPlanner;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryParameterizer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drive;

public class MotionProfileCommand extends CommandBase {
    private static final double ramseteB = 2;
    private static final double ramseteZeta = 0.7;

    private final Drive m_drive;
    private final DifferentialDriveKinematics m_kinematics;
    private Trajectory m_trajectory;
    private final RamseteController controller = new RamseteController(ramseteB, ramseteZeta);
    private final Timer timer = new Timer();


    public MotionProfileCommand(Drive drive, String pathName, boolean reversed) {
        addRequirements(drive);
        this.m_drive = drive;

        // Select max velocity & acceleration
        double maxVoltage, maxVelocity, maxAcceleration, maxCentripetalAcceleration;

        maxVoltage = Constants.motion.maxVoltage;
        maxVelocity = Constants.motion.maxVelocity;
        maxAcceleration = Constants.motion.maxAcceleration;
        maxCentripetalAcceleration = Constants.motion.maxCentripetalAcceleration;

        // Set up trajectory configuration
        m_kinematics = new DifferentialDriveKinematics(Constants.driveBase.width);

        try {
            m_trajectory = PathPlanner.loadPath(pathName, maxVelocity, maxAcceleration, reversed);
        } catch (TrajectoryParameterizer.TrajectoryGenerationException exception) {
            m_trajectory = new Trajectory();
            DriverStation.reportError("Failed to load trajectory", false);
        }
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_drive.setStartingPose(m_trajectory.getInitialPose());
        timer.reset();
        timer.start();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        Trajectory.State setpoint = m_trajectory.sample(timer.get());

        ChassisSpeeds chassisSpeeds = controller.calculate(m_drive.getRobotPos(), setpoint);
        m_drive.kumDrive(chassisSpeeds);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        timer.stop();
        m_drive.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return timer.hasElapsed(m_trajectory.getTotalTimeSeconds());
    }
}
