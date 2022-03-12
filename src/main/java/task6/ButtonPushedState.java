package task6;

public class ButtonPushedState implements IButtonState{


    private final Boolean isOn = true;

    @Override
    public boolean isOn(){return isOn;}

    @Override
    public void change(ButtonSwitchTask6 buttonSwitch){buttonSwitch.setState(new ButtonNotPushedState());}
}
