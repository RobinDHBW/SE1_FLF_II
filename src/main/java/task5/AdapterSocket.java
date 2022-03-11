package task5;

import batteryManagement.BatteryManagement;

public class AdapterSocket extends SocketBasedCharger implements ICharger {

    public int polenumber = 3;

    public AdapterSocket(BatteryManagement batteryManagement) {
        super(batteryManagement);
    }

    public void loadonepole(int amount) {
        loadthreepoles(amount, polenumber);
    }
}
