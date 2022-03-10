package task6;

public class ButtonNotPushed implements IButtonState{

    @Override
    public Boolean isOn(){return false;}

    @Override
    public void change(ButtonSwitch buttonSwitch){buttonSwitch.setState(new ButtonPushed());}
}
