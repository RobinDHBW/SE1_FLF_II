package task3;

import batteryManagement.Coulomb;

import javax.swing.plaf.ColorUIResource;
import java.util.ArrayList;
import java.util.List;

public class SubCell extends Cell{
    protected List<Cell> cells;
    public SubCell(Integer id){
        super(id);
        this.cells = new ArrayList<>();
    }

    public void addCell(Cell c){
        cells.add(c);
    }

    public void fill(Coulomb coulomb, Integer quantity){
        try{
            if(getCapacity()-getAbsoluteFillState() < quantity) throw new Exception("Not enough capacity to fill given energie");
            for (Cell c : cells){
                c.fill(coulomb);
            }
        }catch (Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public Integer getCapacity(){
        return this.cells.stream().mapToInt(Cell::getCapacity).sum();
    }

    public Integer getAbsoluteFillState() {
        return cells.stream().filter(c -> !c.isEmpty()).mapToInt(Cell::getCapacity).sum();
    }
}
