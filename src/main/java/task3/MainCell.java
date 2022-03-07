package task3;

import java.util.ArrayList;
import java.util.List;

public class MainCell extends Cell{
    protected List<SubCell> cells;
    public MainCell(Integer id){
        super(id);
        this.cells = new ArrayList<>();
    }

    public void addCell(SubCell c){
        cells.add(c);
    }

    public Integer getCapacity(){
        return this.cells.stream().mapToInt(SubCell::getCapacity).sum();
    }

    public Integer getAbsoluteFillState() {
        return cells.stream().mapToInt(SubCell::getCapacity).sum();
    }
}
