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

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.drive.DriveTrain;
import frc.robot.controllers.*;

public class Drive extends CommandBase {
    private final DriveTrain dT;
    private final DoubleSupplier yDoubleSup;
    private final DoubleSupplier xDoubleSup;

    public final gPad pad;
    private final DriveTrain driveTrain = new DriveTrain();
    public final gPad gPad = new gPad(Constants.controller.gamePadPort);

    /**
     * Command will drive the robot's drivetrain.
     *
     * @param driveTrain The robot's drivetrain subsystem.
     * @param rotation   Rotation DoubleSupplier.
     * @param speed      Speed DoubleSupplier.
     * @param gamePad    The gamepad being used for input.
     */
    public Drive(DriveTrain driveTrain, DoubleSupplier rotation, DoubleSupplier speed, gPad gamePad) {
        dT = driveTrain;
        xDoubleSup = speed;
        yDoubleSup = rotation;
        pad = gamePad;

        addRequirements(dT);
    }

    @Override
    public void initialize() {
    }

    //The execute method will run every 20ms while the command is active.
    @Override
    public void execute() {
        double rot = yDoubleSup.getAsDouble();
        double speed = xDoubleSup.getAsDouble();

        SmartDashboard.putData("Run Motors", new Drive(driveTrain, () -> 0.6, () -> 0, gPad));
        dT.drive(-speed, rot);


    }

    @Override
    public void end(boolean interrupted) {
        dT.drive(0, 0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
