package batteryManagement;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    protected List<Cell> cells;

    public Cell(){
        this.cells = new ArrayList<>();
    }

    public void addCell(Cell c){
        cells.add(c);
    }

    public Boolean isComposite(){
        return !cells.isEmpty();
    }

}
