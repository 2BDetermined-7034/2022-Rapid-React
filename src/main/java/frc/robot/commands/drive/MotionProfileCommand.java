package frc.robot.commands.drive;

import java.util.List;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.TrajectoryParameterizer;
import edu.wpi.first.wpilibj.trajectory.constraint.CentripetalAccelerationConstraint;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj.trajectory.constraint.TrajectoryConstraint;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drive;

public class MotionProfileCommand extends CommandBase {
    private static final double ramseteB = 2;
    private static final double ramseteZeta = 0.7;

    private final Drive drive;
    private final DifferentialDriveKinematics kinematics;
    private final Trajectory trajectory;
    private final RamseteController controller = new RamseteController(ramseteB, ramseteZeta);
    private final Timer timer = new Timer();

    /** Creates a new MotionProfileCommand with no extra constraints. */
    public MotionProfileCommand(Drive drive, double startVelocityMetersPerSec, List<Pose2d> waypoints, double endVelocityMetersPerSec, boolean reversed)
    {
        this(drive, startVelocityMetersPerSec, waypoints, endVelocityMetersPerSec, reversed, List.of());
    }

    /** Creates a new MotionProfileCommand with extra constraints. */
    public MotionProfileCommand(Drive drive, double startVelocityMetersPerSec, List<Pose2d> waypoints, double endVelocityMetersPerSec, boolean reversed,  List<TrajectoryConstraint> constraints) {
        addRequirements(drive);
        this.drive = drive;

        // Select max velocity & acceleration
        double maxVoltage, maxVelocityMetersPerSec, maxAccelerationMetersPerSec2, maxCentripetalAccelerationMetersPerSec2;

        maxVoltage = 10.0;
        maxVelocityMetersPerSec = 0.0;
        maxAccelerationMetersPerSec2 = 0.0;
        maxCentripetalAccelerationMetersPerSec2 = 0.0;



        // Set up trajectory configuration
        kinematics = new DifferentialDriveKinematics(drive.getTrackWidthMeters());
        DifferentialDriveVoltageConstraint voltageConstraint =
                new DifferentialDriveVoltageConstraint(new SimpleMotorFeedforward(drive.getKs(), drive.getKv(), drive.getKa()), kinematics, maxVoltage);
        CentripetalAccelerationConstraint centripetalAccelerationConstraint =
                new CentripetalAccelerationConstraint(
                        maxCentripetalAccelerationMetersPerSec2);
        TrajectoryConfig config = new TrajectoryConfig(maxVelocityMetersPerSec,
                maxAccelerationMetersPerSec2).setKinematics(kinematics)
                .addConstraint(voltageConstraint)
                .addConstraint(centripetalAccelerationConstraint)
                .addConstraints(constraints)
                .setStartVelocity(startVelocityMetersPerSec)
                .setEndVelocity(endVelocityMetersPerSec).setReversed(reversed);

        // Generate trajectory
        Trajectory generatedTrajectory;
        try {
            generatedTrajectory = TrajectoryGenerator.generateTrajectory(waypoints, config);
        } catch (TrajectoryParameterizer.TrajectoryGenerationException exception) {
            generatedTrajectory = new Trajectory();
            DriverStation.reportError("Failed to generate trajectory, check constants", false);
        }
        trajectory = generatedTrajectory;
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