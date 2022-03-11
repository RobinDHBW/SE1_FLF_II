package task7;

import task2.CentralUnit;

public class CommandOnOff implements ICommand{

    CentralUnit centralUnit;

    public CommandOnOff(CentralUnit cu){
        this.centralUnit = cu;
    }

    @Override
    public void execute(ButtonType typ) {
        switch (typ){
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
