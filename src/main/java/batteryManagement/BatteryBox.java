package batteryManagement;

import task3.Battery;
import task3.Coulomb;

import java.util.ArrayList;
import java.util.List;

public class BatteryBox {
    private final ArrayList<Battery> batteryStore = new ArrayList<>();

    public BatteryBox(Integer width, Integer height) {

        for (int i = 0; i < width * height; i++) {
            batteryStore.add(new Battery(new Coulomb(), 100, 10, 100));
        }
    }

    public void fill(Integer quantity) {
        for (Battery b : batteryStore) {
            if(quantity == 0) return;
            Integer cap = b.getCapacity() - b.getAbsoluteFillState();
            Integer toFill = (cap >= quantity) ? quantity : cap;
            b.fill(new Coulomb(), toFill);
            quantity -= toFill;
            //b.fill(new Coulomb(), quantity / batteryStore.size());
        }
    }

    public List<Coulomb> remove(Integer quantity) {
        List<Coulomb> output = new ArrayList<>();
        for (Battery b : batteryStore) {
            if (quantity == 0) break;
            Integer fillState = b.getAbsoluteFillState();
            Integer toRemove = quantity;
            if (quantity > fillState) toRemove = fillState;

            output.addAll(b.remove(toRemove).stream().map(x -> (Coulomb) x).toList());
            quantity -= toRemove;
        }
        return output;
    }

    public Double getRelativeFillState() {
        return batteryStore.stream()
                .mapToDouble(Battery::getRelativeFillState)
                .average()
                .orElse(0);
    }

    public Integer getAbsoluteFillState() {
        return batteryStore.stream()
                .mapToInt(Battery::getAbsoluteFillState)
                .sum();
    }

    public Integer getCapacity() {
        return batteryStore.stream()
                .mapToInt(Battery::getCapacity)
                .sum();
    }
}
