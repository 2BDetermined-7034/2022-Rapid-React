package frc.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drive;

import java.util.function.DoubleSupplier;


public class DriveCommand extends CommandBase {
    private final Drive m_drive;
    private final DoubleSupplier m_driveY;
    private final DoubleSupplier m_driveX;

    /**
     *
     * @param drive The drivebase motors
     * @param joystickY The joystick Y axis
     * @param joystickX The joystick X axis.
     */
    public DriveCommand(Drive drive, DoubleSupplier joystickY, DoubleSupplier joystickX) {
        m_drive = drive;
        m_driveY = joystickY;
        m_driveX = joystickX;

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
        double xSpeed = m_driveY.getAsDouble();
        double zRotation = m_driveX.getAsDouble();
        if (Math.abs(xSpeed) < Constants.controller.yGate) xSpeed = 0;
        if (Math.abs(zRotation) < Constants.controller.xGate) zRotation = 0;

        //m_drive.arcadeDrive(m_driveY.getAsDouble(), m_driveX.getAsDouble());
        m_drive.chezyDrive(m_driveY.getAsDouble(), m_driveX.getAsDouble());
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
