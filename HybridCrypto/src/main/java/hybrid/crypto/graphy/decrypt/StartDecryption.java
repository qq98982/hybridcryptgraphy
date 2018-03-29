package hybrid.crypto.graphy.decrypt;

import static hybrid.crypto.graphy.utils.KeyUtil.getPrivate;
import static hybrid.crypto.graphy.utils.KeyUtil.getSecretKey;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class StartDecryption {

    public static void main(String[] args) throws IOException, GeneralSecurityException {
        File encryptedKeyReceived = new File("EncryptedFiles/encryptedSecretKey");
        File decreptedKeyFile = new File("DecryptedFiles/SecretKey");
        new DecryptKey(getPrivate("KeyPair/privateKey_Bob", "RSA"),
                       encryptedKeyReceived, decreptedKeyFile, "RSA");

        File encryptedFileReceived = new File("EncryptedFiles/encryptedFile");
        File decryptedFile = new File("DecryptedFiles/decryptedFile");
        new DecryptData(encryptedFileReceived, decryptedFile,
                        getSecretKey("DecryptedFiles/SecretKey", "AES"), "AES");
    }
}
