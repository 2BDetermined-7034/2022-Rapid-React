package frc.robot.commands.drive;

import com.pathplanner.lib.PathPlanner;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryParameterizer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drive;

public class MotionProfileCommand extends CommandBase {

    private static final double ramseteB = Constants.motion.b;
    private static final double ramseteZeta = Constants.motion.zeta;

    private final Drive m_drive;
    private Trajectory m_trajectory;
    private final RamseteController controller = new RamseteController(ramseteB, ramseteZeta);
    private final Timer timer = new Timer();

    public MotionProfileCommand(Drive drive, String pathName, boolean reversed) {
        addRequirements(drive);
        this.m_drive = drive;

        // Select max velocity & acceleration
        double maxVelocity, maxAcceleration;

        maxVelocity = Constants.motion.maxVelocity;
        maxAcceleration = Constants.motion.maxAcceleration;

        try {
            m_trajectory = PathPlanner.loadPath(pathName, maxVelocity, maxAcceleration, reversed);
        } catch (TrajectoryParameterizer.TrajectoryGenerationException exception) {
            m_trajectory = new Trajectory();
            DriverStation.reportError("Failed to load trajectory", false);
        }
    }

    double getTime() {
        return m_trajectory.getTotalTimeSeconds();
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_drive.setAutoEnabled(true);
        m_drive.shift(Constants.driveBase.HIGH_GEAR);
        m_drive.setRobotPos(m_trajectory.getInitialPose());
        timer.reset();
        timer.start();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        Trajectory.State setpoint = m_trajectory.sample(timer.get());
        ChassisSpeeds chassisSpeeds = controller.calculate(m_drive.getRobotPos(), setpoint);
        m_drive.voltageDrive(chassisSpeeds);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        timer.stop();
        m_drive.stop();
        m_drive.setAutoEnabled(false);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return timer.hasElapsed(m_trajectory.getTotalTimeSeconds());
    }
}
