package person;

import button.ButtonPush;
import button.IDCardReader;
import joystick.Joystick;
import joystick.JoystickType;

public abstract class ActivePassenger extends Person {

    protected Joystick joystick;
    protected ButtonPush doorToggleInside;
    protected ButtonPush doorToggleOutside;
    protected IDCardReader idCardReader;

    public ActivePassenger(String name) {
        super(name);
    }

    public void equip(Joystick stick, ButtonPush doorToggleInside) {
        this.joystick = stick;
        this.doorToggleInside = doorToggleInside;

    }

    public void setDoorToggleOutside(ButtonPush doorToggleOutside) {
        this.doorToggleOutside = doorToggleOutside;
    }

    public void setIdCardReader(IDCardReader idCardReader) {
        this.idCardReader = idCardReader;
    }

    public void uneqip() {
        this.joystick = null;
    }


    public void toggleCannon() {
        if (this.joystick.getJoystickType() == JoystickType.CLASSIC) {
            this.joystick.pressBtnLeft();
        } else {
            this.joystick.pressBtn();
        }

    }

    public void switchMix() {
        if (this.joystick.getJoystickType() == JoystickType.CLASSIC) {
            this.joystick.pressBtnRight();
        } else {
            this.joystick.pressBtn();
        }

    }

    public void spray() {
        this.joystick.pushBtn();
    }

    public void toggleDoor() {
        (isInVehicle ? doorToggleInside : doorToggleOutside).operateDevice();
    }

    public void toggleDoorLock() {
        this.idCardReader.operateDevice(this.idCard);
    }

}
