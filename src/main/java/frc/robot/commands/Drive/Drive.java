/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/


//If you guys win bunny bots,
// I will eat a cookie cutter
// - Sam


package frc.robot.commands.Drive;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drive.DriveTrain;
import frc.robot.controllers.*;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;

public class Drive extends CommandBase {
    private final DriveTrain botDriveTrain;
    private final DoubleSupplier m_x;
    private final DoubleSupplier m_y;

    private final gPad controller;

    /**
     * Command will drive the robot's drivetrain.
     *
     * @param driveTrain The robot's drivetrain subsystem.
     * @param rotation   Rotation DoubleSupplier.
     * @param speed      Speed DoubleSupplier.
     * @param gamePad    The gamepad being used for input.
     */
    public Drive(DriveTrain driveTrain, DoubleSupplier rotation, DoubleSupplier speed, gPad gamePad) {
        botDriveTrain = driveTrain;

        m_y = speed;
        m_x = rotation;

        controller = gamePad;

        addRequirements(botDriveTrain);
    }

    @Override
    public void initialize() {
    }

    //The execute method will run every 20ms while the command is active.
    @Override
    public void execute() {
        double rot = m_x.getAsDouble();
        double speed = m_y.getAsDouble();

        //Rumble stuff.
        //Enable/disable rumble in Constants.java under "rumbleEnabled" (Controllers section).
        //Rumble amount is multiplied by "rumbleMultiplier" in Constants.java.
      


        //Drives the robot with the appropriate speed and rotation.
        botDriveTrain.drive(-speed, rot);
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
