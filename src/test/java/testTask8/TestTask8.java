package testTask8;

import button.RoofCannonMode;
import firefighting.CannonIdentifier;
import flf.FLF;
import lights.LEDColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import person.Driver;
import person.EmployeeFirebase;
import person.Operator;
import person.Person;
import tank.MixingRate;
import tank.TankSubject;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestTask8 {
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
        //employee.fillWaterTank();
        //employee.fillFoamTank();
        this.flf.toggleMaintenance(employee);

        if (this.flf.getCabin().getBusDoorLeft().getLocked()) this.driver.toggleDoorLock();

        if (!this.flf.getCabin().getBusDoorLeft().getOpen()) this.driver.toggleDoor();
        if (!this.flf.getCabin().getBusDoorRight().getOpen()) this.operator.toggleDoor();

        this.flf.enterFLF(operator, false);
        this.operator.toggleDoor();
        this.flf.enterFLF(driver, true);
        this.driver.toggleDoor();

        if (this.flf.getMixingProcessor().getCannonState(CannonIdentifier.CANNON_FRONT)) this.driver.toggleCannon();
        if (this.flf.getMixingProcessor().getCannonState(CannonIdentifier.CANNON_ROOF)) this.operator.toggleCannon();

        while (this.flf.getCabin().getBtnRotaryWaterCannonFront().getMode() > 1 && this.flf.getCabin().getBtnRotaryWaterCannonRoof().getMode() != RoofCannonMode.A) {
            this.operator.leftRotaryButtonFrontCannon();
            this.operator.leftRotaryButtonRoofCannon();
        }
    }

    @Test
    void testWaterTankHigh() {
        assertFalse(this.flf.getWaterTankLEDState());
    }

    @Test
    void testFoamTankHigh() {
        assertFalse(this.flf.getFoamTankLEDState());
    }

    @Test
    void testWaterTank50() {
        this.flf.getMixingProcessor().fillComplete(TankSubject.WATER);
        this.flf.getMixingProcessor().fillComplete(TankSubject.FOAM);

        this.operator.toggleCannon();
        while (this.flf.getCabin().getBtnRotaryWaterCannonRoof().getMode() != RoofCannonMode.C) {
            this.operator.rightRotaryButtonRoofCannon();
        }
        while (this.flf.getMixingProcessor().getTankFillState(TankSubject.WATER)*100 > 50) {
            this.operator.spray();
        }
        assertEquals(LEDColor.YELLOW, this.flf.getWaterTankLEDColor());
    }

    @Test
    void testWaterTank25() {
        this.flf.getMixingProcessor().fillComplete(TankSubject.WATER);
        this.flf.getMixingProcessor().fillComplete(TankSubject.FOAM);
        this.operator.toggleCannon();
        while (this.flf.getCabin().getBtnRotaryWaterCannonRoof().getMode() != RoofCannonMode.C) {
            this.operator.rightRotaryButtonRoofCannon();
        }
        while (this.flf.getMixingProcessor().getTankFillState(TankSubject.WATER)*100 > 25) {
            this.operator.spray();
        }
        assertEquals(LEDColor.ORANGE, this.flf.getWaterTankLEDColor());
    }

    @Test
    void testWaterTank10() {
        this.flf.getMixingProcessor().fillComplete(TankSubject.WATER);
        this.flf.getMixingProcessor().fillComplete(TankSubject.FOAM);
        this.operator.toggleCannon();
        while (this.flf.getCabin().getBtnRotaryWaterCannonRoof().getMode() != RoofCannonMode.C) {
            this.operator.rightRotaryButtonRoofCannon();
        }
        while (this.flf.getMixingProcessor().getTankFillState(TankSubject.WATER)*100 > 10) {
            this.operator.spray();
        }
        assertEquals(LEDColor.RED, this.flf.getWaterTankLEDColor());
    }

    @Test
    void testFoamTank50() {
        this.flf.getMixingProcessor().fillComplete(TankSubject.WATER);
        this.flf.getMixingProcessor().fill(TankSubject.FOAM, 17000); //FoamTank has capacity if 33750
        this.operator.toggleCannon();
        while (this.flf.getCabin().getBtnRotaryWaterCannonRoof().getMode() != RoofCannonMode.C) {
            this.operator.rightRotaryButtonRoofCannon();
        }
        while (this.flf.getMixingProcessor().getTankFillState(TankSubject.FOAM)*100 > 50) {
            this.operator.spray();
        }
        assertEquals(LEDColor.YELLOW, this.flf.getFoamTankLEDColor());
    }

    @Test
    void testFoamTank25() {
        this.flf.getMixingProcessor().fillComplete(TankSubject.WATER);
        this.flf.getMixingProcessor().fill(TankSubject.FOAM, 9000); //FoamTank has capacity if 33750
        this.operator.toggleCannon();
        while (this.flf.getCabin().getBtnRotaryWaterCannonRoof().getMode() != RoofCannonMode.C) {
            this.operator.rightRotaryButtonRoofCannon();
        }
        while (this.flf.getMixingProcessor().getTankFillState(TankSubject.FOAM)*100 > 25) {
            this.operator.spray();
        }
        assertEquals(LEDColor.ORANGE, this.flf.getFoamTankLEDColor());
    }

    @Test
    void testFoamTank10() {
        this.flf.getMixingProcessor().fillComplete(TankSubject.WATER);
        this.flf.getMixingProcessor().fill(TankSubject.FOAM, 4000); //FoamTank has capacity if 33750
        this.operator.toggleCannon();
        while (this.flf.getCabin().getBtnRotaryWaterCannonRoof().getMode() != RoofCannonMode.C) {
            this.operator.rightRotaryButtonRoofCannon();
        }
        while (this.flf.getMixingProcessor().getTankFillState(TankSubject.FOAM)*100 > 10) {
            this.operator.spray();
        }
        assertEquals(LEDColor.RED, this.flf.getFoamTankLEDColor());
    }

}
