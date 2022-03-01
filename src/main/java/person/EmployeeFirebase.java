package person;

import drive.Drive;
import tank.MixingProcessor;
import tank.TankSubject;

public class EmployeeFirebase extends Person {

    MixingProcessor mixingProcessor;
    Drive drive;

    public EmployeeFirebase(String name) {
        super(name);
    }

    public void equip(MixingProcessor mixer, Drive drive) {
        this.mixingProcessor = mixer;
        this.drive = drive;
    }

    public void uneqip() {
        this.mixingProcessor = null;
        this.drive = null;
    }

    public void loadBatteries() {
        this.drive.fillComplete();
    }

    public void fillWaterTank() {
        this.mixingProcessor.fillComplete(TankSubject.WATER);
    }

    public void fillFoamTank() {
        this.mixingProcessor.fillComplete(TankSubject.FOAM);
    }
}
