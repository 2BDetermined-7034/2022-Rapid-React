// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final class controller {
        public static int gamePadPort = 1;
    }

    public static final class motion {
        //Max values
        public static double maxAcceleration = 1;
        public static double maxVelocity = 1;
        // Distance PID
        public static double distancekP = 0.5;
        public static double distancekI = 0.0;
        public static double distancekD = 0.0;
        //Heading PID
        public static double headingkP = 0.5;
        public static double headingkI = 0.0;
        public static double headingkD = 0.0;

    }

    public static final class driveBase {
        // Odometry
        public static double startX = 0;
        public static double startY = 0;
        public static double length = 0;
        public static double width = 0;
        public static double highRatio = 6.86;
        public static double lowRatio = 0;
        public static final boolean HIGH_GEAR = false;
        public static final boolean LOW_GEAR = true;
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
        public static final double xSpeed = 0.7;
        public static final double xRot = 0.6;

    }
}
