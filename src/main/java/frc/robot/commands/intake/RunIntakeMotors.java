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
    private final GPad m_gpad;
    private final DoubleSupplier m_revSpeed;

    /**
     *
     * @param intakeMotor The intake motor
     * @param speed The speed you want the intake to move at.
     * @param gamepad The game controller
     */
    public RunIntakeMotors(CargoIntake intakeMotor, DoubleSupplier speed, DoubleSupplier reverseSpeed, GPad gamepad) {
        this.fowS = speed;
        this.m_intake = intakeMotor;
        this.m_gpad = gamepad;
        this.m_revSpeed = reverseSpeed;
        addRequirements(intakeMotor);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        double speed = fowS.getAsDouble();
        double reverseSpeed = m_revSpeed.getAsDouble();

        if (reverseSpeed > 0) {
            SmartDashboard.putNumber("Intake Speed", reverseSpeed + 0.3);
            m_intake.mmmRunMotor(reverseSpeed + 0.3);

        } else {
            SmartDashboard.putNumber("Intake speed", -speed + -0.2);
            m_intake.mmmRunMotor(-speed + -0.2);
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