package task4;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class CryptoStrategyAES implements ICryptoStrategy {
    private final String salt;
    public CryptoStrategyAES(String salt){
        this.salt=salt;
    }

    @Override
    public String encrypt(String plain, String key) {
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(factory.generateSecret(new PBEKeySpec(key.toCharArray(), this.salt.getBytes(), 65536, 256)).getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));

            return Base64.getEncoder().encodeToString(cipher.doFinal(plain.getBytes(StandardCharsets.UTF_8)));
        }catch (Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public String decrypt(String cipher, String key) {
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0};
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(factory.generateSecret(new PBEKeySpec(key.toCharArray(), this.salt.getBytes(),65536, 256)).getEncoded(), "AES");

            Cipher cipherInstance = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipherInstance.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));

            return new String(cipherInstance.doFinal(Base64.getDecoder().decode(cipher)));
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }
}
