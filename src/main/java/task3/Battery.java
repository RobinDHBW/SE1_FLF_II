package task3;

import batteryManagement.Coulomb;
import store.IStoreMedium;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        for (MainCell m : topLevelCells) {
            int k = 0;
            for (SubCell s : midLevelCells) {
                for (int z = 0; z < 3; z++) {
                    s.addCell(lowLevelCells.get(k++));
                }
            }
            for (int z = 0; z < 4; z++) {
                m.addCell(midLevelCells.get(i++));
            }
            this.mainCells.add(topLevelCells.get(j++));
        }

    }

    @Override
    public void fill(Object input, Integer quantity) {
        for (MainCell m : mainCells) {
            if (m.fill((Coulomb) input)) quantity--;
            if (quantity == 0) return;
        }
    }

    @Override
    public List<Object> remove(Integer quantity) {
        List<Object> removedList = new ArrayList<>();
        for (MainCell m : mainCells) {
            Coulomb removed = m.remove();
            if (Objects.nonNull(removed)) {
                quantity--;
                removedList.add(removed);
            }
        }
        return removedList;
    }

    @Override
    public Double getRelativeFillState() {
        return 1.0 / (getCapacity() / getAbsoluteFillState());
    }

    @Override
    public Integer getAbsoluteFillState() {
        return mainCells.stream().mapToInt(CellContainer::getAbsoluteFillState).sum();
    }

    public Integer getCapacity() {
        return mainCells.stream().mapToInt(CellContainer::getCapacity).sum();
    }
}
