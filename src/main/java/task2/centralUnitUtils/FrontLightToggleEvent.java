package task2.centralUnitUtils;

import lights.Light;
import lights.SearchLight;

import java.util.List;

public class FrontLightToggleEvent {

    public FrontLightToggleEvent(List<SearchLight> searchLightsFront) {
        for (Light l : searchLightsFront) {
            l.toggle();
        }
    }

}
