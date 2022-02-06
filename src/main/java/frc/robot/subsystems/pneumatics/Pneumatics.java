/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.pneumatics;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/*
/*
@@P?7!!5&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
&^!#@&J.^&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
P.:B@@B.:&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@#G&@J:?&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@:!@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@GJJ?JB@@
@@@@@5~B@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&:.P&&P.~&
@@@@@J.^&@@@@@@@@@@#GY??JPGGGPPPGB#&&@@@@@@@@@@@@@&!!&@@#..B
@@@@@@@@@@@@@@@G?^..:!?J7~^~~:...::.:^7P&@@@@@@@@@@@@#?!^^G@
@@@@@@@@@@@@#7.. .~YY!:..................7#@@@@@@@@@G.J&@@@@
@@@@@@@@@@@7. ..:Y5~.................:.....?&@@@@@@B!?@@@@@@
@@@@@@@@@&^ ...:5J.......:.....:.....^:.....:&@@@@@~.?@@@@@@
@@@@@@@@@~ ....5?..:...::!:..........:~....:.~@@@@@@@@@@@@@@
@@@@@@@@B.....~J...^:...:!....::......!....:. Y@@@@@@@@@@@@@
@@@@@@@@?.....7^...!....~~....~:.....:!^....:.:@@@@@@@@@@@@@
@@@@@@@@^.....!....!....!~....!:....:^~!....^..P@@@@@@@@@@@@
@@@@@@@&:.....~....!.. .!~....!^^:..^!~7:..~^..~&@@@@@@@@@@@
@@@@@@@P.....~7....7^~!7YYJ?!:^~^:..7PPPJ?JJ~..PP@@@@@@@@@@@
@@@@@@@!....:!7....!??PBBG5J~.......~BBGJ?:^^..&@@@@@@@@@@@@
@@@@@@P.....^!7....~:.PPP5GB! ......^5JYG5.::.^&@@@@@@@@@@@@
@@@@@&:.~:..^!~^...~^.^!!!77.........:^^~:.^..~@@@@@@@@@@@@@
@@@@@#..!:..~~^~::.^^......................:::7@@@@@@@@@@@@@
@@@@@&:.!~:.^!^^^^^:^......................^^:P@@@@@@@@@@@@@
@@@@@@B:^!~:^~~~!^~:^:...................!7^:~&@@@@@@@@@@@@@
@@@@@@@@P?7~^^^!7~~~^!^^:::.....:..::^~~7&Y:~J@@@@@@@@@@@@@@
@@@@@@@@@PY5YJ??7J77~7!^~~~~~~!!~!!~!7JP@@?~7B@@@@@@@@@@@@@@
@@@@@@@@@55B##BB&@&??77^^^^^^:::.:YGG5&@@&!7J@@@@@@@@@@@@@@@
@@@@@@@@&BGBBB#&@@@B!??!::::.:....7BGG@@@@BB&@@@@@@@@@@@@@@@
@@@@@@@BPGGGBB@@@@G..... ..........^P5&@@@@@@@@@@@@@@@@@@@@@

 */

public class Pneumatics extends SubsystemBase {
    public boolean isON;
    public Compressor comp;

    public Pneumatics() {
        comp = new Compressor(Constants.intake.IDcompressor, Constants.pneumatics.compressor);
    }

    public boolean setCompressor(boolean on) {
        this.isON = on;
        if (on) { comp.enabled(); }
        else { comp.disable(); }
        return comp.enabled();
    }

    public boolean toggleCompressor() {

        return setCompressor(!comp.enabled());
    }

    public void debug() {
        SmartDashboard.putBoolean("Is Compressor On?", isON);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}