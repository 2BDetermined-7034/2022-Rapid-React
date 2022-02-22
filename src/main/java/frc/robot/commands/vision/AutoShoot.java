package frc.robot.commands.vision;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AnalogSensor;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.Shooter;


public class AutoShoot extends CommandBase {
    private final AnalogSensor analogSensor;
    private final Indexer indexer;
    private final LimeLight limeLight;
    private final Shooter shooter;

    public AutoShoot(AnalogSensor analogSensor, Indexer indexer, LimeLight limeLight, Shooter shooter) {
        this.analogSensor = analogSensor;
        this.indexer = indexer;
        this.limeLight = limeLight;
        this.shooter = shooter;
        // each subsystem used by the command must be passed into the
        // addRequirements() method (which takes a vararg of Subsystem)
        addRequirements(this.analogSensor, this.indexer, this.limeLight, this.shooter);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
