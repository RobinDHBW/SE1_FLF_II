package person;

import button.ButtonPush;
import button.Pedal;
import instruments.SteeringWheel;
import joystick.Joystick;

public class Driver extends ActivePassenger {
    private SteeringWheel steeringWheel;
    private Pedal gasPedal;
    private Pedal brakePedal;

    public Driver(String name) {
        super(name);
    }

    public void equip(SteeringWheel wheel, Pedal gas, Pedal brake, Joystick stick, ButtonPush doorToggleInside) {
        this.steeringWheel = wheel;
        this.gasPedal = gas;
        this.brakePedal = brake;
        super.equip(stick, doorToggleInside);
    }

    public void uneqip() {
        this.steeringWheel = null;
        this.gasPedal = null;
        this.brakePedal = null;
        super.uneqip();
    }


    public void accelerate() {
        this.gasPedal.operateDevice();
    }

    public void brake() {
        this.brakePedal.operateDevice();
    }

    public void steer(Boolean isLeft, Integer degree) {
        this.steeringWheel.steer(isLeft, degree);
    }
}