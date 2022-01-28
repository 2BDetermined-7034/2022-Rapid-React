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
        public static final int neoMotorLeft1 = 1;
        public static final int neoMotorLeft2 = 2;
        public static final int neoMotorLeft3 =3;
        public static final int neoMotorRight4 = 4;
        public static final int neoMotorRight5 = 5;
        public static final int neoMotorRight6 = 6;
    }

    public final static class intake {
        public static final int intakeMotor = 1;
        public static double intakeSpeed = 0.7;
    }

    public final static class shooter {

    }

    public final static class climb {
        public static final int winchMotorID = 1;
        public static final int solenoidForwardID = 0;
        public static final int solenoidBackID = 7;
        public static final double winchSpeed = 0.5;
        //PID Values
        public static final double kP = 0.05;
        public static final double kI = 0;
        public static final double kD = 0;
        //Encoder garbage (maybe remove if not being used)
        public static final double degreesPerRot = 360;
        public static final double extendedValue = -150;
    }

    public final static class pneumatics {
        public static final int compressorID = 1;
    }
}
