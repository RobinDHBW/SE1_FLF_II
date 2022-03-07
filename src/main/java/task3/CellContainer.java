package task3;

import batteryManagement.Coulomb;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CellContainer extends Cell{
    protected List<Cell> cells;

    public CellContainer(Integer id){
        super(id);
        this.cells = new ArrayList<>();
    }

    public void addCell(Cell c){
        cells.add(c);
    }

    public Boolean isComposite(){
        return !cells.isEmpty();
    }

    public Boolean fill(Coulomb coulomb){
        for(Cell c : cells){
            if(c.fill(coulomb)){
                return true;
            }
        }
        return false;
    }

    public Coulomb remove(){
        Coulomb removed;
        for(Cell c : cells){
            removed = c.remove();
            if(Objects.nonNull(removed)) return removed;
        }
        return null;
    }

    public Integer getCapacity(){
        return this.cells.stream().mapToInt(Cell::getCapacity).sum();
    }

    public Integer getAbsoluteFillState() {
        return cells.stream().filter(c -> !c.isEmpty()).mapToInt(Cell::getCapacity).sum();
    }

}
