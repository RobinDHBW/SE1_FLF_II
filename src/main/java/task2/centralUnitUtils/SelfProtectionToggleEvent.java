package task2.centralUnitUtils;

import firefighting.CannonIdentifier;
import tank.PipeDistribution;

public class SelfProtectionToggleEvent {
    public SelfProtectionToggleEvent(PipeDistribution pipeDistribution){
        pipeDistribution.toggle(CannonIdentifier.CANNON_SELFPROTECTION);
        pipeDistribution.spray(CannonIdentifier.CANNON_SELFPROTECTION);
        pipeDistribution.toggle(CannonIdentifier.CANNON_SELFPROTECTION);
    }
}
