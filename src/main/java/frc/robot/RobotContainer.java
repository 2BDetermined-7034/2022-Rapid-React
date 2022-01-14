/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;

import frc.robot.commands.Drive.Drive;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.controllers.*;
import frc.robot.subsystems.drive.DriveTrain;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  //The robot's subsystems and commands are defined here

  //Defines the gamepad and/or joystick (X3D is the joystick) for use.

  //USB port number is defined in Constants.java under "gamepadPort" or "x3DPort" respectively. 
  //Find the correct port in the driver station under in the tab with the USB icon.

  public final gPad m_gPad = new gPad(Constants.controller.gamePadPort); // Gamepad
 // public final X3D m_X3D = new X3D(Constants.x3DPort); // Joystick


  //Declaration of subsystems.
  private final DriveTrain driveTrain = new DriveTrain(); // Robot drivetrain

  //Declaration of commands.

  //Driving command.
  //Passes in the left joystick's X axis as rotation and the left joystick's Y axis as speed.
  private final Drive driveCommand = new Drive(driveTrain, () -> m_gPad.getAxis("LY"), () -> m_gPad.getAxis("LX"), m_gPad);

  private final Drive autoCommand = new Drive(driveTrain, () -> 0, () -> 0.7, m_gPad);
  //private final Brake brake = new Brake(driveTrain);

  

  // The container for the robot. Contains subsystems, OI devices, and commands.
  public RobotContainer() {
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    driveTrain.setDefaultCommand(driveCommand); //Sets the drivetrain to always be running the "driveCommand" command.
 
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return The command to run in autonomous.
   */
  public Command getAutonomousCommand() {
    //Returns the "auto" command, which we want to run in autonomous.
    return autoCommand;
  }
}
