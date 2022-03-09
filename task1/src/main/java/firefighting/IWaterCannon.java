package firefighting;

import java.util.List;
import tank.*;

public interface IWaterCannon {
    void toggle();

    void spray(List<TankSubject> toSpray);
}
