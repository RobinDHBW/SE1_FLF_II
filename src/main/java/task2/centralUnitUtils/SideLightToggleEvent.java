package task2.centralUnitUtils;

import lights.Light;
import lights.SearchLight;

import java.util.List;

public class SideLightToggleEvent {

    public SideLightToggleEvent(List<SearchLight> searchLightsSide) {
        for (Light l : searchLightsSide) {
            l.toggle();
        }
    }
}
