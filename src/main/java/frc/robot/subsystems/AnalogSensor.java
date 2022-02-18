package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class AnalogSensor extends SubsystemBase {
    private final AnalogInput m_sensor;
    private final AnalogInput m_sensor2;
    public boolean override;

    public AnalogSensor() {
        override = false;
        m_sensor = new AnalogInput(0);
        m_sensor2 = new AnalogInput(1);
    }

    /**
     *
     * @return Boolean - If something trips the sensor (True = yes, something tripped the sensor | false = no, nothing tripped the sensor
     */
    public boolean sensorBoolean0(){
        if(override) return false;
        return m_sensor.getAverageValue() <= 7;
    }

    public boolean sensorBoolean1(){
        if(override) return false;
        return m_sensor2.getAverageValue() <= 7;
    }



    /**
     *
     * @return Double - Sensor 0's average value
     */
    public double getSensor0AvValue() {
        return m_sensor.getAverageValue();
    }

    /**
     *
     * @return Double - Sensor 1's average value
     */
    public double getSensor1AvValue() {
        return m_sensor2.getAverageValue();
    }

    /**
     *
     * @return Boolean / If the color sensors have something in front of them - True = There is something in front of the color sensor.
     */
    public boolean sensorBoolean1_2() {
        boolean sen1  = m_sensor.getAverageValue() > 7;
        boolean sen2 = m_sensor2.getAverageValue() > 7;

        if(override) return false;
        return !sen1 && !sen2;
    }



    @Override
    public void periodic() {
        SmartDashboard.putNumber("Sensor 0 Value", getSensor0AvValue());
        SmartDashboard.putNumber("Sensor 1 Value", getSensor1AvValue());
        // If both sensor 0 and 1 is true, it'll show that the indexer is full.
        SmartDashboard.putBoolean("Is Indexer Full?", sensorBoolean0());
    }
}