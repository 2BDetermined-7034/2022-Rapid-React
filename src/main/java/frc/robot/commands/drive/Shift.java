package frc.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.Drive;

import java.util.Set;

public class Shift extends CommandBase {
    private final Drive m_drive;
    private final boolean m_gear;

    public Shift(Drive drive, boolean gear) {
        m_drive = drive;
        m_gear = gear;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        m_drive.shift(m_gear);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        m_drive.shift(!m_gear);
    }
}