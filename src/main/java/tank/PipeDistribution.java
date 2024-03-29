package tank;

import firefighting.*;
import task1.MixingProcessorReflector;
import task2.centralUnitUtils.Subscriber;
import task9.ICannonVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static tank.TankSubject.FOAM;

public class PipeDistribution extends Subscriber {
    private final WaterCannonRoof waterCannonRoof;// = new WaterCannonRoof();
    private final WaterCannonFront waterCannonFront;// = new WaterCannonFront(90);
    private final ArrayList<WaterDieSelfprotection> waterDiesSelfprotection; // = new ArrayList<>();
    private final Tank foamTank;// = new Tank(FOAM, 75, 45, 10);
    private final Tank waterTank;// = new Tank(TankSubject.WATER, 75, 45, 30);
    private MixingRate mixingRate = MixingRate.NULL;
    private final MixingProcessorReflector mixingProcessor = new MixingProcessorReflector();


    public PipeDistribution(WaterCannonRoof waterCannonRoof, WaterCannonFront waterCannonFront, ArrayList<WaterDieSelfprotection> waterDiesSelfprotection, Tank foamTank, Tank waterTank) {
        super(003);
        this.waterCannonFront = waterCannonFront;
        this.waterCannonRoof = waterCannonRoof;
        this.waterDiesSelfprotection = waterDiesSelfprotection;
        this.foamTank = foamTank;
        this.waterTank = waterTank;
    }


    private void setMixingRate() {
        switch (this.mixingRate) {
            case NULL -> mixingProcessor.setMixingRate(0);
            case THREE -> mixingProcessor.setMixingRate(3);
            case FIVE -> mixingProcessor.setMixingRate(5);
            case TEN -> mixingProcessor.setMixingRate(10);
        }
    }

    private List<TankSubject> mix(Integer quantity) {
        setMixingRate();
        List<Integer> ratios = mixingProcessor.calcRatio(quantity);
        return this.mixingProcessor.mixTwoInputs(waterTank.remove(ratios.get(0)).stream().toList(), foamTank.remove(ratios.get(1)).stream().toList()).stream().map(e -> (TankSubject)e).toList();
    }

    public void changeMixingRate() {
        this.mixingRate = switch (this.mixingRate) {
            case NULL -> MixingRate.THREE;
            case THREE -> MixingRate.FIVE;
            case FIVE -> MixingRate.TEN;
            default -> MixingRate.NULL;
        };

    }

    public void fill(Enum<?> input, Integer quantity) {

        if (input.equals(FOAM)) {
            foamTank.fill(input, quantity);
        } else {
            waterTank.fill(input, quantity);
        }
    }

    public void fillComplete(Enum<?> input) {
        int toFill;
        Integer actualFillState = input.equals(FOAM) ? foamTank.getAbsoluteFillState() : waterTank.getAbsoluteFillState();

        if (input.equals(FOAM)) {
            toFill = foamTank.getCapacity() - actualFillState;
        } else {
            toFill = waterTank.getCapacity() - actualFillState;
        }

        this.fill(input, toFill);
    }

    public void toggle(CannonIdentifier ident) {
        switch (ident) {
            case CANNON_ROOF -> this.waterCannonRoof.toggle();
            case CANNON_FRONT -> this.waterCannonFront.toggle();
            case CANNON_SELFPROTECTION -> {
                for (WaterDieSelfprotection die : this.waterDiesSelfprotection) {
                    die.toggle();
                }
            }
        }
    }

    public void setSprayCapacityPerlIteration(CannonIdentifier ident, Integer amount) {
        switch (ident) {
            case CANNON_ROOF -> this.waterCannonRoof.setSprayCapacityPerlIteration(amount);
            case CANNON_FRONT -> this.waterCannonFront.setSprayCapacityPerlIteration(amount);
        }
    }

    /**
     * @param identifier - id of the Person
     */
    public void spray(CannonIdentifier identifier) {
        switch (identifier) {
            case CANNON_FRONT -> this.waterCannonFront.spray(this.mix(this.waterCannonFront.getSprayCapacityPerlIteration()));
            case CANNON_ROOF -> this.waterCannonRoof.spray(this.mix(this.waterCannonRoof.getSprayCapacityPerlIteration()));
            case CANNON_SELFPROTECTION -> {
                for (WaterDieSelfprotection die : this.waterDiesSelfprotection) {
                    die.spray(this.waterTank.remove(this.waterDiesSelfprotection.get(0).getSprayCapacityPerlIteration()).stream().map(e -> (TankSubject) e).collect(Collectors.toList()));
                }
            }
        }
    }

    public Boolean getCannonState(CannonIdentifier ident) {
        return switch (ident) {
            case CANNON_ROOF -> this.waterCannonRoof.getState();
            case CANNON_FRONT -> this.waterCannonFront.getState();
            case CANNON_SELFPROTECTION -> this.waterDiesSelfprotection.get(0).getState();
        };
    }

    public MixingRate getMixingRate() {
        return mixingRate;
    }

    public Integer getMixingRateValue() {
        return mixingProcessor.getMixingRate();
    }

    public Double getTankFillState(TankSubject ts) {
        return switch (ts) {
            case FOAM -> this.foamTank.getRelativeFillState();
            case WATER -> this.waterTank.getRelativeFillState();
        };
    }

    public Integer getAbsoluteFillState(TankSubject ts) {
        return switch (ts) {
            case FOAM -> this.foamTank.getAbsoluteFillState();
            case WATER -> this.waterTank.getAbsoluteFillState();
        };
    }

    public Integer getSprayCapacity(CannonIdentifier ci) {
        return switch (ci) {
            case CANNON_FRONT -> this.waterCannonFront.getSprayCapacityPerlIteration();
            case CANNON_ROOF -> this.waterCannonRoof.getSprayCapacityPerlIteration();
            case CANNON_SELFPROTECTION -> this.waterDiesSelfprotection.get(0).getSprayCapacityPerlIteration();
        };
    }

    public HashMap<WaterCannon,Boolean> checkCannons(ICannonVisitor visitor) {
        HashMap<WaterCannon,Boolean> cannonStates = new HashMap<>();
        cannonStates.put(this.waterCannonFront, this.waterCannonFront.selfCheck(visitor));
        cannonStates.put(this.waterCannonRoof, this.waterCannonRoof.selfCheck(visitor));
        for (WaterDieSelfprotection die : this.waterDiesSelfprotection) {
            cannonStates.put(die, die.selfCheck(visitor));
        }
        return cannonStates;
    }

    public void resetCannonSelfCheck(){
        this.waterCannonRoof.setSelfCheckPassed(false);
        this.waterCannonFront.setSelfCheckPassed(false);
        for (WaterDieSelfprotection die : this.waterDiesSelfprotection) {
            die.setSelfCheckPassed(false);
        }
    }

    public List<Boolean> getSelfCheckState(CannonIdentifier ident){
        List<Boolean> selfCheckStates = new ArrayList<>();
         switch (ident){
            case CANNON_FRONT -> selfCheckStates.add(waterCannonFront.getSelfCheckPassed());
             case CANNON_ROOF -> selfCheckStates.add(waterCannonRoof.getSelfCheckPassed());
             case CANNON_SELFPROTECTION -> selfCheckStates.addAll(waterDiesSelfprotection.stream().map(WaterCannon::getSelfCheckPassed).collect(Collectors.toList()));
        }
         return selfCheckStates;
    }
}
