package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class AnalogSensor extends SubsystemBase {
    private final AnalogInput m_sensor;

    public AnalogSensor() {
        m_sensor = new AnalogInput(0);
    }

    /**
     *
     * @return Sensor average value
     */
    public double readValue() {
        return m_sensor.getAverageValue();
    }

    /**
     *
     * @return Boolean - If something trips the sensor (True = yes, something tripped the sensor | false = no, nothing tripped the sensor
     */
    public boolean sensorBoolean(){
        return m_sensor.getAverageValue() < 7;
    }

    @Override
    public void periodic() {

    }
}