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

    public final static class controllers {
        public static final int gamePadPort = 0;
        public static final int joystickPort = 1;
        public static final int buttonPadPort = 2;
    }
    public final static class driveBase {
        public static final int neoMotor1 = 1;
        public static final int neoMotor2 = 2;
    }
    public final static class shooter {
        public static final int talonSRX1 = 5;
        public static final double speed = 0.9;
    }
    public final static class intake {
        public static final int talonSRX1 = 0;
        public static final double speed = 0.7;
    }
    public final static class climber {
        public static final int driverMotorID = 0;
        public static final int winchMotorID = 0;

        public static final double driverkP = 0.05;
        public static final double driverkI = 0;
        public static final double driverkD = 0;
    }
}
