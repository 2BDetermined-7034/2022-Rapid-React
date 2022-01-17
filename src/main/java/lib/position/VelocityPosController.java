package lib.position;

/**
 * Class to track based on velocity integration
 */
public class VelocityPosController {

    private final double k_driveLength; // Constant drivebase length
    private final double k_driveWidth; // Constant drivebase width

    private double positionAlongField; // The y value of the position (Short side)
    private double positionAcrossField; // The x value of the position (Long side)

    VelocityPosController(double length, double width){
        k_driveLength = length;
        k_driveWidth = width;
    }

    public void update(double speed, double heading){

    }

}
