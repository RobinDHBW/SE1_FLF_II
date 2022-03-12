package testCryptoUnit;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import task4.CryptoStrategyDES;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCryptUnit {
    private final CryptoStrategyDES cryptoUnit = new CryptoStrategyDES();

    @TestFactory
    Stream<DynamicTest> testCryptoWithMullerExample() {
        ArrayList<DynamicTest> tests = new ArrayList<>();
        String plain = "DHBW MOS";
        String key = cryptoUnit.bitToString("0000000000000000000000000000000000000000011111111111111111111111");

        String cipher = cryptoUnit.encrypt(plain, key);

        Collections.addAll(tests,
                DynamicTest.dynamicTest("check string to bit", () -> assertEquals("0100010001001000010000100101011100100000010011010100111101010011", cryptoUnit.stringToBit(plain))),
                DynamicTest.dynamicTest("check bit to string", () -> assertEquals(plain, cryptoUnit.bitToString(cryptoUnit.stringToBit(plain)))),
                DynamicTest.dynamicTest("check cipher", () -> assertEquals("1000100010000100100000011010101100010000100011101000111110100011", cipher)),
                DynamicTest.dynamicTest("check decrypted plain", () -> assertEquals(plain, this.cryptoUnit.decrypt(cipher, key)))
        );
        return tests.stream();
    }

    @TestFactory
    Stream<DynamicTest> testCryptoWithProjectData() {
        ArrayList<DynamicTest> tests = new ArrayList<>();
        String plain = "FT-DUS-FLF-5";
        String key = "6072";

        String cipher = cryptoUnit.encrypt(plain, key);

        Collections.addAll(tests,
                DynamicTest.dynamicTest("check string to bit", () -> assertEquals(cryptoUnit.pushToNextByte("010001100101010000101101010001000101010101010011001011010100011001001100010001100010110100110101"), cryptoUnit.stringToBit(plain))),
                DynamicTest.dynamicTest("check bit to string", () -> assertEquals(plain, cryptoUnit.bitToString(cryptoUnit.stringToBit(plain)))),
                DynamicTest.dynamicTest("check cipher", () -> assertEquals("10001001101010000001111010001000101010101010001100011110100010010000000000000000000000000000000010001100100010010001111000111010", cipher)),
                DynamicTest.dynamicTest("check decrypted plain", () -> assertEquals(plain, this.cryptoUnit.decrypt(cipher, key)))
        );
        return tests.stream();
    }
}
