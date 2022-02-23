// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.auto.TestPathAuto;
import frc.robot.commands.drive.*;
import frc.robot.commands.indexer.*;
import frc.robot.commands.sensor.SensorOverride;
import frc.robot.controllers.*;
import frc.robot.subsystems.*;
import frc.robot.commands.intake.*;
import frc.robot.commands.shooter.*;
import frc.robot.commands.vision.*;
import frc.robot.commands.climb.*;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    // Controllers
    private final X3D joystick = new X3D(2);
    public final GPad m_GPad = new GPad(Constants.controller.gamePadPort);
    public final X3D joystick2 = new X3D(Constants.controller.climbGamePadPort);
    public final GPad climbPad = new GPad(4);

    // Subsystems
    private final CargoIntake m_cargoIntake = new CargoIntake(); // Intake
    private final Indexer m_indexer = new Indexer(); // Indexer
    private final AnalogSensor m_analogSenseor = new AnalogSensor(); // Color sensor
    private final LimeLight m_limeLight = new LimeLight(); // Limelight
    private final Drive m_drive = new Drive(); // Drivebase
    private final Shooter m_shooter = new Shooter(); // Shooter
    private final AutoShoot m_autoShoot = new AutoShoot(m_analogSenseor, m_indexer, m_limeLight, m_shooter);

    // Commands

    /* Shooter */
    private final RunShooter m_runShooter = new RunShooter(m_shooter, m_indexer, () -> Constants.shooter.speed);

    /* Indexer */
    private final RunIndexer m_runIndexer = new RunIndexer(m_indexer, () -> Constants.indexer.speed, m_analogSenseor);
    private final SensorOverride m_sensorOverride = new SensorOverride(m_analogSenseor);

    /* Intake */
    private final RunIntakeMotors m_runIntake = new RunIntakeMotors(m_cargoIntake,  () -> Constants.intake.speed, m_analogSenseor);
    private final SolenoidToggle m_intakeSolToggle = new SolenoidToggle(m_cargoIntake);
    private final Solenoid m_solUp = new Solenoid(m_cargoIntake, true);
    private final Solenoid m_solDown = new Solenoid(m_cargoIntake, false);

    /* Climber */
    public final Climber m_climber = new Climber();
    public final RunWinch m_runWinch = new RunWinch(m_climber, () -> 0.7);
    public final RunWinch m_runWinchBack = new RunWinch(m_climber, () -> -0.7);
    public final RunSolenoidToggle m_toggleClimbSolenoid = new RunSolenoidToggle(m_climber);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
      SmartDashboard.putData("Indexer Sensor Override", m_sensorOverride);
      SmartDashboard.putData("Solenoid Down", m_solDown);
      SmartDashboard.putData("Solenoid Up", m_solUp);
      SmartDashboard.putData("Reset Climb Encoder", new ResetWinchEncoder(m_climber));
      // Register
      m_drive.register();
      m_cargoIntake.register();
      m_climber.register();

      //m_climber.setDefaultCommand(new RunWinch(m_climber, () -> -joystick2.getY()));

      //SmartDashboard.putBoolean("switched", joystick.getRawButtonPressed(3));

      //m_drive.setDefaultCommand(new TuneVelocity(m_drive, () -> m_GPad.getAxis("LTrigger")));

      if(Constants.controller.useJoystick)
          m_drive.setDefaultCommand(new DriveCommand(m_drive, joystick::getY, joystick::getX));
      else
          m_drive.setDefaultCommand(new DriveCommand(m_drive, () -> m_GPad.getAxis("LY"), () -> m_GPad.getAxis("RX")));

      configureButtonBindings();
  }

    /**
     * Use this method to define your button->command mappings.  Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
     * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {

        // Climber controller

        /* Joystick */
        joystick2.getButton(2).whileHeld(m_toggleClimbSolenoid);
        joystick2.getButton(5).whenHeld(m_runWinch);
        joystick2.getButton(3).whenHeld(m_runWinchBack);
        /* Gamepad */
        climbPad.getButton("RB").whenHeld(m_runWinch);
        climbPad.getButton("LB").whenHeld(m_runWinchBack);
        climbPad.getButton("A").whenPressed(m_toggleClimbSolenoid);

        //joystick2.getButton(5).whenHeld(m_runWinch);
        //joystick2.getButton(3).whenHeld(m_runWinchBack);

       // Main Joystick
        joystick.getButton(2).toggleWhenPressed(new Shift(m_drive, true));
        joystick.getButton(6).toggleWhenPressed(m_intakeSolToggle);
        joystick.getButton(1).whenHeld(m_runIntake);
        joystick.getButton(1).whenHeld(m_runIndexer);
        joystick.getButton(11).whenHeld(new RunIntakeMotors(m_cargoIntake, () -> Constants.intake.speed, m_analogSenseor));
        //joystick.getButton(6).toggleWhenPressed(m_runShooter);
        //joystick.getButton(5).whenHeld(m_runIndexer);
        //joystick.getButton(12).whenHeld(new RunIndexer(m_indexer, () -> -Constants.indexer.speed, m_analogSenseor));
        joystick.getButton(3).whenHeld(new VisAlign(m_drive, m_limeLight, () -> true, () -> (Math.abs(m_GPad.getAxis("LX")) > .4), () -> m_GPad.getAxis("LY")));
        joystick.getButton(5).whenHeld(m_autoShoot);
        joystick.getButton(7).whenHeld(m_runShooter);
        joystick.getButton(9).whenHeld(new RunIntakeMotors(m_cargoIntake, () -> -Constants.intake.speed, m_analogSenseor));
        joystick.getButton(9).whenHeld(new RunIndexer(m_indexer, () -> -Constants.indexer.speed, m_analogSenseor));
        // Main Gamepad (Max configs)

        /* Intake */
        m_GPad.getButton("RB").whileHeld(m_runIntake);
        m_GPad.getButton("BACK").whenPressed(m_solDown);
        m_GPad.getButton("START").whenPressed(m_solUp);
        m_GPad.getButton("LB").whileHeld(new RunIntakeMotors(m_cargoIntake, () -> -Constants.intake.speed, m_analogSenseor));

        /* Drive */
        m_GPad.getButton("A").toggleWhenPressed(new Shift(m_drive, true));

        /* Indexer */
        m_GPad.getButton("RB").whileHeld(m_runIndexer);
        m_GPad.getButton("LB").whileHeld(new RunIndexer(m_indexer, () -> -Constants.indexer.speed, m_analogSenseor));

        /* Shooter */
        //m_GPad.getButton("A").toggleWhenPressed(m_runShooter);
        m_GPad.getButton("X").whenHeld(new VisAlign(m_drive, m_limeLight, () -> true, () -> (Math.abs(m_GPad.getAxis("LX")) > .4), () -> m_GPad.getAxis("LY")));
        m_GPad.getButton("Y").whenHeld(m_autoShoot);


    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return The command to run in autonomous.
     */
    public Command getAutonomousCommand() {
        //Returns the "auto" command, which we want to run in autonomous.
        return new TestPathAuto(m_drive, m_indexer, m_analogSenseor, m_cargoIntake);
    }
}
