package batteryManagement;

import task3.Coulomb;

import java.util.List;

public enum BatteryManagement {
    instance;
    private final BatteryBox batteryBox = new BatteryBox(2, 2);

    public List<Coulomb> remove(Integer quantity) {
        return batteryBox.remove(quantity);
    }

    public void fill(Integer quantity) {
        batteryBox.fill(quantity);
    }

    public void fillComplete() {
        this.fill(batteryBox.getCapacity() - batteryBox.getAbsoluteFillState());
    }

    public Double getRelativeFillState() {
        return batteryBox.getRelativeFillState();
    }

    public Integer getAbsoluteFillState() {
        return batteryBox.getAbsoluteFillState();
    }

    public Integer getCapacity() {
        return batteryBox.getCapacity();
    }

}
