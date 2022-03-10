package task2.centralUnitUtils;

import drive.Drive;

public class EngineToggleEvent {

    public EngineToggleEvent(Drive drive) {
        {
            drive.toggleEngine();
            /*if (drive.getEngineState()) {
                cent.cannonCheck();
            } else {
                //this.mixingProcessor.resetCannonSelfCheck();//Mixinprocessor zu ergänzen
            }*/
        }

    }
}
