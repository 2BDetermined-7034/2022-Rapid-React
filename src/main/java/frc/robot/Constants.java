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
        
        public static final int shooterTalonID1 = 6;
        public static final int shooterTalonID2 = 5;
        public static final int shooterTalonID3 = 4;
        public static final int shooterMultiplier = 1;
    }

    public final static class climb {

    }
}
