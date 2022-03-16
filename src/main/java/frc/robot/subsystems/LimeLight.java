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
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.limelight.CamMode;
import frc.robot.Constants;

public class LimeLight extends SubsystemBase {

  private final NetworkTable m_table;

  public LimeLight() {
    m_table = NetworkTableInstance.getDefault().getTable("limelight");
    setLights(false);
  }

  public double getXAngle() { return m_table.getEntry("tx").getDouble(0); }
  public double getYAngle() { return m_table.getEntry("ty").getDouble(0); }
  public double getArea() { return m_table.getEntry("ta").getDouble(0); }
  public double getShortSide() { return m_table.getEntry("tshort").getDouble(0); }
  public double getLongSide() { return m_table.getEntry("tlong").getDouble(0); }
  public boolean getDetected() { return m_table.getEntry("tv").getDouble(0) > 0; }
  public double getLastDetected() { return m_table.getEntry("tv").getLastChange(); }

  public CamMode getMode() {
    return CamMode.getFromNetworkTableValue(m_table.getEntry("camMode").getNumber(0).intValue());
  }

  public void setMode(CamMode camMode) {
    m_table.getEntry("camMode").setNumber(camMode.getNetworkTableValue());
  }

  public void setLights(boolean on) {
    m_table.getEntry("ledMode").setNumber(on ? 3 : 1);
  }

  public double getEstimatedDistance() {
    return 3.494*Math.pow(Math.tan(Math.toRadians(Constants.vision.Vis_LLAngle+getYAngle())), -1);
  }

  @Override
  public void periodic() {
    if(getCurrentCommand() == null) {
      setLights(true);
    }
    SmartDashboard.putNumber(getName() + " X Angle", getXAngle());
    SmartDashboard.putNumber(getName() + " Y Angle", getYAngle());
    SmartDashboard.putNumber(getName() + " EQ pow 1", getYAngle()*.00605);
    SmartDashboard.putNumber(getName() + " EQ pow 2", Math.pow(getYAngle(), 2)*.00146);
    SmartDashboard.putNumber(getName() + " EQ pow 3", Math.pow(getYAngle(), 3)*.000348);
    //SmartDashboard.putBoolean(getName() + " Detected", getDetected());
    SmartDashboard.putNumber(getName() + " Distance", getEstimatedDistance());
  }
}