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
    public static final class driveBase {
        // Odometry
        public static double length = 0;
        public static double width = 0;
        // Left
        public static int leftTalon1 = 0;
        public static int leftTalon2 = 1;
        // Right
        public static int rightTalon1 = 2;
        public static int rightTalon2 = 3;
        // Speed
        public static final double xSpeed = 0.7;
        public static final double xRot = 0.6;
    }
}
