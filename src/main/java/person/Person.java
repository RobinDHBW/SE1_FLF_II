package person;

import centralUnit.CryptoUnit;
import configuration.Configuration;
import idCard.IDCard;

public abstract class Person {

    protected final String name;
    protected final IDCard idCard;
    private final CryptoUnit cryptoUnit = new CryptoUnit();
    protected Boolean isInVehicle = false;

    public Person(String name) {
        this.name = name;
        this.idCard = new IDCard(cryptoUnit.encrypt(Configuration.instance.cuIdent + "-" + this.name + "-" + Configuration.instance.cuCode, Configuration.instance.cuCode));
    }

    public void setIsInVehicle(Boolean isIn) {
        this.isInVehicle = isIn;
    }

    public String getName() {
        return name;
    }
}