package mixingUnit;

import firefighting.CannonIdentifier;
import task9.ICannonVisitor;
import tank.*;
public interface IPort {

    public Integer getSprayCapacity(CannonIdentifier ci);

    public Integer AbsoluteFillState(TankSubject ts);

    public Double getTankFillState(TankSubject ts);

    public Integer getMixingRateValue();

    public MixingRate getMixingRate();

    public Boolean getCannonState(CannonIdentifier ident);

    public void spray(CannonIdentifier identifier);

    public void setSprayCapacityPerlIteration(CannonIdentifier ident, Integer amount);

    public void toggle(CannonIdentifier ident);

    public void fillComplete(Enum<?> input);

    public void fill(Enum<?> input, Integer quantity);

    public void changeMixingRate();

    public boolean Cannons(ICannonVisitor visitor);

}
