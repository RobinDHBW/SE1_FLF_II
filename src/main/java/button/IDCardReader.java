package button;

import idCard.IDCard;
import task2.CentralUnit;

public class IDCardReader extends Button {
    public IDCardReader(CentralUnit centralUnit) {
        super(centralUnit);
    }

    public void operateDevice(IDCard card) {
        ((CentralUnit) this.operatingDevice).toggleDoorLock(card.getAuthCode());
    }


}
