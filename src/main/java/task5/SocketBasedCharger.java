package task5;


import batteryManagement.BatteryManagement;

public class SocketBasedCharger {

    BatteryManagement batman;

    public SocketBasedCharger(BatteryManagement batteryManagement){
        batman = batteryManagement;
    }

    public void loadthreepoles(int amount, int polenumber) {
        System.out.println("Start charging to: " + amount);
        while(polenumber != 0){
            switch (polenumber) {
                case 3:
                    System.out.println("Loading 300 Energy over Pole 1");
                    int pole1Load = amount - 700;
                    batman.fill(pole1Load);
                    break;
                case 2:
                    System.out.println("Loading 300 Energy over Pole 2");
                    int pole2Load = amount - 700;
                    batman.fill(pole2Load);
                    break;
                case 1:
                    System.out.println("Loading 400 Energy over Pole 3");
                    int pole3Load = amount - 600;
                    batman.fill(pole3Load);
                    break;
            }
            polenumber--;
        }
        System.out.println("Charging completed");
    }


}
