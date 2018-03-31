package hybrid.crypto.graphy.keypair;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.xml.bind.DatatypeConverter;

import hybrid.crypto.graphy.utils.KeyUtil;

/**
 * @author Henry
 */
public class GenerateKeyRsaPairs {

    private KeyPairGenerator keyGen;

    private PublicKey publicKey;

    private PrivateKey privateKey;

    public GenerateKeyRsaPairs(int length) {
        try {
            this.keyGen = KeyPairGenerator.getInstance("RSA");
            this.keyGen.initialize(length);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public void createKeys() {
        final KeyPair keyPair = keyGen.generateKeyPair();
        this.privateKey = keyPair.getPrivate();
        this.publicKey = keyPair.getPublic();
    }

    private PrivateKey getPrivateKey() {
        return this.privateKey;
    }

    private PublicKey getPublicKey() {
        return this.publicKey;
    }

    public static void main(String[] args) {
        GenerateKeyRsaPairs alice = new GenerateKeyRsaPairs(1024);
        GenerateKeyRsaPairs bob = new GenerateKeyRsaPairs(1024);
        alice.createKeys();
        bob.createKeys();
        KeyUtil.writeToFile("KeyPair/publicKey_Alice",
                            DatatypeConverter.printBase64Binary(alice.getPublicKey().getEncoded()).getBytes(
                                    StandardCharsets.UTF_8));
        KeyUtil.writeToFile("KeyPair/privateKey_Alice",
                            DatatypeConverter.printBase64Binary(alice.getPrivateKey().getEncoded())
                                             .getBytes(StandardCharsets.UTF_8));
        KeyUtil.writeToFile("KeyPair/publicKey_Bob",
                            DatatypeConverter.printBase64Binary(bob.getPublicKey().getEncoded())
                                             .getBytes(StandardCharsets.UTF_8));
        KeyUtil.writeToFile("KeyPair/privateKey_Bob",
                            DatatypeConverter.printBase64Binary(bob.getPrivateKey().getEncoded())
                                             .getBytes(StandardCharsets.UTF_8));
    }
}
