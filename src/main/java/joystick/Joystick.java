package joystick;

import button.ButtonPress;
import button.ButtonPush;

public class Joystick {
    private final JoystickType joystickType;
    private final ButtonPush btnPush; // Taster
    private ButtonPress btnPressLeft = null; // Druckknopf
    private ButtonPress btnPressRight = null;
    private ButtonPress btnPress = null;

    public Joystick(JoystickType joystickType, ButtonPush btnPush, ButtonPress btnPressLeft, ButtonPress btnPressRight) {
        //btnPressInt.addListener(this);
        this.joystickType = joystickType;
        this.btnPush = btnPush;
        this.btnPressLeft = btnPressLeft;
        this.btnPressRight = btnPressRight;
    }

    public Joystick(JoystickType joystickType, ButtonPush btnPush, ButtonPress btnPress) {
        this.joystickType = joystickType;
        this.btnPush = btnPush;
        this.btnPress = btnPress;
    }

    public JoystickType getJoystickType() {
        return joystickType;
    }

    public void pressBtnLeft() {
        this.btnPressLeft.operateDevice();
    }

    public void pressBtnRight() {
        this.btnPressRight.operateDevice();
    }

    public void pushBtn() {
        this.btnPush.operateDevice();
    }

    public void pressBtn() {
        this.btnPress.operateDevice();
    }
}
