package utilities;

import java.util.List;

public interface IWaterCannon {
    void toggle();

    void spray(List<TankSubject> toSpray);
}
