// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.climb.*;
import frc.robot.controllers.GPad;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final TeleClimb m_teleClimb = new TeleClimb();
  private final RaiseClimber m_raiseClimber = new RaiseClimber(m_teleClimb);
  private final RunWinch m_runWinch = new RunWinch(m_teleClimb);

  private final LegoClimb m_legoClimb = new LegoClimb();
  //private final RunLegoWinch m_runLegoWinch = new RunLegoWinch(m_legoClimb);

  private final GPad controller = new GPad(Constants.controllers.gamePadPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    m_legoClimb.register();

    m_legoClimb.setDefaultCommand(new LegoSolenoid(m_legoClimb, () -> controller.getRawButtonPressed(3)));
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    controller.getButton("LT").whileHeld(m_raiseClimber);
    controller.getButton("RT").whileHeld(m_runWinch);
    controller.getButton("A").whileHeld(new RunLegoWinch(m_legoClimb, .4));
    controller.getButton("B").whileHeld(new RunLegoWinch(m_legoClimb, -.4));
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
