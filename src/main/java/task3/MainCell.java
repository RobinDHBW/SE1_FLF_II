package task3;

import batteryManagement.Coulomb;

import java.util.ArrayList;
import java.util.List;

public class MainCell extends Cell {
    //protected List<SubCell> cells;

    public MainCell(Integer id) {
        super(id);
        //this.cells = new ArrayList<>();
    }

//    public void fill(Coulomb coulomb, Integer quantity) {
//        for (Cell c : cells) {
//            Integer cap = c.getCapacity() - c.getAbsoluteFillState();
//            Integer toFill = (cap >= quantity) ? quantity : cap;
//            c.fill(coulomb, toFill);
//            quantity -= toFill;
//        }
//    }

    public List<Object> remove(Integer quantity){
        try{
            List<Object> removed = new ArrayList<>();
            if (getAbsoluteFillState() < quantity) throw new Exception("Not enough stored!");
            for(Cell c : cells){
                if(quantity == 0) break;
                Integer fillState = c.getAbsoluteFillState();
                if(fillState == 0)continue;
                Integer toRemove = fillState >= quantity ? quantity : fillState;
                removed.addAll(c.remove(toRemove));
                quantity -= toRemove;
            }
            return removed;
        }catch (Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public void addCell(SubCell c) {
        cells.add(c);
    }

    public Integer getCapacity() {
        return this.cells.stream().mapToInt(Cell::getCapacity).sum();
    }

    public Integer getAbsoluteFillState() {
        return cells.stream().mapToInt(Cell::getAbsoluteFillState).sum();
    }
}
