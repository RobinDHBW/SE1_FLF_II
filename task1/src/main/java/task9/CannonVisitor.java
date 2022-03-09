package task9;

import firefighting.WaterCannon;

public class CannonVisitor implements ICannonVisitor {

    @Override
    public Boolean visit(WaterCannon cannon) {
        cannon.toggle();
        if (!cannon.getState()) return false;
        cannon.toggle();
        cannon.setSelfCheckPassed(true);
        return true;
    }
}
