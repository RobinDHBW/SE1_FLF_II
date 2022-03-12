package task7;

import task2.CentralUnit;

public class CommandOnOff implements ICommand{

    CentralUnit centralUnit;
    ButtonType type;

    public CommandOnOff(CentralUnit cu){
        this.centralUnit = cu;
    }

    @Override
    public void execute(ButtonType typ) {
        this.type = typ;
        switch (type){
            case engines -> centralUnit.switchEngines();
            case warningLight -> centralUnit.switchWarningLight();
            case blueLight -> centralUnit.switchBlueLight();
            case frontLight -> centralUnit.switchFrontLight();
            case roofLight -> centralUnit.switchRoofLight();
            case sideLight -> centralUnit.switchSideLight();
            case selfprotection -> centralUnit.switchSelfprotection();
        }
    }
}
