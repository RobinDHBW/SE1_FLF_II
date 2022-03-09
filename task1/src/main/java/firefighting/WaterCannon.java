package firefighting;

import task9.ICannonCheck;
import task9.ICannonVisitor;
import tank.*;

import java.util.List;

public abstract class WaterCannon implements IWaterCannon, ICannonCheck {
    protected Boolean state = false;
    protected Integer sprayCapacityPerlIteration = 500;
    protected Boolean selfCheckPassed = false;

    public WaterCannon() {

    }

    @Override
    public void toggle() {
        this.state = !this.state;
    }

    @Override
    public void spray(List<TankSubject> toSpray) {
        for (TankSubject ts : toSpray) {
            ts = null;
        }
    }

    @Override
    public Boolean selfCheck(ICannonVisitor visitor) {
        return visitor.visit(this);
    }

    public Integer getSprayCapacityPerlIteration() {
        return sprayCapacityPerlIteration;
    }

    public void setSprayCapacityPerlIteration(Integer sprayCapacityPerlIteration) {
        this.sprayCapacityPerlIteration = sprayCapacityPerlIteration;
    }

    public Boolean getState() {
        return state;
    }

    public Boolean getSelfCheckPassed() {
        return selfCheckPassed;
    }

    public void setSelfCheckPassed(Boolean selfCheckPassed) {
        this.selfCheckPassed = selfCheckPassed;
    }
}
