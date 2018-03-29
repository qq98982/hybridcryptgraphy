package hybrid.crypto.graphy.encrypt;

import static hybrid.crypto.graphy.utils.KeyUtil.getPublicKey;
import static hybrid.crypto.graphy.utils.KeyUtil.getSecretKey;

import java.io.File;

public class StartEncryption {

    public static void main(String[] args) {
        File originalKeyFile = new File("OneKey/secretKey");
        File encyptedKeyFile = new File("EncryptedFiles/encryptedSecretKey");
        new EncryptKey(getPublicKey("KeyPair/publicKey_Bob", "RSA"), originalKeyFile,
                       encyptedKeyFile, "RSA");
        File originalFile = new File("secretfile");
        File encryptedFile = new File("EncryptedFiles/encryptedFile");
        new EncryptData(originalFile, encryptedFile, getSecretKey("OneKey/secretKey", "AES"),
                        "AES");
    }
}
