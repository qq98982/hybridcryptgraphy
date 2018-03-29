package hybrid.crypto.graphy.keypair;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import hybrid.crypto.graphy.utils.KeyUtil;

public class GenerateKeys {
    private KeyPairGenerator keyGen;
    private PublicKey publicKey;
    private PrivateKey privateKey;

    public GenerateKeys(int length) {
        try {
            this.keyGen = KeyPairGenerator.getInstance("RSA");
            this.keyGen.initialize(length);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public void creatKeys() {
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
        GenerateKeys alice = new GenerateKeys(1024);
        GenerateKeys bob = new GenerateKeys(1024);
        alice.creatKeys();
        bob.creatKeys();

        KeyUtil.writeToFile("KeyPair/publicKey_Alice", alice.getPublicKey().getEncoded());
        KeyUtil.writeToFile("KeyPair/privateKey_Alice", alice.getPrivateKey().getEncoded());
        KeyUtil.writeToFile("KeyPair/publicKey_Bob", bob.getPublicKey().getEncoded());
        KeyUtil.writeToFile("KeyPair/privateKey_Bob", bob.getPrivateKey().getEncoded());
    }
}
