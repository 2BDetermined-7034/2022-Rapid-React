package frc.robot.commands.intake;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.controllers.GPad;
import frc.robot.subsystems.CargoIntake;
import frc.robot.subsystems.Indexer;

import java.util.function.DoubleSupplier;

public class RunIntakeMotors extends CommandBase {
    private final CargoIntake m_intake;
    private final DoubleSupplier fowS;
    /**
     *
     * @param intakeMotor The intake motor
     * @param speed The speed you want the intake to move at.
     */
    public RunIntakeMotors(CargoIntake intakeMotor, DoubleSupplier speed) {
        this.fowS = speed;
        this.m_intake = intakeMotor;
        addRequirements(intakeMotor);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        double speed = fowS.getAsDouble();

        m_intake.mmmRunMotor(speed);


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