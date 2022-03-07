package task3;

import batteryManagement.Coulomb;

import java.util.ArrayList;
import java.util.List;

public class SubCell extends Cell {
    protected List<Cell> cells;

    public SubCell(Integer id) {
        super(id);
        this.cells = new ArrayList<>();
    }

    public void addCell(Cell c) {
        cells.add(c);
    }

    public void fill(Coulomb coulomb, Integer quantity) {
        try {
            if (getCapacity() - getAbsoluteFillState() < quantity)
                throw new Exception("Not enough capacity to fill given energie");
            for (Cell c : cells) {
                if (quantity == 0) return;
                if (c.fill(coulomb)) quantity--;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public List<Object> remove(Integer quantity) {
        try {
            List<Object> removed = new ArrayList<>();
            if (getAbsoluteFillState() < quantity) throw new Exception("Not enough stored!");
            for (Cell c : cells) {
                if (quantity == 0) break;
                removed.add(c.remove());
                quantity--;
            }
            return removed;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public Integer getCapacity() {
        return this.cells.stream().mapToInt(Cell::getCapacity).sum();
    }

    public Integer getAbsoluteFillState() {
        return (int) cells.stream().filter(c -> !c.isEmpty()).count();
    }
}
