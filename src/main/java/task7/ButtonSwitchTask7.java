package task7;

import task2.CentralUnit;
import task6.ButtonSwitchTask6;
import task6.IButtonState;

public class ButtonSwitchTask7 extends ButtonSwitchTask6 {

    ButtonType type;

    ICommand onOff;

    public ButtonSwitchTask7(Object o, IButtonState sta, ButtonType typ) {
        super(o, sta);
        this.onOff = new CommandOnOff((CentralUnit) o);
        this.type = typ;
    }

    public void on(ButtonType type){
        onOff.execute(type);
        switchButton();
    }

    public ButtonType getTyp(){return type;}
}
