package task2.centralUnitUtils;

import lights.FlashingBlueLight;
import lights.Light;

import java.util.List;

public class BlueLightsToggleEvent {

    public BlueLightsToggleEvent(List<FlashingBlueLight> flashingBlueLights) {
        for (Light l : flashingBlueLights) {
            l.toggle();
        }
    }
}
