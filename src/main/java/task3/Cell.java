package task3;

import batteryManagement.Coulomb;

import java.util.Objects;

public class Cell {

    protected Coulomb energy;
    protected Integer id;

    public Cell(Integer id) {
        this.id = id;
        this.energy = null;
    }


    public Boolean fill(Coulomb coulomb) {
        if (Objects.isNull(this.energy)) {
            this.energy = coulomb;
            return true;
        }
        return false;
    }

    public Object remove() {
        try {
            if (Objects.isNull(this.energy)) throw new Exception("Store empty");
            Object removed = this.energy;
            this.energy = null;
            return removed;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public Integer getCapacity() {
        return 1;
    }

    public Boolean isEmpty() {
        return Objects.isNull(energy);
    }

}
