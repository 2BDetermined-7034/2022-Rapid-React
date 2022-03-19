// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.PathPlanner;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.TrajectoryParameterizer;
import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
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
    public final X3D climbJoystick = new X3D(Constants.controller.climbGamePadPort);

    public final GPad m_GPad = new GPad(Constants.controller.gamePadPort);
    public final GPad climbPad = new GPad(Constants.controller.climbGamePadPort);

    // Subsystems
    private final CargoIntake m_cargoIntake = new CargoIntake(); // Intake
    private final Indexer m_indexer = new Indexer(); // Indexer
    private final DigitalSensor m_analogSenseor = new DigitalSensor(); // Color sensor
    private final LimeLight m_limeLight = new LimeLight(); // Limelight
    private final Drive m_drive = new Drive(); // Drivebase
    private final Shooter m_shooter = new Shooter(); // Shooter
    //private final AutoShoot m_autoShoot = new AutoShoot(m_analogSenseor, m_indexer, m_limeLight, m_shooter);
    private final TrollShot m_trollShot = new TrollShot(m_shooter, m_indexer, m_analogSenseor);
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
    private final Solenoid m_solUp = new Solenoid(m_cargoIntake, true);
    private final Solenoid m_solDown = new Solenoid(m_cargoIntake, false);

    private final EjectBot m_ejectBot = new EjectBot(m_shooter, m_indexer, m_cargoIntake, m_analogSenseor);
    private final EjectTop m_ejectTop = new EjectTop(m_shooter, m_indexer, m_analogSenseor);

    /* Climber */
    public final Climber m_climber = new Climber();
    //public final RunWinch m_runWinch = new RunWinch(m_climber, () -> 0.9, ()-> m_GPad.getAxis("LTrigger"));
    //public final RunWinch m_runWinchBack = new RunWinch(m_climber, () -> -0.9, () -> m_GPad.getAxis("LTrigger"));
    public final RunSolenoid m_toggleClimbSolenoid = new RunSolenoid(m_climber);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
      // SmartDashboard Data
      SmartDashboard.putData("Indexer Sensor Override", m_sensorOverride);
      SmartDashboard.putData("Intake Solenoid", m_intakeSolToggle);
      SmartDashboard.putData("Climb Solenoid", m_toggleClimbSolenoid);
      SmartDashboard.putData("Eject Top", m_ejectTop);
      SmartDashboard.putData("Eject Bot", m_ejectBot);
      SmartDashboard.putNumber("Bruh", 0);


      //SmartDashboard.putData("Reset Climb Encoder", new ResetWinchEncoder(m_climber));

      // Register
      m_drive.register();
      m_cargoIntake.register();
      m_climber.register();
      m_shooter.register();
      m_analogSenseor.register();


      // Drive default command
      if(Constants.controller.useJoystick)
          m_drive.setDefaultCommand(new DriveCommand(m_drive, joystick::getY, joystick::getX)); // Looks wrong do not change
      else
          m_drive.setDefaultCommand(new DriveCommand(m_drive, () -> m_GPad.getAxis("LY"), () -> m_GPad.getAxis("RX")));

      //Note: Might need to tweak the division
      //m_climber.setDefaultCommand(new DriveCommand(m_drive, () -> -climbPad.getAxis("LY")/3, () -> - climbPad.getAxis("LX")/3));

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

        /* Gamepad */
        climbPad.getButton("RB").whenHeld(new RunWinch(m_climber, () -> 0.9, ()-> climbPad.getAxis("LTrigger"), () -> climbPad.getAxis("RTrigger")));
        climbPad.getButton("LB").whenHeld(new RunWinch(m_climber, () -> -0.9, () -> climbPad.getAxis("LTrigger"), () -> climbPad.getAxis("RTrigger")));
        climbPad.getButton("B").toggleWhenPressed(m_toggleClimbSolenoid);
        climbPad.getButton("Y").whenPressed(m_intakeup);
        //climbPad.getButton("X").whenPressed(new AutoClimbGroup(m_climber, () -> true, m_cargoIntake));
        //climbPad.getButton("X").whenHeld(new AutoClimbGroup(m_climber, () -> false, m_cargoIntake));
        climbPad.getButton("BACK").whenHeld(m_ejectBot);
        climbPad.getButton("START").whenHeld(m_ejectTop);


       // Main Joystick ( configs )
        joystick.getButton(1).whenHeld(new RunIntakeMotors(m_cargoIntake,  () -> Constants.intake.speed, m_analogSenseor, () -> climbPad.getButton("A").get()));
        joystick.getButton(1).whenHeld(new RunIndexer(m_indexer, () -> Constants.indexer.speed, m_analogSenseor, () -> climbPad.getButton("A").get()));
        joystick.getButton(2).whenPressed(m_intakeSolToggle);
        //joystick.getButton(3).whenHeld(new VisAlign(m_drive, m_limeLight, () -> true, () -> (Math.abs(m_GPad.getAxis("LX")) > .4), () -> m_GPad.getAxis("LY")));
        joystick.getButton(3).whenHeld(new VisShoot(m_drive, m_limeLight, m_analogSenseor, m_indexer, m_shooter, () -> true,  () -> (Math.abs(m_GPad.getAxis("LX")) > .4), () -> m_GPad.getAxis("LY")));

        joystick.getButton(4).toggleWhenPressed(new Shift(m_drive, true));

        joystick.getButton(8).whenHeld(m_trollShot);
        joystick.getButton(9).whenHeld(new RunWinch(m_climber, () -> -0.9, () -> climbPad.getAxis("LTrigger"), () -> climbPad.getAxis("RTrigger")));
        joystick.getButton(10).whenHeld(new RunWinch(m_climber, () -> 0.9, ()-> climbPad.getAxis("LTrigger"), () -> climbPad.getAxis("RTrigger")));


    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return The command to run in autonomous.
     */
    public Command getAutonomousCommand() {

        switch (Constants.controller.autoNumber) {
            case 1:
                return new TwoBallMid(m_drive, m_limeLight, m_shooter, m_indexer, m_analogSenseor, m_cargoIntake);
            case 2:
                return new TwoBallBot(m_drive, m_limeLight, m_shooter, m_indexer, m_analogSenseor, m_cargoIntake);
            case 3:
                return new ThreeBall(m_drive, m_limeLight, m_shooter, m_indexer, m_analogSenseor, m_cargoIntake);
            case 4:
                return new FourBall(m_drive, m_limeLight, m_shooter, m_indexer, m_analogSenseor, m_cargoIntake);
            default:
                throw new IllegalStateException("Unexpected Auto: " + Constants.controller.autoNumber);
        }



    }
}
