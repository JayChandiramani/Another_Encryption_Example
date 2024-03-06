package com.jetbrain;

import javax.crypto.*;
import java.util.Scanner;

public class Main {

    private static final String UNICODE_FORMAT = "UTF-8";

    public static void main(String[] args) {
    String Text;
    Scanner Input = new Scanner(System.in);

    System.out.print("Enter message: ");
    Text = Input.nextLine();

    try {

        SecretKey key = generateKey("AES");
        Cipher chipher;
        chipher = Cipher.getInstance("AES");

        byte[] encryptedData = encryptString(Text,key,chipher);
        String encryptedString = new String(encryptedData);
        String decrypted = decryptString(encryptedData,key,chipher);
        System.out.print("Encrypted message (Using AES): ");
        System.out.println (encryptedString);
        System.out.print("Unencrypted message: ");
        System.out.println(decrypted);
        System.out.print("Key: ");
        System.out.println(key);

    }catch (Exception e){
        }
    }

    public static SecretKey generateKey (String encryptionType) {
        try {

            KeyGenerator keyGenerator = KeyGenerator.getInstance(encryptionType);
            SecretKey myKey = keyGenerator.generateKey();
            return myKey;

        } catch (Exception e){
            return null;
        }
    }

    public static byte[] encryptString (String dataToEncrypt, SecretKey myKey, Cipher cipher) {
        try{

            byte[] Text = dataToEncrypt.getBytes(UNICODE_FORMAT);
            cipher.init(Cipher.ENCRYPT_MODE,myKey);
            byte[] textEncrypted = cipher.doFinal(Text);
            return textEncrypted;

        }catch (Exception e) {
            return null;
        }
    }

    public static String decryptString (byte[] dataToDecrypt, SecretKey myKey, Cipher cipher) {
        try{

            cipher.init(Cipher.DECRYPT_MODE,myKey);
            byte[] textDecrypted = cipher.doFinal(dataToDecrypt);
            String result = new String(textDecrypted);
            return result;

        }catch (Exception e){
            return null;
        }
    }
}
