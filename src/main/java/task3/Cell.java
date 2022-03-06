package task3;

import batteryManagement.Coulomb;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cell {

    protected Coulomb energy;
    protected Integer id;

    public Cell(Integer id){
        this.id=id;
        this.energy =null;
    }



    public Boolean fill(Coulomb coulomb){
        if(Objects.isNull(this.energy)){
            this.energy = coulomb;
            return true;
        }
        return false;
    }

    public Coulomb remove(){
        if(Objects.nonNull(this.energy)){
            Coulomb removed = this.energy;
            this.energy = null;
            return removed;
        }
        return null;
    }

    public Integer getCapacity(){
        return 1;
    }

    public Boolean isEmpty(){
        return Objects.isNull(energy);
    }

}
