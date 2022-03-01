package batteryManagement;

import store.StoreMedium;

import java.util.List;

public class Battery extends StoreMedium {

    public Battery(Object subject, Integer length, Integer height, Integer width) {
        super(length, height, width, subject);
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
