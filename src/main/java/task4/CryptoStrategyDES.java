package task4;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class CryptoStrategyDES implements ICryptoStrategy {
    private final KeySchedule keySchedule = new KeySchedule();
    private final InitialPermutation initialPermutation = new InitialPermutation();
    private final FeistelNetwork feistelNetwork = new FeistelNetwork();
    private final IPInverse ipInverse = new IPInverse();

    public String pushToNextByte(String input) {
        int nextByte = (int) (Math.pow(2, (int) (Math.log(input.length()) / Math.log(2)) + 1));
        StringBuilder inputBuilder = new StringBuilder(input);
        while (inputBuilder.length() < nextByte) {
            inputBuilder.insert(0, "0");
        }
        input = inputBuilder.toString();
        return input;
    }

    public String pushTo64Bit(String input) {
        StringBuilder inputBuilder = new StringBuilder(input);
        while (inputBuilder.length() < 64) {
            inputBuilder.insert(0, "0");
        }
        input = inputBuilder.toString();
        return input;
    }

    public String stringToBit(String input) {
        return this.pushToNextByte(new BigInteger(input.getBytes(StandardCharsets.UTF_8)).toString(2));
    }

    public String bitToString(String input) {
        return new String(new BigInteger(input, 2).toByteArray());
    }

    @Override
    public String decrypt(String cipher, String key) {
        StringBuilder res = new StringBuilder();
        int limit = cipher.length() % 64 > 0 ? (cipher.length() / 64) + 1 : cipher.length() / 64;
        for (int i = 0; i < limit; i++) {
            int index = i * 64;
            String toProcess = cipher.substring(index, index + (Math.min(cipher.length() - index, 64)));
            res.append(this.bitToString(this.ipInverse.permute(this.feistelNetwork.iterate(this.initialPermutation.permute(pushTo64Bit(toProcess)), this.keySchedule.schedule(pushTo64Bit(this.stringToBit(key)), false)))));
        }
        return res.toString();
    }

    @Override
    public String encrypt(String plain, String key) {
        StringBuilder res = new StringBuilder();
        int limit = plain.length() % 8 > 0 ? (plain.length() / 8) + 1 : plain.length() / 8;
        for (int i = 0; i < limit; i++) {
            int index = i * 8;
            String toProcess = plain.substring(index, index + (Math.min(plain.length() - index, 8)));
            res.append(this.ipInverse.permute(this.feistelNetwork.iterate(this.initialPermutation.permute(pushTo64Bit(stringToBit(toProcess))), this.keySchedule.schedule(pushTo64Bit(stringToBit(key)), true))));
        }
        return res.toString();
    }

}
