package hybrid.crypto.graphy.decrypt;

import static hybrid.crypto.graphy.utils.KeyUtil.getFileInBytes;
import static hybrid.crypto.graphy.utils.KeyUtil.writeToFile;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class DecryptData {
    private Cipher cipher;

    public DecryptData(File encryptedFileReceived, File decryptedFile, SecretKeySpec secretKey,
                       String algorithm)
            throws IOException, GeneralSecurityException {

        this.cipher = Cipher.getInstance(algorithm);
        decryptFile(getFileInBytes(encryptedFileReceived), decryptedFile, secretKey);

    }

    public void decryptFile(byte[] input, File output, SecretKeySpec key)
            throws IOException, GeneralSecurityException {
        this.cipher.init(Cipher.DECRYPT_MODE, key);
        writeToFile(output, this.cipher.doFinal(input));
    }

}
