package testTask3;

import batteryManagement.Battery;
import batteryManagement.Coulomb;
import configuration.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask3 {
    private Battery battery;


    @BeforeEach
    void initRoutine() {
        battery = new Battery(Configuration.instance.mainCellPerBatterie, Configuration.instance.cellPerSubCell, Configuration.instance.subCellPerMainCell);
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
