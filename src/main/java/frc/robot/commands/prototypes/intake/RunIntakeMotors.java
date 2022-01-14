package frc.robot.commands.prototypes.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.prototypes.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.function.DoubleSupplier;

public class RunIntakeMotors extends CommandBase {
    private final DoubleSupplier dubSup;
    private final CargoIntake m_intake;

    public RunIntakeMotors(CargoIntake intakeMotor, DoubleSupplier xSpeed) {
        dubSup = xSpeed;
        m_intake = intakeMotor;
        addRequirements(intakeMotor);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        double speed = SmartDashboard.getNumber("IntakeSpeed", 0);
        //double speed = -0.9;
        m_intake.mmmRunMotor(Constants.cargoIntakeSpeed);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        m_intake.mmmRunMotor(0);
    }
}
