// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.controllers.*;
import frc.robot.subsystems.*;
import frc.robot.commands.drive.*;
import frc.robot.commands.pneumatics.*;
import frc.robot.commands.intake.*;
import frc.robot.commands.shooter.*;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  // The robot's subsystems and commands are defined here...
  private final Shooter m_Shooter = new Shooter();
  private final RunShooter m_RunShooter = new RunShooter(m_Shooter, null);

  private final LimeLight m_limeLight = new LimeLight();

  // Drive
  private final Drive m_drive = new Drive(Constants.driveBase.startX, Constants.driveBase.startY);
  public final GPad m_GPad = new GPad(Constants.controller.gamePadPort);

  private final CargoIntake m_cargoIntake = new CargoIntake();
  private final RunIntakeMotors m_runIntake = new RunIntakeMotors(m_cargoIntake,() -> Constants.intake.speed, m_GPad);
  private final RunIntakeMotors m_revINtake = new RunIntakeMotors(m_cargoIntake, () -> -Constants.intake.speed, m_GPad);

  private final IntakeSolenoid m_solUp = new IntakeSolenoid(m_cargoIntake, () -> Constants.intake.solenoid_TRUE);
  private final IntakeSolenoid m_solDown = new IntakeSolenoid(m_cargoIntake, () -> Constants.intake.solenoid_FALSE);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    SmartDashboard.putNumber("ShooterSpeed", 0);
    // Configure the button bindings
    SmartDashboard.putNumber("ty: (data)", m_limeLight.getYAngle());
    SmartDashboard.putNumber("dist?: (data)", m_limeLight.getEstimatedDistance());

    // Register
    m_drive.register();
    m_cargoIntake.register();

    // Default commands
    m_cargoIntake.setDefaultCommand(m_solDown);

    m_drive.setDefaultCommand(new DriveCommand(
                    m_drive,
                    m_GPad,
                    () -> m_GPad.getY(GenericHID.Hand.kLeft),
                    () -> m_GPad.getX(GenericHID.Hand.kRight)
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
        m_GPad.getButton("RB").whenHeld(m_runIntake);
        m_GPad.getButton("X").whenPressed(m_solDown);
        m_GPad.getButton("Y").whenPressed(m_solUp);
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return The command to run in autonomous.
     */
    public Command getAutonomousCommand() {
        //Returns the "auto" command, which we want to run in autonomous.
        return new DriveToPoint(m_drive, 1, 0, false, 0.001);
    }
}
