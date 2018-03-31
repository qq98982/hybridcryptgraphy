package hybrid.crypto.graphy.decrypt;

import static hybrid.crypto.graphy.utils.KeyUtil.getFileInBytes;
import static hybrid.crypto.graphy.utils.KeyUtil.writeToFile;

import java.io.File;
import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

/**
 * @author Henry
 */
public class DecryptData {
    private Cipher cipher;

    public DecryptData(File encryptedFileReceived, File decryptedFile, SecretKeySpec secretKey,
                       String algorithm)
            throws GeneralSecurityException, Base64DecodingException {

        this.cipher = Cipher.getInstance(algorithm);
        decryptFile(Base64.decode(getFileInBytes(encryptedFileReceived)), decryptedFile, secretKey);
    }

    public void decryptFile(byte[] input, File output, SecretKeySpec key)
            throws GeneralSecurityException {
        this.cipher.init(Cipher.DECRYPT_MODE, key);
        writeToFile(output, this.cipher.doFinal(input));
    }

}
