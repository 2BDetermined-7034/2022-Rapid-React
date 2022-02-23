package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AnalogSensor;
import frc.robot.subsystems.CargoIntake;

import java.util.function.DoubleSupplier;


public class RunIntakeMotors extends CommandBase {
    private final CargoIntake m_intake;
    private final DoubleSupplier fowS;
    private final AnalogSensor m_analog;
    /**
     *
     * @param intakeMotor The intake motor
     * @param speed The speed you want the intake to move at.
     */
    public RunIntakeMotors(CargoIntake intakeMotor, DoubleSupplier speed, AnalogSensor colorSensor) {
        this.fowS = speed;
        this.m_analog = colorSensor;
        this.m_intake = intakeMotor;
        addRequirements(intakeMotor);
    }

    @Override
    public void initialize() {
        m_intake.setSolenoid(false);
    }

    @Override
    public void execute() {
        double speed = fowS.getAsDouble();
        m_intake.setSpeed(speed);

    }

    @Override
    public boolean isFinished() {
/*
       if(m_analog.sensorBoolean1_2()) {
           m_intake.setSpeed(0);
           m_intake.setSolenoid(true);
       }

 */
        return false;

    }

    @Override
    public void end(boolean interrupted) {
        m_intake.setSpeed(0);
    }
}