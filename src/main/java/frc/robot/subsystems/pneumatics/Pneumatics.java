/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.pneumatics;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Pneumatics extends SubsystemBase {
    public boolean isON;
    public Compressor comp;

    public Pneumatics() {
        comp = new Compressor(Constants.intake.IDcompressor, PneumaticsModuleType.CTREPCM);
    }

    public boolean setCompressor(boolean on) {
        this.isON = on;
        if (on) { comp.enabled(); }
        else { comp.disable(); }
        return comp.enabled();
    }

    public boolean toggleCompressor() {

        return setCompressor(!comp.enabled());
    }

    public void debug() {
        SmartDashboard.putBoolean("Is Compressor On?", isON);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}