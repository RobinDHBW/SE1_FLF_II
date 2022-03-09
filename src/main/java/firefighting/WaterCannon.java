package firefighting;

import tank.TankSubject;
import task9.ICannonCheck;

import java.util.List;

public abstract class WaterCannon implements IWaterCannon, ICannonCheck {
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

    @Override
    public Boolean selfCheck() {
        try{
            this.toggle();
            if (!this.state) throw new Exception("Cannon malfunction!");
            this.toggle();
            return true;
        } catch (Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return false;
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
