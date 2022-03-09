package testTask9;

import button.RoofCannonMode;
import firefighting.CannonIdentifier;
import flf.FLF;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import person.Driver;
import person.EmployeeFirebase;
import person.Operator;
import person.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTask9 {
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

    @TestFactory
    Stream<DynamicTest> testCannonSelfchecks() {
        ArrayList<DynamicTest> tests = new ArrayList<>();
        this.operator.toggleEngines();
        Collections.addAll(tests,
                DynamicTest.dynamicTest("check CannonRoof", () -> assertTrue(this.flf.getMixingProcessor().getSelfCheckState(CannonIdentifier.CANNON_ROOF).get(0))),
                DynamicTest.dynamicTest("check CannonFront", () -> assertTrue(this.flf.getMixingProcessor().getSelfCheckState(CannonIdentifier.CANNON_FRONT).get(0)))
        );
        List<Boolean> selfProtectionSelfCheckState = this.flf.getMixingProcessor().getSelfCheckState(CannonIdentifier.CANNON_SELFPROTECTION);
        Integer index = 0;
        for (Boolean b : selfProtectionSelfCheckState) {
            tests.add(DynamicTest.dynamicTest("check WaterDieSelfProtection #" + ++index, () -> assertTrue(b)));
        }
        return tests.stream();
    }
}
