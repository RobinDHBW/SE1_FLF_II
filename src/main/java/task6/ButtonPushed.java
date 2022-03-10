package task6;

public class ButtonPushed implements IButtonState{

    @Override
    public Boolean isOn(){return true;}

    @Override
    public void change(ButtonSwitch buttonSwitch){buttonSwitch.setState(new ButtonNotPushed());}
}
