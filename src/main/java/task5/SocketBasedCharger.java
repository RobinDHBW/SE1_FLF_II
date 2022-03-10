package task5;


import batteryManagement.BatteryManagement;

public class SocketBasedCharger {

    BatteryManagement batman;

    public SocketBasedCharger(BatteryManagement batteryManagement){
        batman = batteryManagement;
    }

    public void loadthreepoles(int amount, int polenumber) {
        int i = polenumber;
        System.out.println("Start charging to: " + amount);
        while (i != 0){
            switch (i){
                case 3:
                    System.out.println("Loading 300 Energy over Pole 1");
                    batman.fill(amount-700);
                    i--;
                case 2:
                    System.out.println("Loading 300 Energy over Pole 2");
                    batman.fill(amount-700);
                    i--;
                case 1:
                    System.out.println("Loading 400 Energy over Pole 3");
                    batman.fill(amount-600);
                    i--;
            }
        }
        System.out.println("Charging completed");
    }


}
