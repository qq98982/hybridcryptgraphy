package hybrid.crypto.graphy.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.spec.SecretKeySpec;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

/**
 * @author Henry
 */
public class KeyUtil {
    public static byte[] getFileInBytes(File f) {
        try (InputStream inputStream = new FileInputStream(f)) {
            byte[] bytes = new byte[(int) f.length()];
            inputStream.read(bytes);
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeToFile(File output, byte[] bytes) {
        final File parentFile = output.getParentFile();
        if (!Files.exists(parentFile.toPath())) {
            final boolean mkdirs = parentFile.mkdirs();
            if (!mkdirs) {
                return;
            }
        }

        try (FileOutputStream fos = new FileOutputStream(output);) {
            fos.write(bytes);
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SecretKeySpec getSecretKey(String fileName, String algorithm) {
        try {
            byte[] bytes = Files.readAllBytes(new File(fileName).toPath());
            return new SecretKeySpec(Base64.decode(bytes), algorithm);
        } catch (IOException | Base64DecodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static PrivateKey getPrivate(String fileName, String algorithm) {
        PrivateKey privateKey = null;
        try {
            final byte[] bytes = Files.readAllBytes(new File(fileName).toPath());
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decode(bytes));
            privateKey = KeyFactory.getInstance(algorithm).generatePrivate(
                    pkcs8EncodedKeySpec);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException | Base64DecodingException e) {
            e.printStackTrace();
        }
        return privateKey;
    }

    public static PublicKey getPublicKey(String fileName, String algorithm) {
        PublicKey publicKey = null;
        try {
            final byte[] bytes = Files.readAllBytes(new File(fileName).toPath());
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decode(bytes));
            final KeyFactory instance = KeyFactory.getInstance(algorithm);
            publicKey = instance.generatePublic(x509EncodedKeySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return publicKey;
    }

    public static void writeToFile(String path, byte[] bytes) {
        KeyUtil.writeToFile(new File(path), bytes);
    }

}
