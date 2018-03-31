package hybrid.crypto.graphy.decrypt;

import static hybrid.crypto.graphy.utils.KeyUtil.getFileInBytes;
import static hybrid.crypto.graphy.utils.KeyUtil.writeToFile;

import java.io.File;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;

import javax.crypto.Cipher;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

/**
 * @author Henry
 */
public class DecryptKey {

    private Cipher cipher;

    public DecryptKey(PrivateKey privateKey, File encryptedKeyReceived, File decreptedKeyFile, String algorithm)
            throws GeneralSecurityException, Base64DecodingException {

        this.cipher = Cipher.getInstance(algorithm);
        decryptFile(Base64.decode(getFileInBytes(encryptedKeyReceived)), decreptedKeyFile, privateKey);

    }

    public void decryptFile(byte[] input, File output, PrivateKey key)
            throws GeneralSecurityException {
        this.cipher.init(Cipher.DECRYPT_MODE, key);
        writeToFile(output, this.cipher.doFinal(input));
    }

}
