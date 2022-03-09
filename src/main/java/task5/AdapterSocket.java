package task5;

public class AdapterSocket extends SocketBasedCharger implements ICharger{

    public AdapterSocket(){
        polenumber = 3;
    }

    public void loadonepole(int amount, int polenumber){
        loadthreepoles(amount, polenumber);
    }

}
