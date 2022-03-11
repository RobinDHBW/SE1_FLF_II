package testTask4;

import configuration.Configuration;
import org.junit.jupiter.api.Test;
import task4.CryptoStrategyAES;
import task4.CryptoStrategyDES;
import task4.CryptoStrategyRSA;
import task4.ICryptoStrategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask4 {
    private ICryptoStrategy cryptoStrategy;
    private String test ="The quick brown fox jumps over the lazy dog";
    private String cuCode = Configuration.instance.cuCode;

    @Test
    void testAESStrategy(){
        this.cryptoStrategy = new CryptoStrategyAES(Configuration.instance.cuSalt);
        assertEquals(test,cryptoStrategy.decrypt(cryptoStrategy.encrypt(test, cuCode), cuCode));
    }

    @Test
    void testRSAStrategy(){
        this.cryptoStrategy = new CryptoStrategyRSA();
        assertEquals(test,cryptoStrategy.decrypt(cryptoStrategy.encrypt(test, cuCode), cuCode));
    }

    @Test
    void testDESStrategy(){
        this.cryptoStrategy = new CryptoStrategyDES();
        assertEquals(test,cryptoStrategy.decrypt(cryptoStrategy.encrypt(test, cuCode), cuCode));
    }
}
