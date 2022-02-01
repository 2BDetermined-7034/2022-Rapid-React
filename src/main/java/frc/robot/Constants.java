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
        public static final int gamePadPort = 0;
        public static final int joystickPort = 1;
        public static final int buttonPadPort = 2;
    }

    public static final class pneumatics {
        public static PneumaticsModuleType compressor = PneumaticsModuleType.CTREPCM;
        public static PneumaticsModuleType shifter = PneumaticsModuleType.CTREPCM;
        public static PneumaticsModuleType intake = PneumaticsModuleType.CTREPCM;
    }

    public static final class driveBase {
        // Odometry
        public static double length = 0.762; // Distance from
        public static double width = 0.7112; //Length from left wheel to right
        public static double highRatio = 0;
        public static double lowRatio = 1/15.32;
        public static double meterRatio = 0.31918581;
        public static final boolean HIGH_GEAR = true;
        public static final boolean LOW_GEAR = false;
        //PID
        public static final double leftFF = 1;
        public static final double leftP = 0;
        public static final double leftI = 0;
        public static final double leftD = 0;

        public static final double rightFF = 1;
        public static final double rightP = 0;
        public static final double rightI = 0;
        public static final double rightD = 0;
        // Left
        public static int driveL1ID = 4;
        public static int driveL2ID = 9;
        public static int driveL3ID = 42;
        // Right
        public static int driveR1ID = 3;
        public static int driveR2ID = 1;
        public static int driveR3ID = 7;
        //Shifter
        public static int shifterID = 0;
        // Speed
        public static final double xSpeed = 0.6;
        public static final double xRot = 0.5;

    }
    public static final class motion {
        //Ramsete gains
        public static double b = 2.1;
        public static double zeta = 0.8;
        //Max values
        public static double maxVelocity = 1;
        public static double maxAcceleration = 1;
    }

    public final static class shooter {

        public static final int leftShooterNEO = 2;
        public static final int rightShooterNEO = 10;
        public static final int shooterMultiplier = 1;
    }

    public final static class intake {
        public static final int intakeTalon = 1;
        public static final int solenoidForward = 1;
        public static final int solenoidReverse = 2;
        public static final double speed = 0.5;
        public static boolean solenoid_TRUE = true;
        public static boolean solenoid_FALSE = false;
        public static int IDcompressor = 0;
    }

    public final static class indexer {
        public static final int indexerTalon = 1;
    }

    public static final class vision {

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
        public static double Vis_LLAngle = 20.5;
    }
}
