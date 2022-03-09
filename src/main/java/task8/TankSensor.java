package task8;

import store.StoreMedium;

import java.util.ArrayList;

public class TankSensor {
    private final ArrayList<ITankSensorListener> listeners;
    private final StoreMedium store;

    public TankSensor(StoreMedium store) {
        this.listeners = new ArrayList<>();
        this.store = store;
    }

    public void checkFillingLevel() {
        Double fillState = this.store.getRelativeFillState();
        for (ITankSensorListener l : listeners) {
            if (fillState > 25 && fillState < 50) {
                l.level50();
            } else if (fillState > 10 && fillState < 25) {
                l.level25();
            } else if (fillState < 10) {
                l.level10();
            } else {
                l.levelHigh();
            }
        }
    }

    public void addListener(ITankSensorListener listener){
        this.listeners.add(listener);
    }

    public void removeListener(ITankSensorListener listener){
        this.listeners.remove(listener);
    }

}
