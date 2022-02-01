package frc.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

public class DriveMeters extends CommandBase {
    private final Drive m_drive;
    private final double m_distance;

    /**
     * 
     * @param drive The drivebase subsystem
     * @param meters Distance in meters(Note, encoders have to be calibrated to meters)
     */
    public DriveMeters(Drive drive, double meters) {
        m_drive = drive;
        m_distance = meters;
        addRequirements(m_drive);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        m_drive.setPosition(m_distance);
    }


    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        m_drive.arcadeDrive(0, 0);
    }
}
