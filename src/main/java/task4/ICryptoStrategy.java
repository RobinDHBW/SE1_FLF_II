package task4;

public interface ICryptoStrategy {
    String encrypt(String plain, String key);
    String decrypt(String cipher, String key);
}
