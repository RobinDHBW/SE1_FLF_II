package task6;

import button.ButtonBoolean;

public class ButtonSwitchTask6 extends ButtonBoolean {


    protected boolean isOn;
    protected IButtonState state;



    public IButtonState getState() {
        return state;
    }

    public void setState(IButtonState state) {
        this.state = state;
    }

    public void setOn(boolean onOff) {
        isOn = onOff;
    }

    public void switchButton(){state.change(this);}

    public ButtonSwitchTask6(Object o, IButtonState sta) {
        super(o);
        this.state = sta;
    }

    public Boolean isOn(){
        setOn(state.isOn());
        return isOn;
    }
}
