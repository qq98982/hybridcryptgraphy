package hybrid.crypto.graphy.encrypt;

import static hybrid.crypto.graphy.utils.KeyUtil.writeToFile;

import java.io.File;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import hybrid.crypto.graphy.utils.KeyUtil;

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
            writeToFile(encryptedKeyFile, this.cipher.doFinal(bytesOfFile));
        } catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }


}
