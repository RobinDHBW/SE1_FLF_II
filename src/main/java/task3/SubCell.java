package task3;

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

    public Integer getCapacity(){
        return this.cells.stream().mapToInt(Cell::getCapacity).sum();
    }

    public Integer getAbsoluteFillState() {
        return cells.stream().filter(c -> !c.isEmpty()).mapToInt(Cell::getCapacity).sum();
    }
}
