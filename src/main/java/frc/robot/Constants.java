// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.PneumaticsModuleType;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */

public final class Constants {
/*
    public final static class limelight {
        public static double Vis_LLAngle = 20.5;
    }
*/
    public final static class controller {
        public static final int driveJoystick = 2;
        public static final int climbGamePadPort = 3;
        public static final int gamePadPort = 0;
        public static final int climberGamepad = 6;
        public static final int buttonPadPort = 2;
        public static final boolean useJoystick = true; // Switch between using gamepad and joystick (for driver)

        public static final double yGate = 0.15;
        public static final double xGate = 0.15;

        public static final int autoNumber = 4;
    }

    public static final class pneumatics {
        public static final PneumaticsModuleType compressor = PneumaticsModuleType.REVPH;
        public static final PneumaticsModuleType shifter = PneumaticsModuleType.REVPH;
        public static final PneumaticsModuleType intake = PneumaticsModuleType.REVPH;
        public static final PneumaticsModuleType climber = PneumaticsModuleType.REVPH;
    }

    public static final class driveBase {
        // Odometry
        public static final double length = 0.762; // Distance from
        public static final double width = 0.7112; //Length from left wheel to right
        public static final double highRatio = 1 / 7.08;
        public static final double lowRatio = 1 / 18.75; // 18.75
        public static final double wheelRatio = 0.3192;
        public static final double gyroOffset = 0;

        //public static final double wheelMeterRatio = (0.31918581/61) * (1.15);
        public static final boolean HIGH_GEAR = false;

        // Left
        public static final int driveL1ID = 4;
        public static final int driveL2ID = 5;
        public static final int driveL3ID = 6;
        // Right
        public static final int driveR1ID = 1;
        public static final int driveR2ID = 2;
        public static final int driveR3ID = 3;
        //Shifter
        public static final int leftShifterID = 1;
        public static final int rightShifterID = 0;
        // Speed
        public static final double xSpeed = .9;
        public static final double xRot = .75;
    }
    public static final class motion {
        //Drivebase tuning
        public static final double ksVolts = 0.16302;
        public static final double kvVoltSecondsPerMeter = 2.8008; //7.2507
        public static final double kaVoltSecondsSquaredPerMeter = 0.36623;
        public static final double kPDriveVel = 0;// 3.5796

        //Ramsete gains
        public static final double b = 2;
        public static final double zeta = 0.7;
        //Max values

        public static final double maxVelocity = 3;
        public static final double maxAcceleration = 2.0;
    }

    public final static class shooter {
        public static final int leftShooterNEO = 8;
        public static final int rightShooterNEO = 11;
        public static final double speed = .25;
        public static final double passiveSpeed = 0; //Passive speed, should be negative
        public static final double shooterRange = 0.005;
        public static final double shootTime = 2.1;

    }

    public final static class climb {
        public static final int winchMotorID = 1;
        public static final int solenoidForwardID = 4;
        public static final int solenoidBackID = 5;
        public static final double winchSpeed = 0.9;
        //PID Values
        public static final double kP = 0.05;
        public static final double kI = 0;
        public static final double kD = 0;
        //Encoder garbage (maybe remove if not being used)

        public static final int maxPos = 227440;

        public static final int extendedValue = 312252;
        public static final int encoderAcceptableError = 10;
    }
    public final static class intake {
        public static final int intakeMotor = 9;
        public static final int intakeMotor2 = 12;
        public static final int solenoidForward = 3;
        public static final int solenoidReverse = 2;
        public static final double speed = 0.4; // 0.4
        public static final double stopSpeed = 0.35; //Speed when wrong color
        public static final boolean intakeUp = true;
        public static final boolean intakeDown = false;
        public static final int IDcompressor = 0;
    }

    public final static class indexer {
        public static final double speed = 0.85; //changed from 85


        public static final int indexerMotor1 = 10;
        public static final int indexerMotor2 = 7;
    }

    public static final class vision {

        public static final int pGain = 16;

        public static final double VisX_kP = 1;
        public static final double VisX_MAX = .3;
        public static final double VisTimeLimit = 5;
        public static final double VisX_Offset = 0;
        public static final double VisX_Tol = 0;
        public static final double VisY_Tol = 5;
        public static final double VisY_distanceConstant = 254.3;
        public static final double VisY_Offset = 8;
        public static final double VisY_VTol = 100;
        public static final double VisX_VTol = 100;
        public static final int Vis_TimerConfidence = 5;
        public static double Vis_LLAngle = 35;
    }
}
