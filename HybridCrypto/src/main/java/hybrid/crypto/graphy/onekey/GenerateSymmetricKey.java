package hybrid.crypto.graphy.onekey;

import static hybrid.crypto.graphy.utils.KeyUtil.writeToFile;

import java.security.SecureRandom;

import javax.crypto.spec.SecretKeySpec;

public class GenerateSymmetricKey {
    private SecretKeySpec secretKeySpec;

    public GenerateSymmetricKey(int length, String algorithm) {
        SecureRandom rd = new SecureRandom();
        byte[] key = new byte[length];
        rd.nextBytes(key);
        this.secretKeySpec = new SecretKeySpec(key, algorithm);
    }

    private SecretKeySpec getKey(){
        return this.secretKeySpec;
    }

    public static void main(String[] args) {
        GenerateSymmetricKey generateSymmetricKey = new GenerateSymmetricKey(16, "AES");
        writeToFile("OneKey/secretKey",generateSymmetricKey.getKey().getEncoded());
    }
}
