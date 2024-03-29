// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.auto.*;
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

import java.util.List;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    // Controllers
    private final X3D joystick = new X3D(Constants.controller.driveJoystick);

    public final GPad m_GPad = new GPad(Constants.controller.gamePadPort);
    public final GPad climbPad = new GPad(Constants.controller.climbGamePadPort);


    SendableChooser<Command> m_chooser;

    // Subsystems
    private final CargoIntake m_cargoIntake = new CargoIntake(); // Intake
    private final Indexer m_indexer = new Indexer(); // Indexer
    private final DigitalSensor m_analogSenseor = new DigitalSensor(); // Color sensor
    private final LimeLight m_limeLight = new LimeLight(); // Limelight
    private final Drive m_drive = new Drive(); // Drivebase
    private final Shooter m_shooter = new Shooter(); // Shooter
    private final TrollShot m_trollShot = new TrollShot(m_shooter, m_indexer, m_analogSenseor);
    private final LaunchShot m_launch = new LaunchShot(m_shooter, m_indexer, m_analogSenseor);
    // Commands

    /* Shooter */
    private final RunShooter m_runShooter = new RunShooter(m_shooter, m_indexer, () -> Constants.shooter.speed, m_analogSenseor);

    /* Indexer */
    //private final RunIndexer m_runIndexer = new RunIndexer(m_indexer, () -> Constants.indexer.speed, m_analogSenseor, () -> climbPad.getButton("A").getAsBoolean());
    private final SensorOverride m_sensorOverride = new SensorOverride(m_analogSenseor);

    /* Intake */
    //private final RunIntakeMotors m_runIntake = new RunIntakeMotors(m_cargoIntake,  () -> Constants.intake.speed, m_analogSenseor, () -> climbPad.getButton("A").getAsBoolean());
    private final SolenoidToggle m_intakeSolToggle = new SolenoidToggle(m_cargoIntake);
    private final Solenoid m_intakeup = new Solenoid(m_cargoIntake, false);

    private final EjectBot m_ejectBot = new EjectBot(m_shooter, m_indexer, m_cargoIntake, m_analogSenseor);
    private final EjectTop m_ejectTop = new EjectTop(m_shooter, m_indexer, m_analogSenseor);

    /* Climber */
    public final Climber m_climber = new Climber();
    public final RunSolenoid m_toggleClimbSolenoid = new RunSolenoid(m_climber);

    public final AddCorrect addCorrect = new AddCorrect();
    public final SubCorrect subCorrect = new SubCorrect();
    public final ResetCorrect resetCorrect = new ResetCorrect();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
      // SmartDashboard Data
      SmartDashboard.putData("Indexer Sensor Override", m_sensorOverride);
      SmartDashboard.putData("Intake Solenoid", m_intakeSolToggle);
      SmartDashboard.putData("Climb Solenoid", m_toggleClimbSolenoid);
      SmartDashboard.putData("Eject Top", m_ejectTop);
      SmartDashboard.putData("Eject Bot", m_ejectBot);

      SmartDashboard.putData("Add", addCorrect);
      SmartDashboard.putData("Subtract", subCorrect);
      SmartDashboard.putData("Reset", resetCorrect);

      // Register
      m_drive.register();
      m_cargoIntake.register();
      m_climber.register();
      m_shooter.register();
      m_analogSenseor.register();

      //Auto
      m_chooser = new SendableChooser<>();

      m_chooser.setDefaultOption("Four Ball", new FourBall(m_drive, m_limeLight, m_shooter, m_indexer, m_analogSenseor, m_cargoIntake));
      m_chooser.addOption("Five Ball", new FiveBall(m_drive, m_limeLight, m_shooter, m_indexer, m_analogSenseor, m_cargoIntake));
      m_chooser.addOption("Six Ball", new SixBall(m_drive, m_limeLight, m_shooter, m_indexer, m_analogSenseor, m_cargoIntake));
      m_chooser.addOption("Two Ball Bot", new TwoBallBot(m_drive, m_limeLight, m_shooter, m_indexer, m_analogSenseor, m_cargoIntake));
      m_chooser.addOption("Two Ball Back", new TwoBallBack(m_drive, m_limeLight, m_shooter, m_indexer, m_analogSenseor, m_cargoIntake));

      m_chooser.addOption("Three Ball", new ThreeBall(m_drive, m_limeLight, m_shooter, m_indexer, m_analogSenseor, m_cargoIntake));
      m_chooser.addOption("TwoBallD1", new DefensiveTwoBall(m_drive, m_limeLight, m_shooter, m_indexer, m_analogSenseor, m_cargoIntake));
      m_chooser.addOption("ThreeBallD1", new DefensiveThreeBall(m_drive, m_limeLight, m_shooter, m_indexer, m_analogSenseor, m_cargoIntake));
      m_chooser.addOption("TwoBallD1Hanger", new DefensiveTwoBallHanger(m_drive, m_limeLight, m_shooter, m_indexer, m_analogSenseor, m_cargoIntake));

      SmartDashboard.putData("Auto",m_chooser);

      // Drive default command
      if(Constants.controller.useJoystick)
          m_drive.setDefaultCommand(new DriveCommand(m_drive, joystick::getY, joystick::getX)); // Looks wrong do not change
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

        climbPad.getButton("RB").whenHeld(new RunWinch(m_climber, () -> Constants.climb.winchSpeed, ()-> climbPad.getAxis("LTrigger"), () -> climbPad.getAxis("RTrigger")));
        climbPad.getButton("LB").whenHeld(new RunWinch(m_climber, () -> -Constants.climb.winchSpeed * 0.85, () -> climbPad.getAxis("LTrigger"), () -> climbPad.getAxis("RTrigger")));
        climbPad.getButton("B").toggleWhenPressed(m_toggleClimbSolenoid);
        climbPad.getButton("Y").whenPressed(m_intakeup);
        //climbPad.getButton("X").whenPressed(new AutoClimbGroup(m_climber, () -> true, m_cargoIntake));
        //climbPad.getButton("X").whenHeld(new AutoClimbGroup(m_climber, () -> false, m_cargoIntake));
        climbPad.getButton("BACK").whenHeld(m_ejectBot);
        climbPad.getButton("START").whenHeld(m_ejectTop);

        joystick.getButton(1).whenHeld(new RunIntakeMotors(m_cargoIntake,  () -> Constants.intake.speed, m_analogSenseor, () -> climbPad.getButton("A").get()));
        joystick.getButton(1).whenHeld(new RunIndexer(m_indexer, m_shooter, () -> Constants.indexer.speed, m_analogSenseor, () -> climbPad.getButton("A").get()));
        joystick.getButton(2).whenPressed(m_intakeSolToggle);
        joystick.getButton(3).whenHeld(new VisShoot(m_drive, m_limeLight, m_analogSenseor, m_indexer, m_shooter, () -> true,  () -> (Math.abs(m_GPad.getAxis("LX")) > .4), () -> m_GPad.getAxis("LY")));
        joystick.getButton(4).toggleWhenPressed(new Shift(m_drive, true));
        joystick.getButton(5).whenHeld(m_launch);
        joystick.getButton(8).whenHeld(m_trollShot);
        joystick.getButton(9).whenHeld(new RunWinch(m_climber, () -> -Constants.climb.winchSpeed, () -> climbPad.getAxis("LTrigger"), () -> climbPad.getAxis("RTrigger")));
        joystick.getButton(10).whenHeld(new RunWinch(m_climber, () -> Constants.climb.winchSpeed, ()-> climbPad.getAxis("LTrigger"), () -> climbPad.getAxis("RTrigger")));

    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return The command to run in autonomous.
     */
    public Command getAutonomousCommand() {
        return m_chooser.getSelected();

    }
}
