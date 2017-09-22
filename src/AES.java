import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.crypto.Cipher;

import sun.misc.BASE64Decoder;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {

  static String IV = "1234567890123456";
  static String plaintext = "test text 123456"; /* Note null padding */
  static String encryptionKey = "1234567890123456";

  public static void main(String[] args) throws Exception {
    try {
      System.out.println("==Java==");
      System.out.println("plain:   " + plaintext);

      byte[] cipher = encrypt(plaintext, encryptionKey);

      System.out.print("cipher:  ");
      for (int i = 0; i < cipher.length; i++)
        System.out.print(Integer.toHexString(new Integer(cipher[i] & 0xFF))
          + " ");
      System.out.println("");

      String decrypted = decrypt(cipher, encryptionKey);

      System.out.println("decrypt: " + decrypted);

    }
    catch (Exception e) {
      e.printStackTrace();
    }
    {
      BASE64Decoder decoder = new BASE64Decoder();
      String plainText =
        new String(decoder.decodeBuffer(new ByteArrayInputStream(
          "QmVybmFyZG8gQnJlZGVy".getBytes())));
      System.out.println("PlainText : " + plainText);
      byte[] encodedBytes =
        decoder.decodeBuffer(new ByteArrayInputStream(
          "zioI76eBuJRjQZWwU+kKRQ==".getBytes()));
      System.out.println("DecryptText : "
        + decrypt(encodedBytes, encryptionKey));
    }
  }

  public static byte[] encrypt(String plainText, String encryptionKey)
    throws Exception {
    Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
    SecretKeySpec key =
      new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
    cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV
      .getBytes("UTF-8")));
    return cipher.doFinal(plainText.getBytes("UTF-8"));
  }

  public static String decrypt(byte[] cipherText, String encryptionKey)
    throws Exception {
    Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
    SecretKeySpec key =
      new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
    cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV
      .getBytes("UTF-8")));
    return new String(cipher.doFinal(cipherText), "UTF-8");
  }
}