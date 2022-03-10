package testTask2;

import flf.FLF;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import person.Driver;
import person.Operator;
import person.Person;
import task5.LoadingStation;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestTask2 {

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
        this.loadingStation = new LoadingStation();
    }

    @Test
    void testLights(){
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
    void testEngine(){
        assertFalse(flf.getDrive().getEngineState());
        flf.getCabin().getCentralUnit().switchEngines();
        assertTrue(flf.getDrive().getEngineState());
    }

}
