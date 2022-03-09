package task8;

import store.StoreMedium;

import java.util.ArrayList;

public class TankSensor {
    private final ArrayList<ITankSensorListener> listeners;

    public TankSensor() {
        this.listeners = new ArrayList<>();
    }

    public void checkFillingLevel(Double fillState) {
        //Double fillState = this.store.getRelativeFillState();
        for (ITankSensorListener l : listeners) {
            if (fillState > 25 && fillState < 50) {
                l.tankLevelChanged(TankLevel.FIFTY);
            } else if (fillState > 10 && fillState < 25) {
                l.tankLevelChanged(TankLevel.TWENTYFIVE);
            } else if (fillState < 10) {
                l.tankLevelChanged(TankLevel.TEN);
            } else {
                l.tankLevelChanged(null);
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
