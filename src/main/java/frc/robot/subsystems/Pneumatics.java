/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.hal.CompressorJNI;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Pneumatics extends SubsystemBase {

    public Compressor comp;

    public Pneumatics() {
        comp = new Compressor(0);
        comp.start();
    }

    public boolean setCompressor(boolean on) {
        if (on) { comp.start(); }
        else { comp.stop(); }
        return comp.enabled();
      }

    public boolean toggleCompressor() { return setCompressor(!comp.enabled()); }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}