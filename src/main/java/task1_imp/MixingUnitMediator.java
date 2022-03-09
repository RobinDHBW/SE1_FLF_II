package task1_imp;

import firefighting.CannonIdentifier;
import firefighting.WaterCannonFront;
import firefighting.WaterCannonRoof;
import firefighting.WaterDieSelfprotection;
import tank.MixingRate;
import tank.Tank;
import tank.TankSubject;

import java.lang.reflect.Method;
import java.util.ArrayList;

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

    public void fill() {
        try {
            Method method = port.getClass().getMethod("fill");
            method.invoke(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fillComplete(TankSubject foam) {
        try {
            Method method = port.getClass().getMethod("fillComplete");
            method.invoke(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void toggle(CannonIdentifier ident) {
        try {
            Method method = port.getClass().getMethod("toggle");
            method.invoke(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setSprayCapacityPerlIteration(CannonIdentifier cannonRoof, Integer amountPerIteration) {
        try {
            Method method = port.getClass().getMethod("setSprayCapacityPerlIteration");
            method.invoke(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void spray(CannonIdentifier ident) {
        try {
            Method method = port.getClass().getMethod("spray");
            method.invoke(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean getCannonState(CannonIdentifier ident) {
        try {
            Method method = port.getClass().getMethod("getCannonState");
            method.invoke(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public MixingRate getMixingRate() {
        try {
            Method method = port.getClass().getMethod("getMixingRate");
            method.invoke(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer getMixingRateValue() {
        try {
            Method method = port.getClass().getMethod("getMixingRateValue");
            method.invoke(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Double getTankFillState(TankSubject foam) {
        try {
            Method method = port.getClass().getMethod("getTankFillState");
            method.invoke(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer getAbsoluteFillState(TankSubject water) {
        try {
            Method method = port.getClass().getMethod("getAbsoluteFillState");
            method.invoke(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer getSprayCapacity(CannonIdentifier cannonSelfprotection) {
        try {
            Method method = port.getClass().getMethod("getSprayCapacity");
            method.invoke(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

