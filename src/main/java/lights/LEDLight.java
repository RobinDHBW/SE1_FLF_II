package lights;

import java.util.ArrayList;
import java.util.List;

public class LEDLight extends Light {
    protected final List<LED> ledComposition = new ArrayList<>();

    public LEDLight(LightPosition position, Integer ledCount, LEDColor color) {
        super(position);
        for (int i = 0; i < ledCount; i++) {
            ledComposition.add(new LED(color));
        }
    }

    public void changeLEDColor(LEDColor color){
        for(LED l: ledComposition){
            l.setColor(color);
        }
    }

    public LEDColor getLEDColor(){
        return ledComposition.get(0).getColor();
    }
}
