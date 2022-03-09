package task8;

import tank.TankSubject;

public interface ITankSensorListener {
    void tankLevelChanged(TankLevel level, Object subject);
}
