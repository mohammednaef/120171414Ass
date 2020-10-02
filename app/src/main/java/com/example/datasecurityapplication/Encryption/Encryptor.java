package com.example.datasecurityapplication.Encryption;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class Encryptor {
    private byte[] key;
    private static final String ALGORITHM = "AES";

    public Encryptor(byte[] key)
    {
        this.key = key;
    }

    public byte[] encrypt(byte[] plainText) throws Exception
    {
        SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        return cipher.doFinal(plainText);
    }

    public byte[] decrypt(byte[] cipherText) throws Exception
    {
        SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        return cipher.doFinal(cipherText);
    }

    // الاستدعاء
//    byte[] encryptionKey = "MZygpewJsCpRrfOr".getBytes(StandardCharsets.UTF_8);
//    byte[] plainText = text.getBytes(StandardCharsets.UTF_8);
//
//    Encryptor advancedEncryptionStandard = new Encryptor(encryptionKey);
//
//    byte[] cipherText = advancedEncryptionStandard.encrypt(plainText);
//
//    byte[] decryptedCipherText = advancedEncryptionStandard.decrypt(cipherText);
//
//    // System.out.println(new String(plainText));
//        System.out.println(new String(cipherText));
//    //  System.out.println(new String(decryptedCipherText));


}
