package lib.position;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import frc.robot.subsystems.Drive;

public class PositionController {

    private double k_wpiOdometryWeight;
    private DifferentialDriveOdometry m_wpiOdometryController;

    private VelocityPosController m_velocityPositionController;


    PositionController(Drive drivetrain, Pose2d startingPos){
        m_wpiOdometryController = new DifferentialDriveOdometry(Rotation2d.fromDegrees(drivetrain.getCurrentAngle()), startingPos);

    }


    public void update(Drive drivetrain){


    }
}
