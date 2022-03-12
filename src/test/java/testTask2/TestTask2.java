package testTask2;

import button.RoofCannonMode;
import firefighting.CannonIdentifier;
import flf.FLF;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import person.Driver;
import person.EmployeeFirebase;
import person.Operator;
import person.Person;
import tank.TankSubject;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTask2 {

    private FLF flf;
    private Driver driver;
    private Operator operator;


    @BeforeEach
    void initRoutine() {
        this.driver = new Driver("Sam");
        this.operator = new Operator("Red Adair");
        ArrayList<Person> authorizedPersons = new ArrayList<>();
        authorizedPersons.add(this.driver);
        authorizedPersons.add(this.operator);
        this.flf = new FLF.Builder(authorizedPersons).build();

        driver.setDoorToggleOutside(this.flf.getCabin().getDoorToggleLeftOutside());
        driver.setIdCardReader(this.flf.getCabin().getCardReaderLeft());
        operator.setDoorToggleOutside(this.flf.getCabin().getDoorToggleRightOutside());
        operator.setIdCardReader(this.flf.getCabin().getCardReaderRight());

        EmployeeFirebase employee = new EmployeeFirebase("Karl-Heinz");

        this.flf.toggleMaintenance(employee);
        employee.loadBatteries();
        this.flf.toggleMaintenance(employee);

        if (this.flf.getCabin().getBusDoorLeft().getLocked()) this.driver.toggleDoorLock();

        if (!this.flf.getCabin().getBusDoorLeft().getOpen()) this.driver.toggleDoor();
        if (!this.flf.getCabin().getBusDoorRight().getOpen()) this.operator.toggleDoor();

        this.flf.enterFLF(operator, false);
        this.operator.toggleDoor();
        this.flf.enterFLF(driver, true);
        this.driver.toggleDoor();

        if (this.flf.getPipeDistribution().getCannonState(CannonIdentifier.CANNON_FRONT)) this.driver.toggleCannon();
        if (this.flf.getPipeDistribution().getCannonState(CannonIdentifier.CANNON_ROOF)) this.operator.toggleCannon();

        while (this.flf.getCabin().getBtnRotaryWaterCannonFront().getMode() > 1 && this.flf.getCabin().getBtnRotaryWaterCannonRoof().getMode() != RoofCannonMode.A) {
            this.operator.leftRotaryButtonFrontCannon();
            this.operator.leftRotaryButtonRoofCannon();
        }
    }

    @Test
    void testLights() {
        assertFalse(flf.getWarnLightsState());
        flf.getCabin().getCentralUnit().switchWarningLight();
        assertTrue(flf.getWarnLightsState());

        assertFalse(flf.getBlueLightState());
        flf.getCabin().getCentralUnit().switchBlueLight();
        assertTrue(flf.getBlueLightState());

        assertFalse(flf.getSearchLightFrontState());
        flf.getCabin().getCentralUnit().switchFrontLight();
        assertTrue(flf.getSearchLightFrontState());

        assertFalse(flf.getSearchLightRoofState());
        flf.getCabin().getCentralUnit().switchRoofLight();
        assertTrue(flf.getSearchLightRoofState());

        assertFalse(flf.getSearchLightSideState());
        flf.getCabin().getCentralUnit().switchSideLight();
        assertTrue(flf.getSearchLightSideState());
    }

    @Test
    void testEngine() {
        assertFalse(flf.getDrive().getEngineState());
        flf.getCabin().getCentralUnit().switchEngines();
        assertTrue(flf.getDrive().getEngineState());
    }

    @Test
    void testSelfdefence() {
        flf.getPipeDistribution().fillComplete(TankSubject.FOAM);
        flf.getPipeDistribution().fillComplete(TankSubject.WATER);
        assertFalse(flf.getPipeDistribution().getCannonState(CannonIdentifier.CANNON_SELFPROTECTION));
        flf.getCabin().getCentralUnit().switchSelfprotection();
        assertFalse(flf.getPipeDistribution().getCannonState(CannonIdentifier.CANNON_SELFPROTECTION));
    }
}
