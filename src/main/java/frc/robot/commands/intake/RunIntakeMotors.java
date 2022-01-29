package frc.robot.commands.intake;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.controllers.gPad;
import frc.robot.subsystems.intake.CargoIntake;

import java.util.function.DoubleSupplier;

public class RunIntakeMotors extends CommandBase {
    private final CargoIntake m_intake;
    private final DoubleSupplier fowS;
    private final DoubleSupplier backS;
    private final gPad m_gpad;

    /**
     *
     * @param intakeMotor The intake motor
     * @param speed The speed you want the intake to move at.
     * @param gamepad The game controller
     */
    public RunIntakeMotors(CargoIntake intakeMotor, DoubleSupplier speed, DoubleSupplier revSpeed, gPad gamepad) {
        this.fowS = speed;
        this.backS = revSpeed;
        this.m_intake = intakeMotor;
        this.m_gpad = gamepad;
        addRequirements(intakeMotor);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {

        double speed = fowS.getAsDouble();
        double reverseSpeed = backS.getAsDouble();

        if (reverseSpeed > 0) {
            m_intake.mmmRunMotor(reverseSpeed);

        } else {
            m_intake.mmmRunMotor(-speed);
        }
        /*
        if(dashSpeed > 0.1) {
            m_intake.mmmRunMotor(dashSpeed);
        } else {
            m_intake.mmmRunMotor(-dashSpeed);
        }
         */


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