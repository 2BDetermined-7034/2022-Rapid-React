package frc.robot.commands.intake;

import java.util.function.*;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.intake.CargoIntake;

public class IntakeSolenoid extends CommandBase {
    private CargoIntake m_intake;
    private Boolean boo;

    public void solenoid(CargoIntake intake, Boolean erected) {
        m_intake = intake;
        boo = erected;
        addRequirements(m_intake);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_intake.setBrake(boo);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

}