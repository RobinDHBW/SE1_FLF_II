package lights;

public abstract class Light {
    protected LightPosition position;
    protected Boolean isOn = false;

    public Light(LightPosition position) {
        this.position = position;
    }

    public Boolean toggle() {
        return isOn = !isOn;
    }

    public Boolean getState() {
        return isOn;
    }
}
