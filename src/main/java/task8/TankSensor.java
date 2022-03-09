package task8;

import tank.TankSubject;

import java.util.ArrayList;

public class TankSensor {
    private final ArrayList<ITankSensorListener> listeners;

    public TankSensor() {
        this.listeners = new ArrayList<>();
    }

    public void checkFillingLevel(Double fillState, TankSubject subject) {
        fillState*=100;
        for (ITankSensorListener l : listeners) {
            if (fillState > 25 && fillState < 50) {
                l.tankLevelChanged(TankLevel.FIFTY, subject);
            } else if (fillState > 10 && fillState < 25) {
                l.tankLevelChanged(TankLevel.TWENTYFIVE, subject);
            } else if (fillState < 10) {
                l.tankLevelChanged(TankLevel.TEN, subject);
            } else {
                l.tankLevelChanged(null, subject);
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
