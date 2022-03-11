package task5;


import batteryManagement.BatteryManagement;

public class SocketBasedCharger {

    BatteryManagement batteryManagement;

    public SocketBasedCharger(BatteryManagement yeet){
        this.batteryManagement = yeet;
    }

    public void loadthreepoles(int amount, int polenumber) {

        int amn;
        int loaded;
        int send;

        System.out.println("Start charging to: " + amount);
        amn = amount;

        while(amn >= 299){

            loaded = amn-300;

            if(amn < 599){
                send = amn;
            }
            else{
                send = 300;
            }
            amn = loaded;

            switch (polenumber) {
                case 3:
                    System.out.println("Loading " + send + " Energy over Pole 1");
                    batteryManagement.fill(send);
                    break;
                case 2:
                    System.out.println("Loading " + send + " Energy over Pole 2");
                    batteryManagement.fill(send);
                    break;
                case 1:
                    System.out.println("Loading " + send + " Energy over Pole 3");
                    batteryManagement.fill(send);
                    break;
            }
            if(polenumber != 0) polenumber--;
            else polenumber =3;
        }
        System.out.println("Charging completed");
    }


}
