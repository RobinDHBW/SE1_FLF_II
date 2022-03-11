package task6;

import button.ButtonBoolean;

public class ButtonSwitchTask6 extends ButtonBoolean {

    boolean isOn;
    private IButtonState state;

    public IButtonState getState() {
        return state;
    }

    public void setState(IButtonState state) {
        this.state = state;
    }

    public void switchButton(){state.change(this);}

    public ButtonSwitchTask6(Object o, IButtonState sta) {
        super(o);
        this.state = sta;
    }

    public boolean isOn(){
        isOn = state.isOn();
        return isOn;
    }
}
