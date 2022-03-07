package task3;

import batteryManagement.Coulomb;

import java.util.ArrayList;
import java.util.List;

public class MainCell extends Cell {
    protected List<SubCell> cells;

    public MainCell(Integer id) {
        super(id);
        this.cells = new ArrayList<>();
    }

    public void fill(Coulomb coulomb, Integer quantity) {
        for (SubCell c : cells) {
            Integer cap = c.getCapacity() - c.getAbsoluteFillState();
            Integer toFill = (cap >= quantity) ? quantity : cap;
            c.fill(coulomb, toFill);
            quantity -= toFill;
        }
    }

    public void addCell(SubCell c) {
        cells.add(c);
    }

    public Integer getCapacity() {
        return this.cells.stream().mapToInt(SubCell::getCapacity).sum();
    }

    public Integer getAbsoluteFillState() {
        return cells.stream().mapToInt(SubCell::getAbsoluteFillState).sum();
    }
}
