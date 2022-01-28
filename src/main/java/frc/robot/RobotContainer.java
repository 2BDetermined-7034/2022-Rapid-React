// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.fasterxml.jackson.databind.ser.impl.ReadOnlyClassToSerializerMap;
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
  private final GPad m_controller = new GPad(Constants.controllers.gamePadPort);

  // The robot's subsystems and commands are defined here...

  //Climber subsystem and commands
  private final Climber m_climber = new Climber();
  private final RunSolenoid m_runSolenoid = new RunSolenoid(m_climber, () -> m_controller.getRawButtonPressed(4));
  private final RunWinch m_runWinchPos = new RunWinch(m_climber, Constants.climb.winchSpeed);
  private final RunWinch m_runWinchNeg = new RunWinch(m_climber, -Constants.climb.winchSpeed);
  private final HookExtendo m_extendHook = new HookExtendo(m_climber, true, () -> m_controller.getRawButtonPressed(5));
  private final HookExtendo m_retractHook = new HookExtendo(m_climber, false, () -> m_controller.getRawButtonPressed(5));

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    m_climber.register();
    m_climber.setDefaultCommand(m_runSolenoid);

    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    m_controller.getButton("A").whileHeld(m_runWinchPos);
    m_controller.getButton("B").whileHeld(m_runWinchNeg);
    m_controller.getButton("X").whileHeld(m_extendHook);
    m_controller.getButton("Y").whileHeld(m_retractHook);

    m_controller.getButton("START").whenPressed(new ResetClimbEncoder(m_climber));
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
