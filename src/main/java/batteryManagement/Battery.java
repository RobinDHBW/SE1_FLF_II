package batteryManagement;

import store.IStoreMedium;
import task3.Cell;
import task3.MainCell;
import task3.SubCell;

import java.util.ArrayList;
import java.util.List;

public class Battery implements IStoreMedium {
    private final List<MainCell> mainCells;

    public Battery(Integer length, Integer height, Integer width) {
        this.mainCells = new ArrayList<>();

        //Instanziierung Topdown
        List<MainCell> topLevelCells = new ArrayList<>();
        List<SubCell> midLevelCells = new ArrayList<>();
        List<Cell> lowLevelCells = new ArrayList<>();

        Integer id = 0;
        for (int i = 0; i < width; i++) {
            topLevelCells.add(new MainCell(id++));
            for (int j = 0; j < length; j++) {
                midLevelCells.add(new SubCell(id++));
                for (int k = 0; k < height; k++) {
                    lowLevelCells.add(new Cell(id++));
                }
            }
        }

        //Zusammensetzung Bottom up
        int i = 0;
        int j = 0;
        for (SubCell s : midLevelCells) {
            for (int z = 0; z < height; z++) {
                s.addCell(lowLevelCells.get(j++));
            }
        }
        for (MainCell m : topLevelCells) {
            for (int z = 0; z < width; z++) {
                m.addCell(midLevelCells.get(i++));
            }
            this.mainCells.add(m);
        }

    }

    @Override
    public void fill(Object input, Integer quantity) {
        try {
            for (MainCell m : mainCells) {
                if(quantity == 0) return;
                Integer cap = m.getCapacity() - m.getAbsoluteFillState();
                Integer toFill = (cap >= quantity) ? quantity : cap;
                m.fill((Coulomb) input, toFill);
                quantity -= toFill;
            }
            if(quantity>0) throw new Exception("Could not fill given quantity into battery, left:" + quantity);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }

    }

    @Override
    public List<Object> remove(Integer quantity) {
        try{
            List<Object> removed = new ArrayList<>();
            if (getAbsoluteFillState() < quantity) throw new Exception("Not enough stored!");
            for(MainCell c : mainCells){
                if(quantity ==0)break;
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

    @Override
    public Double getRelativeFillState() {
        Integer fillState = getAbsoluteFillState();
        if(fillState == 0) return 0.0;
        return 1.0 / (getCapacity() / fillState);
    }

    @Override
    public Integer getAbsoluteFillState() {
        return mainCells.stream().mapToInt(MainCell::getAbsoluteFillState).sum();
    }

    public Integer getCapacity() {
        return mainCells.stream().mapToInt(MainCell::getCapacity).sum();
    }
}
