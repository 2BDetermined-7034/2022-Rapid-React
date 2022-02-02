// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.drive.*;
import frc.robot.commands.indexer.*;
import frc.robot.controllers.*;
import frc.robot.subsystems.*;
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
  private final Shooter m_shooter = new Shooter();
  private final RunShooter m_runShooter = new RunShooter(m_shooter, () -> 0.5);

  private final LimeLight m_limeLight = new LimeLight();

  // Drive
  private final Drive m_drive = new Drive();
  public final GPad m_GPad = new GPad(Constants.controller.gamePadPort);

  private final CargoIntake m_cargoIntake = new CargoIntake();
  private final Indexer m_indexer = new Indexer();
  private final RunIndexer m_runIndexer = new RunIndexer(m_indexer, () -> m_GPad.getAxis("RTrigger"));

  private final RunIntakeMotors m_runIntake = new RunIntakeMotors(m_cargoIntake, () -> 0.5, m_GPad);

  private final IntakeSolenoid m_solUp = new IntakeSolenoid(m_cargoIntake, () -> Constants.intake.intakeUp);
  private final IntakeSolenoid m_solDown = new IntakeSolenoid(m_cargoIntake, () -> Constants.intake.intakeDown);



  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
      // Register
      m_drive.register();
      m_cargoIntake.register();
      m_shooter.register();

      // Default commands
      m_drive.setDefaultCommand(new DriveCommand(m_drive, m_GPad, () -> m_GPad.getAxis("LX"), () -> m_GPad.getAxis("LY")));
      m_indexer.setDefaultCommand(m_runIndexer);

      configureButtonBindings();
  }

    /**
     * Use this method to define your button->command mappings.  Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
     * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        // Intake
        m_GPad.getButton("START").toggleWhenPressed(m_runIntake);
        m_GPad.getButton("RB").toggleWhenPressed(m_runShooter);

        m_GPad.getButton("X").whenPressed(m_solDown);
        m_GPad.getButton("Y").whenPressed(m_solUp);
        // Indexer
        m_GPad.getButton("A").whenPressed(m_runShooter);

    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return The command to run in autonomous.
     */
    public Command getAutonomousCommand() {
        //Returns the "auto" command, which we want to run in autonomous.
        return new DriveMeters(m_drive, 1);
    }
}
