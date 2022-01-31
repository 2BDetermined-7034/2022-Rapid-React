package frc.robot.commands.drive;

import java.util.List;

import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.TrajectoryParameterizer;
import edu.wpi.first.math.trajectory.constraint.CentripetalAccelerationConstraint;
import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.math.trajectory.constraint.TrajectoryConstraint;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drive;

public class MotionProfileCommand extends CommandBase {
    private static final double ramseteB = 2;
    private static final double ramseteZeta = 0.7;

    private final Drive drive;
    private final DifferentialDriveKinematics kinematics;
    private Trajectory trajectory;
    private final RamseteController controller = new RamseteController(ramseteB, ramseteZeta);
    private final Timer timer = new Timer();


    public MotionProfileCommand(Drive drive, String pathName, boolean reversed) {
        addRequirements(drive);
        this.drive = drive;

        // Select max velocity & acceleration
        double maxVoltage, maxVelocity, maxAcceleration, maxCentripetalAcceleration;

        maxVoltage = Constants.motion.maxVoltage;
        maxVelocity = Constants.motion.maxVelocity;
        maxAcceleration = Constants.motion.maxAcceleration;
        maxCentripetalAcceleration = Constants.motion.maxCentripetalAcceleration;

        // Set up trajectory configuration
        kinematics = new DifferentialDriveKinematics(Constants.driveBase.width);

        try {
            trajectory = PathPlanner.loadPath(pathName, maxVelocity, maxAcceleration, reversed);
        } catch (TrajectoryParameterizer.TrajectoryGenerationException exception) {
            trajectory = new Trajectory();
            DriverStation.reportError("Failed to load trajectory", false);
        }
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        timer.reset();
        timer.start();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        Trajectory.State setpoint = trajectory.sample(timer.get());

        ChassisSpeeds chassisSpeeds = controller.calculate(drive.getRobotPos(), setpoint);
        drive.kinoDrive(chassisSpeeds);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        timer.stop();
        drive.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return timer.hasElapsed(trajectory.getTotalTimeSeconds());
    }
}
