package task2.centralUnitUtils;

import lights.Light;
import lights.SearchLight;

import java.util.List;

public class RoofLightToggleEvent {
    public RoofLightToggleEvent(List<SearchLight> searchLightsRoof) {
        for (Light l : searchLightsRoof) {
            l.toggle();
        }
    }
}
