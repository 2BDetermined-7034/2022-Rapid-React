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
    }

    @Override
    public void execute() {
        double speed = fowS.getAsDouble();
        m_intake.setSolenoid(false);
        m_intake.mmmRunMotor(speed);



        /*
        if(m_analog.sensorBoolean0()) {
            m_intake.mmmRunMotor(0);
        }

        if(m_analog.sensorBoolean1_2()) {
            m_intake.mmmRunMotor(0);
        }
        */
    }

    @Override
    public boolean isFinished() {
        // Sets the intake solenoid up
        if(m_analog.sensorBoolean0()) {
            m_intake.setSolenoid(true);
        }
        return m_analog.sensorBoolean0();
    }

    @Override
    public void end(boolean interrupted) {
        m_intake.mmmRunMotor(0);
    }
}