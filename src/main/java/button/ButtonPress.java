package button;

public class ButtonPress extends ButtonTap {
    private boolean isActivated;

    public ButtonPress(Object o) {
        super(o);
        this.isActivated = false;
    }

    public boolean isHeld5seconds() {
        return isActivated;
    }

    public boolean hold5sec() {
        this.isActivated = !isHeld5seconds();
        return this.isActivated;
    }
}
