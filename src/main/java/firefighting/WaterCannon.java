package firefighting;

import tank.TankSubject;

import java.util.List;

public class WaterCannon implements IWaterCannon {
    protected Boolean state = false;
    protected Integer sprayCapacityPerlIteration = 500;

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

    public Integer getSprayCapacityPerlIteration() {
        return sprayCapacityPerlIteration;
    }

    public void setSprayCapacityPerlIteration(Integer sprayCapacityPerlIteration) {
        this.sprayCapacityPerlIteration = sprayCapacityPerlIteration;
    }

    public Boolean getState() {
        return state;
    }
}
