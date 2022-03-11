package task6;

public class ButtonPushed implements IButtonState{

    @Override
    public Boolean isOn(){return true;}

    @Override
    public void change(ButtonSwitchTask6 buttonSwitch){buttonSwitch.setState(new ButtonNotPushed());}
}
