package frc.robot.commands.pneumatics;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Pneumatics;


public class RunCompressor extends CommandBase {
    private final Pneumatics m_compressor;

    public RunCompressor(Pneumatics compressor) {
        m_compressor = compressor;
        // each subsystem used by the command must be passed into the
        // addRequirements() method (which takes a vararg of Subsystem)
        addRequirements(m_compressor);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        m_compressor.setCompressor(true);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
