package lib.position;

/**
 * Class to track location based on encoders
 */
public class EncoderOdometryController {

    private double m_positionAlongField; // The y value of the position (Short side)
    private double m_positionAcrossField; // The x value of the position (Long side)

    private double m_prevHeading;
    private double m_prevPositionAcross;
    private double m_prevPositionAlong;

    public EncoderOdometryController(){
        m_positionAcrossField = 0;
        m_positionAlongField = 0;

        m_prevHeading = 0;
        m_prevPositionAcross = 0;
        m_prevPositionAlong = 0;
    }

    public void update(double leftEncPos, double rightEncPos, double heading){
    }

    public double getPositionAcrossField() {
        return m_positionAcrossField;
    }

    public double getPositionAlongField() {
        return m_positionAlongField;
    }

}
