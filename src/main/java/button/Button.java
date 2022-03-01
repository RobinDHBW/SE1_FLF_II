package button;


public abstract class Button {
    protected Object operatingDevice;

    public Button(Object operatingDevice) {
        this.operatingDevice = operatingDevice;
    }

    public void operateDevice() {

    }
}
