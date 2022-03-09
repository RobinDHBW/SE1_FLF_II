package lights;

public class LED {
    private LEDColor color;

    public LED(LEDColor color) {
        this.color = color;
    }

    public LEDColor getColor() {
        return color;
    }

    public void setColor(LEDColor color) {
        this.color = color;
    }
}
