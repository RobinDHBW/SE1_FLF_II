package lights;

import task2.centralUnitUtils.Subscriber;

public abstract class Light extends Subscriber{
    protected LightPosition position;
    protected Boolean isOn = false;

    public Light(LightPosition position) {
        super(002);
        this.position = position;
    }

    public Boolean toggle() {
        return isOn = !isOn;
    }

    public Boolean getState() {
        return isOn;
    }
}
