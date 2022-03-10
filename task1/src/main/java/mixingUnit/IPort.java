package mixingUnit;

import firefighting.CannonIdentifier;
import firefighting.WaterCannon;
import tank.TankSubject;
import task9.ICannonVisitor;

import java.util.HashMap;
import java.util.List;

public interface IPort {

    Integer getSprayCapacity(CannonIdentifier ci);

    Integer AbsoluteFillState(TankSubject ts);

    Double getTankFillState(TankSubject ts);

    Integer getMixingRateValue();

    MixingRate getMixingRate();

    Boolean getCannonState(CannonIdentifier ident);

    void spray(CannonIdentifier identifier);

    void setSprayCapacityPerlIteration(CannonIdentifier ident, Integer amount);

    void toggle(CannonIdentifier ident);

    void fillComplete(Enum<?> input);

    void fill(Enum<?> input, Integer quantity);

    void changeMixingRate();

    HashMap<WaterCannon, Boolean> checkCannons(ICannonVisitor visitor);

    void resetCannonSelfCheck();

    List<Boolean> getSelfCheckState(CannonIdentifier ident);


}
