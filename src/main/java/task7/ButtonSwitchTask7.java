package task7;

import task2.CentralUnit;
import task6.ButtonSwitchTask6;
import task6.IButtonState;

public class ButtonSwitchTask7 extends ButtonSwitchTask6 {

    ButtonType type;
    ICommand command;

    public ButtonSwitchTask7(Object o, IButtonState sta, ButtonType typ) {
        super(o, sta);
        this.command = new CommandOnOff((CentralUnit) o);
        this.type = typ;
    }

    public void onOff(){
        command.execute(getTyp());
        switchButton();
    }

    public ButtonType getTyp(){return type;}
}
