// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.drive.*;
import frc.robot.commands.indexer.*;
import frc.robot.commands.sensor.ReadSensor;
import frc.robot.controllers.*;
import frc.robot.subsystems.*;
import frc.robot.commands.intake.*;
import frc.robot.commands.shooter.*;
import frc.robot.commands.vision.*;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    private final X3D joystick = new X3D(1);
    private final X3D joystick2 = new X3D(2);
    private final CargoIntake m_cargoIntake = new CargoIntake();
    private final Indexer m_indexer = new Indexer();

    private final AnalogSensor m_analogSenseor = new AnalogSensor();

    private final LimeLight m_limeLight = new LimeLight();

    private final Drive m_drive = new Drive();

    private final Shooter m_shooter = new Shooter();

    private final ReadSensor m_readSensor = new ReadSensor(m_analogSenseor);
  // The robot's subsystems and commands are defined here...

  private final RunShooter m_runShooter = new RunShooter(m_shooter, m_indexer, () -> SmartDashboard.getNumber("Shooter Speed", 0));

  // Drive
  public final GPad m_GPad = new GPad(Constants.controller.gamePadPort);

  private final RunIndexer m_runIndexer = new RunIndexer(m_indexer, () -> 0.3, m_analogSenseor);

  private final RunIntakeMotors m_runIntake = new RunIntakeMotors(m_cargoIntake,  () -> -0.4, m_analogSenseor);

  private final Solenoid m_solUp = new Solenoid(m_cargoIntake, false);
  private final Solenoid m_solDown = new Solenoid(m_cargoIntake, true);



  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
      // Register
      m_drive.register();
      m_cargoIntake.register();

      m_analogSenseor.setDefaultCommand(m_readSensor);

      SmartDashboard.putData("Intake Down", m_solDown);
      SmartDashboard.putData("Intake Up", m_solUp);
      SmartDashboard.putNumber("Shooter Speed", 0.2);
      //SmartDashboard.putBoolean("switched", joystick.getRawButtonPressed(3));
      // Default commands
      //m_drive.setDefaultCommand(new TuneVelocity(m_drive, () -> m_GPad.getAxis("LTrigger")));

      // Easy controller switching because I kept forgetting how to get the axis
      if(Constants.controller.useJoystick)
          m_drive.setDefaultCommand(new DriveCommand(m_drive, joystick::getY, joystick::getX));
      else m_drive.setDefaultCommand(new DriveCommand(m_drive, () -> m_GPad.getAxis("LY"), () -> m_GPad.getAxis("LX")));


      configureButtonBindings();
  }

    /**
     * Use this method to define your button->command mappings.  Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
     * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        /* joystick */
        joystick.getButton(2).toggleWhenPressed(new Shift(m_drive, true));
        // Intake
        joystick.getButton(6).whenPressed(m_solUp);
        joystick.getButton(4).whenPressed(m_solDown);

        joystick.getButton(1).whenHeld(m_runIntake);
        joystick.getButton(11).whenHeld(new RunIntakeMotors(m_cargoIntake, () -> 0.5, m_analogSenseor));

        // Shooter (taken out since indexer brokey
        //joystick.getButton(6).whenHeld(m_runShooter);

        // Indexer
        joystick.getButton(5).whenHeld(m_runIndexer);
        joystick.getButton(12).whenHeld(new RunIndexer(m_indexer, () -> -0.5, m_analogSenseor));


        /* Gamepad */

        // Intake
        m_GPad.getButton("B").whileHeld(m_runIntake);
        m_GPad.getButton("X").toggleWhenPressed(new Shift(m_drive, true));
        m_GPad.getButton("RB").whileHeld(new RunIntakeMotors(m_cargoIntake, () -> 0.5, m_analogSenseor));

        m_GPad.getButton("BACK").whenPressed(m_solDown);
        m_GPad.getButton("START").whenPressed(m_solUp);
        // Indexer
        m_GPad.getButton("Y").whileHeld(m_runIndexer);
        m_GPad.getButton("RB").whileHeld(new RunIndexer(m_indexer, () -> -0.5, m_analogSenseor));

        // Shooter
        m_GPad.getButton("A").toggleWhenPressed(m_runShooter);
        m_GPad.getButton("LSB").whenHeld(new VisAlign(m_drive, m_limeLight, () -> true, () -> (Math.abs(m_GPad.getAxis("LX")) > .4), () -> m_GPad.getAxis("LY")));

    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return The command to run in autonomous.
     */
    public Command getAutonomousCommand() {
        //Returns the "auto" command, which we want to run in autonomous.
        return new MotionProfileCommand(m_drive, "5ball", true);
    }
}
