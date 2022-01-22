package lib.motion;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.Constants;
import frc.robot.subsystems.drive.Drive;


public class DriveToPoint extends CommandBase {
    private final Drive m_drive;
    private final double m_targetX;
    private final double m_targetY;
    private final boolean m_preAlign;
    private final double m_confidence;
    private final PIDController m_headingController;
    private final PIDController m_distanceController;
    private boolean atPoint = false;

    public DriveToPoint(Drive drive, double xPos, double yPos, boolean preAlign, double confidence) {
        m_drive = drive;
        m_targetX = xPos;
        m_targetY = yPos;
        m_preAlign = preAlign;
        m_confidence = confidence;

        m_headingController = new PIDController(0, 0 ,0);
        m_headingController.setP(Constants.motion.headingkP);
        m_headingController.setI(Constants.motion.headingkI);
        m_headingController.setD(Constants.motion.headingkD);
        m_headingController.enableContinuousInput(-0.5, 0.5);

        m_distanceController = new PIDController(0, 0 ,0);
        m_distanceController.setP(Constants.motion.distancekP);
        m_distanceController.setI(Constants.motion.distancekI);
        m_distanceController.setD(Constants.motion.distancekD);

        addRequirements(m_drive);
    }

    /**
     * The initial subroutine of a command.  Called once when the command is initially scheduled.
     */
    @Override
    public void initialize() {
        if (m_preAlign) {
            while (true) {
                Pose2d robotPos = m_drive.getRobotPos();

                double xDistance = Math.abs(robotPos.getX() - m_targetX);
                double yDistance = Math.abs(robotPos.getY() - m_targetY);

                double heading = Math.toDegrees(Math.atan2(xDistance, yDistance));
                if (heading > 180) heading -= 360;
                double headingControl = MathUtil.clamp(m_headingController.calculate(m_drive.getCurrentAngle() / 360, heading / 360), -1, 1);
                if (Math.abs(headingControl) < m_confidence) break;

                m_drive.arcadeDrive(0, headingControl);
            }
        }
    }

    /**
     * The main body of a command.  Called repeatedly while the command is scheduled.
     * (That is, it is called repeatedly until {@link #isFinished()} returns true.)
     */
    @Override
    public void execute() {
        Pose2d robotPos = m_drive.getRobotPos();

        double xDistance = robotPos.getX() - m_targetX;
        double yDistance = robotPos.getY() - m_targetY;

        double netDistance = Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
        double heading = Math.toDegrees(Math.atan2(xDistance, yDistance));
        if (heading > 180) heading -= 360;

        double distanceControl = MathUtil.clamp(m_headingController.calculate(netDistance, 0), -1, 1);
        double headingControl = MathUtil.clamp(m_headingController.calculate(m_drive.getCurrentAngle() / 360, heading / 360), -1, 1);
        if (Math.abs(distanceControl) < m_confidence && Math.abs(headingControl) < m_confidence) atPoint = true;

        m_drive.arcadeDrive(distanceControl, headingControl);

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
        return atPoint;
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
