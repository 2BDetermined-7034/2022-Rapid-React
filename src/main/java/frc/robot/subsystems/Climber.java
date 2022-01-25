// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
    private final CANSparkMax m_winchNeo;
    private final Solenoid m_solenoid;

    /**
     * Creates a new TeleClimb
     */
    public Climber() {
        m_winchNeo = new CANSparkMax(Constants.climb.winchMotorID, CANSparkMaxLowLevel.MotorType.kBrushless);
        m_winchNeo.setIdleMode(CANSparkMax.IdleMode.kBrake);

        m_solenoid = new Solenoid(Constants.climb.solenoidID);


        //Putting smartdashboard stuff
        SmartDashboard.putNumber("TeleClimberWinchSpeed", 0);
    }

    public void runWinch(double speed){
        m_winchNeo.set(speed);
    }
    public void setSolenoid(boolean broken) {
        m_solenoid.set(broken);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }
}
