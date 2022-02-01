// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooter;
import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.DoubleSupplier;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RunShooter extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final Shooter m_Shooter;
  private final DoubleSupplier shooterSpeed;

  /**
   * Creates a new RunShooter.
   *
   * @param subsystem The subsystem used by this command.
   * @param speed DoubleSupplier for the motor.
   */
  public RunShooter(Shooter subsystem, DoubleSupplier speed) {
    m_Shooter = subsystem;
    shooterSpeed = speed;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_Shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    double speed = shooterSpeed.getAsDouble();
      if(speed != 0) {
        m_Shooter.goBrr(0.5);
      }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
