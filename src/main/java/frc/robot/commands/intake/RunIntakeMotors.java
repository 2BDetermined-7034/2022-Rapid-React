package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.controllers.gPad;
import frc.robot.subsystems.intake.CargoIntake;

import java.util.function.DoubleSupplier;

public class RunIntakeMotors extends CommandBase {
    private final CargoIntake m_intake;
    private final DoubleSupplier fowS;
    private final DoubleSupplier revS;
    private final gPad m_gpad;
    public RunIntakeMotors(CargoIntake intakeMotor, DoubleSupplier speed,  DoubleSupplier revSpeed,  gPad gamepad) {
        fowS = speed;
        m_intake = intakeMotor;
        revS = revSpeed;
        m_gpad = gamepad;
        addRequirements(intakeMotor);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        // double speed = SmartDashboard.getNumber("Intake Speed", 0.4);
        //double speed = -Constants.intake.speed;

        double speed = fowS.getAsDouble();
        double reverseSpeed = revS.getAsDouble();

        if(speed == .5) {
            m_intake.mmmRunMotor(.5);
        } else {
            m_intake.mmmRunMotor(-.5);
        }
        //Determines if the intake should run the intake backwards or forwards.

        //If reverseDoubleSupp is getting input (the right bumper is being held), run the intake backwards based on right bumper pressure.


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