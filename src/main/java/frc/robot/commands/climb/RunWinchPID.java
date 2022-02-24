package frc.robot.commands.climb;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Climber;


public class RunWinchPID extends CommandBase {
    private final Climber m_climber;
    private final double m_setpoint;

    public RunWinchPID(Climber climber, double setpoint) {
        m_climber = climber;
        m_setpoint = setpoint;
        // each subsystem used by the command must be passed into the
        // addRequirements() method (which takes a vararg of Subsystem)
        addRequirements(m_climber);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        m_climber.setWinchPosition(m_setpoint);
    }

    @Override
    public boolean isFinished() {
        int acceptableError = Constants.climb.encoderAcceptableError;
        // Checks to see if the encoder is within 10 degrees/points or whatever
        // The point value should probably be adjustable as a constant tbh.
        // If it is, then the command ends because it's close enough ¯\_(ツ)_/¯
        return m_climber.getEncoderPosition() >= m_setpoint - acceptableError && m_climber.getEncoderPosition() <= m_setpoint + acceptableError;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
