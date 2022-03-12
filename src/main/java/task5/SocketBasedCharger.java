package task5;


import batteryManagement.BatteryManagement;

public class SocketBasedCharger {

    BatteryManagement batteryManagement;
    int polenumber;

    protected SocketBasedCharger(BatteryManagement bat) {
        this.batteryManagement = bat;
    }

    public void loadthreepoles(int amount, int plnb) {

        int amn;
        int loaded;
        int send;

        this.polenumber = plnb;

        //System.out.println("Start charging to: " + amount);
        amn = amount;

        while (amn >= 299) {

            loaded = amn - 300;

            if (amn < 599) {
                send = amn;
            } else {
                send = 300;
            }
            amn = loaded;

            switch (polenumber) {
                case 3 -> batteryManagement.fill(send);
                case 2 -> batteryManagement.fill(send);
                case 1 -> batteryManagement.fill(send);
            }
            if (polenumber != 0) polenumber--;
            else polenumber = 3;
        }
        //System.out.println("Charging completed");
    }

    public int getPolenumber() {
        return polenumber;
    }

    public void setPolenumber(int polenumber) {
        this.polenumber = polenumber;
    }


}
