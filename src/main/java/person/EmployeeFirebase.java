package person;

import drive.Drive;
import tank.PipeDistribution;
import tank.TankSubject;

public class EmployeeFirebase extends Person {

    PipeDistribution pipeDistribution;
    Drive drive;

    public EmployeeFirebase(String name) {
        super(name);
    }

    public void equip(PipeDistribution mixer, Drive drive) {
        this.pipeDistribution = mixer;
        this.drive = drive;
    }

    public void uneqip() {
        this.pipeDistribution = null;
        this.drive = null;
    }

    public void loadBatteries() {
        this.drive.fillComplete();
    }

    public void fillWaterTank() {
        this.pipeDistribution.fillComplete(TankSubject.WATER);
    }

    public void fillFoamTank() {
        this.pipeDistribution.fillComplete(TankSubject.FOAM);
    }
}
