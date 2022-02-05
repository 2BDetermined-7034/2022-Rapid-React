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
        return m_sensor.getAverageValue() < 10;
    }

    /**
     *
     * @param intake Intake subsystem
     * @param shooter Shooter subsystem
     */
    public void testSensorStuff(CargoIntake intake, Shooter shooter) {
        // TODO This pretty much checks if the sensor is true, if so, turns the intake off, and turns the shooter on, though an issue is that the button bindings, the intake is a toggle, so I don't know if it'll disable that toggle if I set the speed to 0.
        if(sensorBoolean()) intake.mmmRunMotor(0); shooter.setSpeed(0.35);
    }

    @Override
    public void periodic() {

    }
}