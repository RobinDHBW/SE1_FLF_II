package firefighting;


import java.util.List;

import tank.*;

public class Tank extends StoreMedium {

    public Tank(TankSubject subject, Integer length, Integer height, Integer width) {
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
