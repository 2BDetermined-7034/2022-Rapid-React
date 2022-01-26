package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.controllers.gPad;
import frc.robot.subsystems.drive.Drive;

import java.util.function.DoubleSupplier;


public class DriveCommand extends CommandBase {
    private final Drive m_drive;
    private final gPad m_gPad;
    private final DoubleSupplier m_driveY;
    private final DoubleSupplier m_driveX;

    public DriveCommand(Drive drive, gPad gamepad, DoubleSupplier joystickY, DoubleSupplier joystickX) {
        m_gPad = gamepad;
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
        m_drive.arcadeDrive(m_driveY.getAsDouble(), -m_driveX.getAsDouble());
        m_gPad.setRumble(RumbleType.kLeftRumble, m_driveY.getAsDouble());
        m_gPad.setRumble(RumbleType.kRightRumble, m_driveX.getAsDouble());
        //m_drive.debugNavX();
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
