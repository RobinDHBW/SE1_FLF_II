package person;

import task4.CryptoStrategyAES;
import task4.CryptoStrategyDES;
import task4.CryptoStrategyRSA;
import task4.ICryptoStrategy;
import configuration.Configuration;
import idCard.IDCard;

public abstract class Person {

    protected final String name;
    protected final IDCard idCard;
    private final ICryptoStrategy cryptoUnit;
    protected Boolean isInVehicle = false;

    public Person(String name) {
        this.name = name;
        this.cryptoUnit = switch (Configuration.instance.encryptionStrategy) {
            case AES -> new CryptoStrategyAES(Configuration.instance.cuSalt);
            case RSA -> new CryptoStrategyRSA();
            default -> new CryptoStrategyDES();
        };
        this.idCard = new IDCard(cryptoUnit.encrypt(Configuration.instance.cuIdent + "-" + this.name + "-" + Configuration.instance.cuCode, Configuration.instance.cuCode));
    }

    public void setIsInVehicle(Boolean isIn) {
        this.isInVehicle = isIn;
    }

    public String getName() {
        return name;
    }
}