package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.drive.Drive;

import java.util.function.DoubleSupplier;


public class DriveToPoint extends CommandBase {
    private final Drive m_drive;
    private final double m_xPos;
    private final double m_yPos;
    private final PIDController m_headingController;
    private final PIDController m_distanceController;

    public DriveToPoint(Drive drive, double xPos, double yPos) {
        m_drive = drive;
        m_xPos = xPos;
        m_yPos = yPos;

        m_headingController = new PIDController(0, 0 ,0);
        m_headingController.setP(Constants.motion.headingkP);
        m_headingController.setI(Constants.motion.headingkI);
        m_headingController.setD(Constants.motion.headingkD);

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

    }

    /**
     * The main body of a command.  Called repeatedly while the command is scheduled.
     * (That is, it is called repeatedly until {@link #isFinished()}) returns true.)
     */
    @Override
    public void execute() {

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
        // TODO: Make this return true when this Command no longer needs to run execute()
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
