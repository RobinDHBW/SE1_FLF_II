
import cabin.*;
import flf.FLF;
import person.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TestFLFBuilder {
private FLF proto;

    @BeforeEach
    void buildProto(){
        ArrayList<Person> authorizedPersons = new ArrayList<>();
        authorizedPersons.add(new Driver("Red Adair"));
        authorizedPersons.add(new Operator("Sam"));
        this.proto=new FLF.Builder(authorizedPersons).build();
    }

    @TestFactory
    Stream<DynamicTest> testCtrlPanelBuild() {
        ControlPanel ctrProto = this.proto.getCabin().getCtrlPanel();
        return  Stream.of(
                DynamicTest.dynamicTest("check btnSwitchEngines", () -> assertNotNull(ctrProto.getBtnSwitchEngines())),
                DynamicTest.dynamicTest("check btnSwitchWarnlight", () -> assertNotNull(ctrProto.getBtnSwitchWarnlight())),
                DynamicTest.dynamicTest("check btnSwitchBluelight", () -> assertNotNull(ctrProto.getBtnSwitchBluelight())),
                DynamicTest.dynamicTest("check btnSwitchFrontlight", () -> assertNotNull(ctrProto.getBtnSwitchFrontlight())),
                DynamicTest.dynamicTest("check btnSwitchRooflight", () -> assertNotNull(ctrProto.getBtnSwitchRooflight())),
                DynamicTest.dynamicTest("check btnSwitchSidelight", () -> assertNotNull(ctrProto.getBtnSwitchSidelight()))
        );
    }


    @TestFactory
    Stream<DynamicTest> testCabinBuild() {
        Cabin cabProto = this.proto.getCabin();
        return Stream.of(
                DynamicTest.dynamicTest("check seatList", ()->assertTrue(cabProto.getSeatList().size()==4 && cabProto.getSeatList().get(0) != null)),
                DynamicTest.dynamicTest("check batteryIndicator", ()-> assertNotNull(cabProto.getBatteryIndicator())),
                DynamicTest.dynamicTest("check speedometer", ()-> assertNotNull(cabProto.getSpeedometer())),
                DynamicTest.dynamicTest("check gasPedal", ()-> assertNotNull(cabProto.getGasPedal())),
                DynamicTest.dynamicTest("check brakePedal", ()-> assertNotNull(cabProto.getBrakePedal())),
                DynamicTest.dynamicTest("check steeringWheel", ()-> assertNotNull(cabProto.getSteeringWheel())),
                DynamicTest.dynamicTest("check btnRotaryWaterCannonRoof", ()-> assertNotNull(cabProto.getBtnRotaryWaterCannonRoof())),
                DynamicTest.dynamicTest("check btnRotaryWaterCannonFront", ()-> assertNotNull(cabProto.getBtnRotaryWaterCannonFront())),
                DynamicTest.dynamicTest("check ctrlPanel", ()-> assertNotNull(cabProto.getCtrlPanel())),
                DynamicTest.dynamicTest("check centralUnit", ()-> assertNotNull(cabProto.getCentralUnit())),
                DynamicTest.dynamicTest("check joystickDriver", ()-> assertNotNull(cabProto.getJoystickDriver())),
                DynamicTest.dynamicTest("check joystickOperator", ()-> assertNotNull(cabProto.getJoystickOperator())),
                DynamicTest.dynamicTest("check busDoorLeft", ()-> assertNotNull(cabProto.getBusDoorLeft())),
                DynamicTest.dynamicTest("check busDoorRight", ()-> assertNotNull(cabProto.getBusDoorRight()))
        );
    }

    /**
     * @return stream - stream
     */
    @TestFactory
    Stream<DynamicTest> testFLFBuild() {

        return Stream.of(
                DynamicTest.dynamicTest("Check Cabin", () -> assertNotNull(this.proto.getCabin())),
                DynamicTest.dynamicTest("Check SearchLightFront", () -> assertTrue(this.proto.getSearchLightsFront().size() == 6 && this.proto.getSearchLightsFront().get(0) != null)),
                DynamicTest.dynamicTest("Check SearchLightSide", () -> assertTrue(this.proto.getSearchLightsSide().size() == 10 && this.proto.getSearchLightsSide().get(0) != null)),
                DynamicTest.dynamicTest("Check SearchLightRoof", () -> assertTrue(this.proto.getSearchLightsRoof().size() == 4 && this.proto.getSearchLightsRoof().get(0) != null)),
                DynamicTest.dynamicTest("Check directionIndicatorsLeft", () -> assertTrue(this.proto.getDirectionIndicatorsLeft().size() == 2 && this.proto.getDirectionIndicatorsLeft().get(0) != null)),
                DynamicTest.dynamicTest("Check directionIndicatorsRight", () -> assertTrue(this.proto.getDirectionIndicatorsRight().size() == 2 && this.proto.getDirectionIndicatorsRight().get(0) != null)),
                DynamicTest.dynamicTest("Check brakingLights", () -> assertTrue(this.proto.getBrakingLights().size() == 2 && this.proto.getBrakingLights().get(0) != null)),
                DynamicTest.dynamicTest("Check flashingBlueLights", () -> assertTrue(this.proto.getFlashingBlueLights().size() == 10 && this.proto.getFlashingBlueLights().get(0) != null)),
                DynamicTest.dynamicTest("Check warningLights", () -> assertTrue(this.proto.getWarningLights().size() == 2 && this.proto.getWarningLights().get(0) != null)),
                DynamicTest.dynamicTest("Check cabin", () -> assertNotNull(this.proto.getCabin())),
                DynamicTest.dynamicTest("Check drive", () -> assertNotNull(this.proto.getDrive())),
                DynamicTest.dynamicTest("Check mixingProcessor", () -> assertNotNull(this.proto.getPipeDistribution()))
        );

    }
}
