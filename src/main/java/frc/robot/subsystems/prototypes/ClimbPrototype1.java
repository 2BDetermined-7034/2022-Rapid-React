// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.prototypes;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimbPrototype1 extends SubsystemBase {
    public final WPI_TalonSRX m_deployMotor;
    public final WPI_TalonSRX m_winchMotor;

    public ClimbPrototype1() {
        m_deployMotor = new WPI_TalonSRX(Constants.climbDriverMotorID);
        m_winchMotor = new WPI_TalonSRX(Constants.climbWinchMotorID);
    }

    public void runDriver(double speed){
        m_deployMotor.set(speed);
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
