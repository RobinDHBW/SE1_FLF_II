package testTask3;

import batteryManagement.Coulomb;
import configuration.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task3.Battery;

import static org.junit.jupiter.api.Assertions.*;

public class TestTask3 {
    private Battery battery;


    @BeforeEach
    void initRoutine() {
        battery = new Battery(new Coulomb(),Configuration.instance.mainCellPerBatterie, Configuration.instance.cellPerSubCell, Configuration.instance.subCellPerMainCell);
    }

    @Test
    void testCompositeSize(){
        assertEquals(Configuration.instance.mainCellPerBatterie* Configuration.instance.cellPerSubCell* Configuration.instance.subCellPerMainCell,this.battery.getCapacity());
    }

    @Test
    void testCompositeStructure(){
        Integer cap = this.battery.getCapacity();
        this.battery.fill(new Coulomb(), cap);
        assertEquals(Configuration.instance.mainCellPerBatterie* Configuration.instance.cellPerSubCell* Configuration.instance.subCellPerMainCell,this.battery.getAbsoluteFillState());
    }
}
