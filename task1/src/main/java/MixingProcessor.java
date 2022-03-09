import firefighting.*;
import mixingUnit.IPort;
import mixingUnit.MixingRate;
import task9.ICannonVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import tank.*;

public class MixingProcessor {

    public Port port;

    private static final MixingProcessor instance = new MixingProcessor();

    private final WaterCannonRoof waterCannonRoof = new WaterCannonRoof();
    private final WaterCannonFront waterCannonFront = new WaterCannonFront(90);
    private final ArrayList<WaterDieSelfprotection> waterDiesSelfprotection = new ArrayList<>();
    private final Tank foamTank = new Tank(TankSubject.FOAM, 75, 45, 10);
    private final Tank waterTank = new Tank(TankSubject.WATER, 75, 45, 30);
    private MixingRate mixingRate = MixingRate.NULL;

    private MixingProcessor() {
        port = new Port();
    }

    public static MixingProcessor getInstance() {
        return instance;
    }

    private Integer innercalcFoamPortion(Integer quantity) {
        return switch (this.mixingRate) {
            case NULL -> 0;
            case THREE -> (quantity / 100) * 3;
            case FIVE -> (quantity / 100) * 5;
            case TEN -> (quantity / 100) * 10;
        };
    }

    private List<TankSubject> innerMix(Integer quantity) {
        Integer foamPortion = innercalcFoamPortion(quantity);

        return Stream.concat(
                foamTank.remove(foamPortion).stream().map(e -> (TankSubject) e).toList().stream(),
                waterTank.remove(quantity - foamPortion).stream().map(e -> (TankSubject) e).toList().stream()).collect(Collectors.toList());

    }

    public void innerChangeMixingRate() {
        this.mixingRate = switch (this.mixingRate) {
            case NULL -> MixingRate.THREE;
            case THREE -> MixingRate.FIVE;
            case FIVE -> MixingRate.TEN;
            default -> MixingRate.NULL;
        };

    }

    public void innerFill(Enum<?> input, Integer quantity) {

        if (input.equals(TankSubject.FOAM)) {
            foamTank.fill(input, quantity);
        } else {
            waterTank.fill(input, quantity);
        }
    }

    public void innerFillComplete(Enum<?> input) {
        int toFill;
        Integer actualFillState = input.equals(TankSubject.FOAM) ? foamTank.getAbsoluteFillState() : waterTank.getAbsoluteFillState();

        if (input.equals(TankSubject.FOAM)) {
            toFill = foamTank.getCapacity() - actualFillState;
        } else {
            toFill = waterTank.getCapacity() - actualFillState;
        }

        this.innerFill(input, toFill);
    }

    public void innerToggle(CannonIdentifier ident) {
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

    public void innersetSprayCapacityPerlIteration(CannonIdentifier ident, Integer amount) {
        switch (ident) {
            case CANNON_ROOF -> this.waterCannonRoof.setSprayCapacityPerlIteration(amount);
            case CANNON_FRONT -> this.waterCannonFront.setSprayCapacityPerlIteration(amount);
        }
    }

    /**
     * @param identifier - id of the Person
     */
    public void innerSpray(CannonIdentifier identifier) {
        switch (identifier) {
            case CANNON_FRONT -> this.waterCannonFront.spray(this.innerMix(this.waterCannonFront.getSprayCapacityPerlIteration()));
            case CANNON_ROOF -> this.waterCannonRoof.spray(this.innerMix(this.waterCannonRoof.getSprayCapacityPerlIteration()));
            case CANNON_SELFPROTECTION -> {
                for (WaterDieSelfprotection die : this.waterDiesSelfprotection) {
                    die.spray(this.waterTank.remove(this.waterDiesSelfprotection.get(0).getSprayCapacityPerlIteration()).stream().map(e -> (TankSubject) e).collect(Collectors.toList()));
                }
            }
        }
    }

    public Boolean innergetCannonState(CannonIdentifier ident) {
        return switch (ident) {
            case CANNON_ROOF -> this.waterCannonRoof.getState();
            case CANNON_FRONT -> this.waterCannonFront.getState();
            case CANNON_SELFPROTECTION -> this.waterDiesSelfprotection.get(0).getState();
        };
    }

    public MixingRate innergetMixingRate() {
        return mixingRate;
    }

    public Integer innergetMixingRateValue() {
        return innercalcFoamPortion(100);
    }

    public Double innergetTankFillState(TankSubject ts) {
        return switch (ts) {
            case FOAM -> this.foamTank.getRelativeFillState();
            case WATER -> this.waterTank.getRelativeFillState();
        };
    }

    public Integer innergetAbsoluteFillState(TankSubject ts) {
        return switch (ts) {
            case FOAM -> this.foamTank.getAbsoluteFillState();
            case WATER -> this.waterTank.getAbsoluteFillState();
        };
    }

    public Integer innergetSprayCapacity(CannonIdentifier ci) {
        return switch (ci) {
            case CANNON_FRONT -> this.waterCannonFront.getSprayCapacityPerlIteration();
            case CANNON_ROOF -> this.waterCannonRoof.getSprayCapacityPerlIteration();
            case CANNON_SELFPROTECTION -> this.waterDiesSelfprotection.get(0).getSprayCapacityPerlIteration();
        };
    }

    public HashMap<WaterCannon,Boolean> innerCheckCannons(ICannonVisitor visitor) {
        HashMap<WaterCannon,Boolean> cannonStates = new HashMap<>();
        cannonStates.put(this.waterCannonFront, this.waterCannonFront.selfCheck(visitor));
        cannonStates.put(this.waterCannonRoof, this.waterCannonRoof.selfCheck(visitor));
        for (WaterDieSelfprotection die : this.waterDiesSelfprotection) {
            cannonStates.put(die, die.selfCheck(visitor));
        }
        return cannonStates;
    }

    public void innerResetCannonSelfCheck(){
        this.waterCannonRoof.setSelfCheckPassed(false);
        this.waterCannonFront.setSelfCheckPassed(false);
        for (WaterDieSelfprotection die : this.waterDiesSelfprotection) {
            die.setSelfCheckPassed(false);
        }
    }

    public List<Boolean> innerGetSelfCheckState(CannonIdentifier ident){
        List<Boolean> selfCheckStates = new ArrayList<>();
        switch (ident){
            case CANNON_FRONT -> selfCheckStates.add(waterCannonFront.getSelfCheckPassed());
            case CANNON_ROOF -> selfCheckStates.add(waterCannonRoof.getSelfCheckPassed());
            case CANNON_SELFPROTECTION -> selfCheckStates.addAll(waterDiesSelfprotection.stream().map(WaterCannon::getSelfCheckPassed).collect(Collectors.toList()));
        };
        return selfCheckStates;
    }


    public class Port implements IPort {

        @Override
        public Integer getSprayCapacity(CannonIdentifier ci){return innergetSprayCapacity(ci);}

        @Override
        public Integer AbsoluteFillState(TankSubject ts){return innergetAbsoluteFillState(ts);}

        @Override
        public Double getTankFillState(TankSubject ts){return innergetTankFillState(ts);}

        @Override
        public Integer getMixingRateValue(){return innergetMixingRateValue();}

        @Override
        public MixingRate getMixingRate(){return innergetMixingRate();}

        @Override
        public Boolean getCannonState(CannonIdentifier ident){return innergetCannonState(ident);}

        @Override
        public void spray(CannonIdentifier identifier){innerSpray(identifier);}

        @Override
        public void setSprayCapacityPerlIteration(CannonIdentifier ident, Integer amount){innersetSprayCapacityPerlIteration(ident, amount);}

        @Override
        public void toggle(CannonIdentifier ident){innerToggle(ident);}

        @Override
        public void fillComplete(Enum<?> input){innerFillComplete(input);}

        @Override
        public void fill(Enum<?> input, Integer quantity){innerFill(input, quantity);}

        @Override
        public void changeMixingRate(){innerChangeMixingRate();}

        @Override
        public HashMap<WaterCannon, Boolean> checkCannons(ICannonVisitor visitor) {
            return innerCheckCannons(visitor);
        }

        @Override
        public void resetCannonSelfCheck() {
            innerResetCannonSelfCheck();
        }

        @Override
        public List<Boolean> getSelfCheckState(CannonIdentifier ident) {
            return innerGetSelfCheckState(ident);
        }
    }
}
