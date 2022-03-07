package task3;

import batteryManagement.Coulomb;
import store.IStoreMedium;

import java.util.ArrayList;
import java.util.List;

public class Battery implements IStoreMedium {
    private List<MainCell> mainCells;

    public Battery(Object subject, Integer length, Integer height, Integer width) {
        //super(length, height, width, subject);

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
                Integer fillState = c.getAbsoluteFillState();
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
//        List<Object> removedList = new ArrayList<>();
//        Integer count = 0;
//        for (MainCell m : mainCells) {
//            Integer cap = m.getAbsoluteFillState();
//
//        }
//
//        for (int i = 0; i < count; i++) {
//            removedList.add(new Coulomb());
//        }
//        return removedList;
    }

    @Override
    public Double getRelativeFillState() {
        return 1.0 / (getCapacity() / getAbsoluteFillState());
    }

    @Override
    public Integer getAbsoluteFillState() {
        Integer fill = mainCells.stream().mapToInt(MainCell::getAbsoluteFillState).sum();
        return mainCells.stream().mapToInt(MainCell::getAbsoluteFillState).sum();
    }

    public Integer getCapacity() {
        return mainCells.stream().mapToInt(MainCell::getCapacity).sum();
    }
}
