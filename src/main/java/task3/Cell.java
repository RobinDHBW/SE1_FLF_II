package task3;

import batteryManagement.Coulomb;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cell {

    protected List<Cell> cells;
    protected Coulomb energy;
    protected Integer id;

    public Cell(Integer id) {
        this.id = id;
        this.energy = null;
        cells = new ArrayList<>();
    }


    public void fill(Coulomb coulomb, Integer quantity) {
        if (isComposite()) {
            for (Cell c : cells) {
                Integer cap = c.getCapacity() - c.getAbsoluteFillState();
                Integer toFill = (cap >= quantity) ? quantity : cap;
                c.fill(coulomb, toFill);
                quantity -= toFill;
            }
        } else {
            if (Objects.isNull(this.energy)) {
                this.energy = coulomb;
            }
        }
    }

    public List<Object> remove(Integer quantity) {
        try {
            List<Object> removed = new ArrayList<>();
            if (isComposite()) {
                if (getAbsoluteFillState() < quantity) throw new Exception("Not enough stored!");
                for (Cell c : cells) {
                    if (quantity == 0) break;
                    Integer fillState = c.getAbsoluteFillState();
                    if (fillState == 0) continue;
                    Integer toRemove = fillState >= quantity ? quantity : fillState;
                    removed.addAll(c.remove(toRemove));
                    quantity -= toRemove;
                }
                return removed;
            } else {
                if (Objects.isNull(this.energy)) throw new Exception("Store empty");
                removed.add(this.energy);
                this.energy = null;
                return removed;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public void addCell(Cell c) {
        this.cells.add(c);
    }

    public Boolean isEmpty() {
        return Objects.isNull(energy);
    }

    public Boolean isComposite() {
        return !cells.isEmpty();
    }

    public Integer getCapacity() {
        if (!isComposite()) return 1;
        return this.cells.stream().mapToInt(Cell::getCapacity).sum();
    }

    public Integer getAbsoluteFillState() {
        if (!isComposite() && !isEmpty()) return 1;
        return this.cells.stream().mapToInt(Cell::getAbsoluteFillState).sum();
    }
}
