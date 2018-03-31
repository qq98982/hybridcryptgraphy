package hybrid.crypto.graphy.encrypt;

import static hybrid.crypto.graphy.utils.KeyUtil.getFileInBytes;
import static hybrid.crypto.graphy.utils.KeyUtil.writeToFile;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import com.sun.org.apache.xml.internal.security.utils.Base64;

/**
 * @author Henry
 */
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
                writeToFile(encrypted, Base64.encode(this.cipher.doFinal(fileInBytes)).getBytes(
                        StandardCharsets.UTF_8));
            } catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
                e.printStackTrace();
            }
        }
    }


}
