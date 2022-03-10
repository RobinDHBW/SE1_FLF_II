package testTask5;

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
import task5.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestTask5 {
    private FLF flf;
    private Driver driver;
    private Operator operator;
    private LoadingStation loadingStation;
    private OnePoleSocket pole;

    @BeforeEach
    void initRoutine() {
        this.driver = new Driver("Sam");
        this.operator = new Operator("Red Adair");
        ArrayList<Person> authorizedPersons = new ArrayList<>();
        authorizedPersons.add(this.driver);
        authorizedPersons.add(this.operator);
        this.flf = new FLF.Builder(authorizedPersons).build();
        this.loadingStation = new LoadingStation();
    }

    @Test
    void testOnePoleSocketExist(){assertNotNull(loadingStation.socket);}

    @Test
    void testFillStateIsEmpty() {
        assertEquals(0.0 , this.flf.getDrive().getRelativeFillState());
    }

    @Test
    void testFillStateIsNotEmpty() {
        this.flf.getDrive().load1000();
        assertEquals(1000 , this.flf.getDrive().getRelativeFillState());
        System.out.println(flf.getDrive().getAbsoluteFillState());
    }
}

