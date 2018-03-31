package hybrid.crypto.graphy;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.GeneralSecurityException;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;

import hybrid.crypto.graphy.decrypt.StartDecryption;
import hybrid.crypto.graphy.encrypt.StartEncryption;
import hybrid.crypto.graphy.keypair.GenerateKeyRsaPairs;
import hybrid.crypto.graphy.onekey.GenerateSymmetricKey;

/**
 * @author Henry
 */
public class App {
    public static void main(String[] args) throws GeneralSecurityException, Base64DecodingException,
                                                  IOException {
        System.out.println("Original file content: ");
        Files.newBufferedReader(new File("secretFile").toPath()).lines().forEach(System.out::println);
        System.out.println("*****************************************\n");
        GenerateKeyRsaPairs.main(null);
        System.out.println("RUN: Generate rsa key pairs");

        GenerateSymmetricKey.main(null);
        System.out.println("RUN: Generate symmetric key");

        StartEncryption.main(null);
        System.out.println("\nEncrypted file content: ");
        Files.newBufferedReader(new File("EncryptedFiles/encryptedFile").toPath()).lines().forEach(
                System.out::println);
        System.out.println("*****************************************\n");
        StartDecryption.main(null);
        System.out.println("Decrypted file content: ");
        Files.newBufferedReader(new File("DecryptedFiles/decryptedFile").toPath()).lines().forEach(
                System.out::println);
    }
}
