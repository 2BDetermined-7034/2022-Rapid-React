// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.prototypes.intake.RunIntakeMotors;
import frc.robot.commands.prototypes.shooters.ShootBallsHooded;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.prototypes.CargoIntake;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.util.Timer;
import java.util.TimerTask;

import frc.robot.controllers.*;
import frc.robot.subsystems.prototypes.HoodedShooter;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  Timer timer = new Timer();
  private final GPad controller = new GPad(Constants.controllers.gamePadPort);
  // The robot's subsystems and commands are defined here...

  private final HoodedShooter m_hoodedShooter = new HoodedShooter();
  private final ShootBallsHooded m_shootBallsHooded = new ShootBallsHooded(m_hoodedShooter);

  private final CargoIntake m_cargoIntake = new CargoIntake();
  private final RunIntakeMotors m_runIntake = new RunIntakeMotors(m_cargoIntake, () -> 0.5);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    SmartDashboard.putData("Run Balls", new ShootBallsHooded(m_hoodedShooter));
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        SmartDashboard.putNumber("Battery Voltage", RobotController.getBatteryVoltage());
      }
    }, 0, 1000);
    controller.getButton("A").whenHeld(m_shootBallsHooded);
    controller.getButton("B").whenHeld(m_runIntake);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
