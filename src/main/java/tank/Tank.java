package tank;

import store.StoreMedium;
import task8.TankSensor;

import java.util.List;


public class Tank extends StoreMedium {

    public Tank(TankSubject subject, Integer length, Integer height, Integer width, TankSensor sensor) {
        super(length, height, width, subject, sensor);
    }

    @Override
    public void fill(Object input, Integer quantity) {
        super.fill(input, quantity);
    }

    @Override
    public List<Object> remove(Integer quantity) {
        return super.remove(quantity);
    }

}
