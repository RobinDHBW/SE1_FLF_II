package task6;

public class ButtonNotPushedState implements IButtonState{

    private final Boolean isOn = false;

    @Override
    public boolean isOn(){return  isOn;}

    @Override
    public void change(ButtonSwitchTask6 buttonSwitch){buttonSwitch.setState(new ButtonPushedState());}
}
