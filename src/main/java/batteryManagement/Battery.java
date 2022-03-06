package batteryManagement;

import configuration.Configuration;
import store.StoreMedium;

import java.util.ArrayList;
import java.util.List;

public class Battery extends StoreMedium {
    private List<MainCell> mainCells;

    public Battery(Object subject, Integer length, Integer height, Integer width) {
        super(length, height, width, subject);

        //Instanziierung Topdown
        List<MainCell> topLevelCells = new ArrayList<>();
        List<SubCell> midLevelCells = new ArrayList<>();
        List<Cell> lowLevelCells = new ArrayList<>();

        for (int i = 0; i < Configuration.instance.mainCellPerBatterie; i++) {
            topLevelCells.add(new MainCell());
            for (int j = 0; j < Configuration.instance.subCellPerMainCell; j++) {
                midLevelCells.add(new SubCell());
                for (int k = 0; k < Configuration.instance.cellPerSubCell; k++) {
                    lowLevelCells.add(new Cell());
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
        super.fill(input, quantity);
    }

    @Override
    public List<Object> remove(Integer quantity) {
        return super.remove(quantity);
    }
}
