// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.prototypes;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

// Climb code assumes NEOs are being used for the winch and driver motors

public class ClimbPrototype1 extends SubsystemBase {
    public final CANSparkMax m_deployMotor;
    public final CANSparkMax m_winchMotor;

    public ClimbPrototype1() {
        m_deployMotor = new CANSparkMax(Constants.climber.driverMotorID, CANSparkMaxLowLevel.MotorType.kBrushless);
        m_winchMotor = new CANSparkMax(Constants.climber.winchMotorID, CANSparkMaxLowLevel.MotorType.kBrushless);
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
