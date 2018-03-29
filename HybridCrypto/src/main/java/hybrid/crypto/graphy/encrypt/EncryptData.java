package hybrid.crypto.graphy.encrypt;

import static hybrid.crypto.graphy.utils.KeyUtil.getFileInBytes;
import static hybrid.crypto.graphy.utils.KeyUtil.writeToFile;

import java.io.File;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class EncryptData {

    private Cipher cipher;

    EncryptData(File originaFile, File encrypted, SecretKeySpec secretKey, String cipherAlgorithm) {
        try {
            this.cipher = Cipher.getInstance(cipherAlgorithm);
            encryptFile(getFileInBytes(originaFile), encrypted, secretKey);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    private void encryptFile(byte[] fileInBytes, File encrypted, SecretKeySpec secretKey) {
        if (null != fileInBytes) {
            try {
                this.cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                writeToFile(encrypted, this.cipher.doFinal(fileInBytes));
            } catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
                e.printStackTrace();
            }
        }
    }


}
