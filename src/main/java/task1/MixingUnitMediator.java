package task1;

import firefighting.*;
import tank.MixingRate;
import tank.Tank;
import tank.TankSubject;
import task9.ICannonVisitor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MixingUnitMediator {

    Comp_loader loader;
    Object port;

    public MixingUnitMediator(WaterCannonRoof waterCannonRoof, WaterCannonFront waterCannonFront, ArrayList<WaterDieSelfprotection> waterDieSelfprotection, Tank foamTank, Tank waterTank){
        this.loader = new Comp_loader();
        this.port = loader.getPort();
    }

    public void changeMixingRate() {
        try {
            Method method = port.getClass().getMethod("changeMixingRate");
            method.invoke(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fill(TankSubject input, Integer quantity) {
        try {
            Method method = port.getClass().getMethod("fill", TankSubject.class, Integer.class);
            method.invoke(port, input, quantity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fillComplete(TankSubject input) {
        try {
            Method method = port.getClass().getMethod("fillComplete", TankSubject.class);
            method.invoke(port, input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void toggle(CannonIdentifier ident) {
        try {
            Method method = port.getClass().getMethod("toggle", CannonIdentifier.class);
            method.invoke(port, ident);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setSprayCapacityPerlIteration(CannonIdentifier cannonRoof, Integer amountPerIteration) {
        try {
            Method method = port.getClass().getMethod("setSprayCapacityPerlIteration", CannonIdentifier.class, Integer.class);
            method.invoke(port, cannonRoof, amountPerIteration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void spray(CannonIdentifier ident) {
        try {
            Method method = port.getClass().getMethod("spray", CannonIdentifier.class);
            method.invoke(port, ident);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Boolean getCannonState(CannonIdentifier ident) {
        try {
            Method method = port.getClass().getMethod("getCannonState", CannonIdentifier.class);
            return (Boolean) method.invoke(port, ident);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public MixingRate getMixingRate() {
        try {
            Method method = port.getClass().getMethod("getMixingRate");
            return (MixingRate)method.invoke(port);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Integer getMixingRateValue() {
        try {
            Method method = port.getClass().getMethod("getMixingRateValue");
           return(Integer) method.invoke(port);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Double getTankFillState(TankSubject foam) {
        try {
            Method method = port.getClass().getMethod("getTankFillState", TankSubject.class);
           return (Double) method.invoke(port, foam);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Integer getAbsoluteFillState(TankSubject water) {
        try {
            Method method = port.getClass().getMethod("getAbsoluteFillState", TankSubject.class);
           return (Integer) method.invoke(port, water);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Integer getSprayCapacity(CannonIdentifier cannonSelfprotection) {
        try {
            Method method = port.getClass().getMethod("getSprayCapacity", CannonIdentifier.class);
            return (Integer)method.invoke(port, cannonSelfprotection);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public HashMap<WaterCannon,Boolean> checkCannons(ICannonVisitor visitor) {
        try {
            Method method = port.getClass().getMethod("checkCannons", ICannonVisitor.class);
            return (HashMap<WaterCannon,Boolean>) method.invoke(port, visitor);
        }catch (Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public void resetCannonSelfCheck(){
        try{
            Method method = port.getClass().getMethod("resetCannonSelfCheck");
            method.invoke(port);
        }catch (Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Boolean> getSelfCheckState(CannonIdentifier ident){
        try{
            Method method = port.getClass().getMethod("getSelfCheckState", CannonIdentifier.class);
            return (List<Boolean>)method.invoke(port, ident);
        }catch (Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }


}

