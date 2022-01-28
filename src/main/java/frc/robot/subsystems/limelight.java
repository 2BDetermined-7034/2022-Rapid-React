/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class limelight extends SubsystemBase {
  /**
   * Creates a new limelight.
   */
  private final NetworkTable m_table;
  public limelight() {
    m_table = NetworkTableInstance.getDefault().getTable("limelight");

  }
  public double getXAngle() { return m_table.getEntry("tx").getDouble(0); }
  public double getYAngle() { return m_table.getEntry("ty").getDouble(0); }
  public double getArea() { return m_table.getEntry("ta").getDouble(0); }
  public double getShortSide() { return m_table.getEntry("tshort").getDouble(0); }
  public double getLongSide() { return m_table.getEntry("tlong").getDouble(0); }
  public boolean getDetected() { return m_table.getEntry("tv").getDouble(0) > 0; }
  public double getLastDetected() { return m_table.getEntry("tv").getLastChange(); }

  public double getEstimatedDistance() {
    return 3.845*Math.pow(Math.tan(Math.toRadians(getYAngle())), -1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber(getName() + " X Angle", getXAngle());
    SmartDashboard.putNumber(getName() + " Y Angle", getYAngle());
    SmartDashboard.putBoolean(getName() + " Detected", getDetected());
    SmartDashboard.putNumber(getName() + " Distance", getEstimatedDistance());
  }
}
