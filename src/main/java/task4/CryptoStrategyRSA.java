package task4;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class CryptoStrategyRSA implements ICryptoStrategy {
    private final PrivateKey privateKey;
    private final PublicKey publicKey;
    public CryptoStrategyRSA(){
        KeyPair pair = this.generateKeys();
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();
    }

    private KeyPair generateKeys() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            return  keyGen.generateKeyPair();
        }catch (Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public String encrypt(String plain, String key) {
        try{
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, this.publicKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(plain.getBytes()));
        }catch (Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public String decrypt(String cipher, String key) {
        try{
            Cipher cipherInstance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipherInstance.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(cipherInstance.doFinal(Base64.getDecoder().decode(cipher)));
        }catch (Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }
}
