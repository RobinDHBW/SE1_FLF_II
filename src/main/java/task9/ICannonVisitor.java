package task9;

import firefighting.WaterCannon;

public interface ICannonVisitor {
    Boolean visit(WaterCannon cannon);
}
