/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.drive.DriveCommand;
import frc.robot.commands.drive.DrivePath;
import frc.robot.commands.pneumatics.IntakeSolenoid;
import frc.robot.commands.intake.RunIntakeMotors;
import frc.robot.controllers.gPad;
import frc.robot.subsystems.drive.Drive;
import frc.robot.subsystems.intake.CargoIntake;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

    // Drive
    private final Drive m_drive = new Drive(Constants.driveBase.startX, Constants.driveBase.startY);
    public final gPad m_gPad = new gPad(Constants.controller.gamePadPort);

    // Intake
    private final CargoIntake m_cargoIntake = new CargoIntake();
    private final RunIntakeMotors m_runIntake = new RunIntakeMotors(m_cargoIntake,() ->  m_gPad.getAxis("LTrigger"), () -> m_gPad.getAxis("RTrigger"), m_gPad);

    private final IntakeSolenoid m_solUp = new IntakeSolenoid(m_cargoIntake, () -> Constants.intake.solenoid_TRUE);
    private final IntakeSolenoid m_solDown = new IntakeSolenoid(m_cargoIntake, () -> Constants.intake.solenoid_FALSE);
    public RobotContainer() {
        // Register
        m_drive.register();
        m_cargoIntake.register();

        // Default commands
        m_cargoIntake.setDefaultCommand(m_runIntake);
        m_drive.setDefaultCommand(new DriveCommand(
                        m_drive,
                        m_gPad,
                        () -> m_gPad.getY(GenericHID.Hand.kLeft),
                        () -> m_gPad.getX(GenericHID.Hand.kRight)
                )
        );

        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings.  Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
     * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        // Add Button

        SmartDashboard.putData(m_runIntake);
        SmartDashboard.putData("Put Intake Down", m_solDown);
        SmartDashboard.putData("Put Intake Up", m_solUp);

        // Controller button configuration
        m_gPad.getButton("X").whenPressed(m_solDown);
        m_gPad.getButton("Y").whenPressed(m_solUp);
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return The command to run in autonomous.
     */
    public Command getAutonomousCommand() {
        //Returns the "auto" command, which we want to run in autonomous.
        return new DrivePath(m_drive);
    }
}
