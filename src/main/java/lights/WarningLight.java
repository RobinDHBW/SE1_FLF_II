package lights;

public class WarningLight extends LEDLight {

    public WarningLight(LightPosition position) {
        super(position, 1, LEDColor.ORANGE);
    }
}
