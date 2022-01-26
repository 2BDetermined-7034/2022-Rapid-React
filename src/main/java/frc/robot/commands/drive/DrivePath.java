package frc.robot.commands.drive;

import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.drive.Drive;

public class DrivePath extends CommandBase {
    private final Drive m_drive;
    private PathPlannerTrajectory path;
    private final double k_maxVelocity;
    private final double k_maxAcceleration;
    private final Timer timer;
    private final RamseteController controller = new RamseteController(2.1, 0.8);


    public DrivePath(Drive drive) {
        m_drive = drive;
        k_maxAcceleration = Constants.motion.maxAcceleration;
        k_maxVelocity = Constants.motion.maxVelocity;
        timer = new Timer();

        addRequirements(m_drive);
    }

    /**
     * The initial subroutine of a command.  Called once when the command is initially scheduled.
     */
    @Override
    public void initialize() {

    }

    /**
     * The main body of a command.  Called repeatedly while the command is scheduled.
     * (That is, it is called repeatedly until {@link #isFinished()} returns true.)
     */
    @Override
    public void execute() {
        //Trajectory.State goal = path.sample(timer.get());
        //ChassisSpeeds adjustedSpeeds = controller.calculate(m_drive.getRobotPos(), goal);
        //m_drive.kinoDrive(adjustedSpeeds);
        m_drive.setPosition(1);
    }

    /**
     * <p>
     * Returns whether this command has finished. Once a command finishes -- indicated by
     * this method returning true -- the scheduler will call its {@link #end(boolean)} method.
     * </p><p>
     * Returning false will result in the command never ending automatically. It may still be
     * cancelled manually or interrupted by another command. Hard coding this command to always
     * return true will result in the command executing once and finishing immediately. It is
     * recommended to use * {@link edu.wpi.first.wpilibj2.command.InstantCommand InstantCommand}
     * for such an operation.
     * </p>
     *
     * @return whether this command has finished.
     */
    @Override
    public boolean isFinished() {
        return false;
    }

    /**
     * The action to take when the command ends. Called when either the command
     * finishes normally -- that it is called when {@link #isFinished()} returns
     * true -- or when  it is interrupted/canceled. This is where you may want to
     * wrap up loose ends, like shutting off a motor that was being used in the command.
     *
     * @param interrupted whether the command was interrupted/canceled
     */
    @Override
    public void end(boolean interrupted) {
        m_drive.arcadeDrive(0, 0);
    }
}
