// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import javax.annotation.ParametersAreNonnullByDefault;

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

    public static final class driveBase {
        // Odometry
        public static double startX = 0;
        public static double startY = 0;
        public static double length = 0;
        public static double width = 0;
        public static double highRatio = 0;
        public static double lowRatio = 1/15.32;
        public static final boolean HIGH_GEAR = true;
        public static final boolean LOW_GEAR = false;
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

    public final static class shooter {

        public static final int shooterTalonID1 = 6;
        public static final int shooterTalonID2 = 5;
        public static final int shooterTalonID3 = 4;
        public static final int shooterMultiplier = 1;
    }

    public final static class intake {
        public static final int intakeTalon = 1;
        public static final int solenoidForward = 1;
        public static final int solenoidReverse = 2;
        public static final double speed = 0.5;
        public static boolean solenoid_TRUE = true;
        public static boolean solenoid_FALSE = false;
    }

    public final static class indexer {
        public static final int indexerNEO1 = 1;
        public static final int indexerNEO2 = 2;
    }

    public static final class motion {
        //Ramsete gains
        public static double b = 2.1;
        public static double zeta = 0.8;
        //Max values
        public static double maxAcceleration = 1;
        public static double maxVelocity = 1;
        // Distance PID
        public static double distancekP = 3;
        public static double distancekI = 0.5;
        public static double distancekD = 0.0;
        //Heading PID
        public static double headingkP = 2;
        public static double headingkI = 0.5;
        public static double headingkD = 0.0;
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
