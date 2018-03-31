package hybrid.crypto.graphy.encrypt;

import static hybrid.crypto.graphy.utils.KeyUtil.writeToFile;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import hybrid.crypto.graphy.utils.KeyUtil;

/**
 * @author Henry
 */
public class EncryptKey {
    private Cipher cipher;
    EncryptKey(PublicKey publicKey, File originalKeyFile, File encryptedKeyFile, String ciptherAlgorithm){
        try {
            this.cipher = Cipher.getInstance(ciptherAlgorithm);
            encrpytFile(KeyUtil.getFileInBytes(originalKeyFile), encryptedKeyFile, publicKey);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    private void encrpytFile(byte[] bytesOfFile, File encryptedKeyFile, PublicKey publicKey) {
        try {
            this.cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            writeToFile(encryptedKeyFile, Base64.encode(this.cipher.doFinal(bytesOfFile)).getBytes(
                    StandardCharsets.UTF_8));
        } catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }


}
