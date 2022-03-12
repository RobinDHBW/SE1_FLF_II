package testTask5;


import button.RoofCannonMode;
import firefighting.CannonIdentifier;
import flf.FLF;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import person.Driver;
import person.EmployeeFirebase;
import person.Operator;
import person.Person;
import task5.LoadingStation;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestTask5 {
    private FLF flf;
    private Driver driver;
    private Operator operator;
    private LoadingStation loadingStation;

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
        employee.fillWaterTank();
        employee.fillFoamTank();
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
        this.loadingStation = new LoadingStation();
    }

    @Test
    void testOnePoleSocketExist(){assertNotNull(loadingStation.socket);}

    @Test
    void testFillStateIsLessThenFull() {

        this.flf.getDrive().getAbsoluteFillState();

        assertEquals(1.0 , this.flf.getDrive().getRelativeFillState());

        int i = 0;
        this.flf.getDrive().accelerate();
        while (i <= 9){
            i++;
            this.flf.getDrive().drive();
        }
        this.flf.getDrive().brake();
        this.flf.getDrive().load1000();

        assertEquals(400000, this.flf.getDrive().getAbsoluteFillState());
    }

}

