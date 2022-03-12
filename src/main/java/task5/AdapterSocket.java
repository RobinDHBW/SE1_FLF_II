package task5;

import batteryManagement.BatteryManagement;

public class AdapterSocket extends SocketBasedCharger implements ICharger {

    public AdapterSocket(BatteryManagement batteryManagement) {
        super(batteryManagement);
    }

    public void loadonepole(int amount) {
        setPolenumber(3);
        loadthreepoles(amount, getPolenumber());
    }
}
