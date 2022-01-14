/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drive;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.*;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;


public class DriveTrain extends SubsystemBase {
  public WPI_TalonSRX talonL;
  public WPI_TalonSRX talonL2;

  SpeedControllerGroup left;


  public WPI_TalonSRX talonR;
  public WPI_TalonSRX talonR2;

  SpeedControllerGroup right;

  public DifferentialDrive diffDrive;

  public BaseMotorController test;

  
  /**
   * Creates an instance of the robot's drivetrain.
   */
  public DriveTrain() {

    // Left
    talonL = new WPI_TalonSRX(Constants.driveBase.leftTalon1);
    talonL2 = new WPI_TalonSRX(Constants.driveBase.leftTalon2);
    // Right
    talonR = new WPI_TalonSRX(Constants.driveBase.rightTalon1);
    talonR2 = new WPI_TalonSRX(Constants.driveBase.rightTalon2);

    left = new SpeedControllerGroup(talonL, talonL2);
    right = new SpeedControllerGroup(talonR, talonR2);

    //Creates a differentialDrive with the left and right motors.
    //The differntialDrive is what drives the robot.
    diffDrive = new DifferentialDrive(left, right);

    //Gotta be coastin on god on god%
    talonL.setNeutralMode(NeutralMode.Coast);
    talonL2.setNeutralMode(NeutralMode.Coast);
    talonR.setNeutralMode(NeutralMode.Coast);
    talonR2.setNeutralMode(NeutralMode.Coast);
  }

  /**
   * Drives the robot at the desired speed and with the desired rotation.
   * Speed and rotation are affected by a modifier in Constants.java (check Driving section).
   * 
   * @param speed Speed of the motor(s)
   * @param rot Rotation speed (left and right)
   */
  public void drive(double speed, double rot){
    diffDrive.arcadeDrive(speed * Constants.driveBase.xSpeed, rot * Constants.driveBase.xRot);
  }




  @Override
  public void periodic() {}
}