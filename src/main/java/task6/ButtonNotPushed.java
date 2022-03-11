package task6;

public class ButtonNotPushed implements IButtonState{

    @Override
    public Boolean isOn(){return false;}

    @Override
    public void change(ButtonSwitchTask6 buttonSwitch){buttonSwitch.setState(new ButtonPushed());}
}
