package task2.centralUnitUtils;

import lights.Light;
import lights.WarningLight;

import java.util.List;

public class WarningLightToggleEvent {

    public WarningLightToggleEvent(List<WarningLight> warningLights) {
        for (Light l : warningLights) {
            l.toggle();
        }
    }

}
