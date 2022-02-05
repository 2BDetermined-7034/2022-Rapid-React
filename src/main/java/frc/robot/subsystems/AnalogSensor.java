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

    public boolean readSensor(){
        if(m_sensor.getAverageValue() < 10) return true;
        return false;
    }

    @Override
    public void periodic() {

    }
}