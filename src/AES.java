import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import static java.nio.charset.StandardCharsets.UTF_8;

public class AES {

  static String IV = "1234567890123456";

  static String plaintext = "test text 123456";

  static String encryptionKey = "1234567890123456";

  public static void main(String[] args) throws Exception {
    System.out.println("plain:   " + plaintext);

    byte[] cipher = encrypt(plaintext, encryptionKey);

    System.out.print("cipher:  ");
    for (int i = 0; i < cipher.length; i++) {
      System.out.print(hex(cipher[i]) + " ");
    }
    System.out.println("");

    String decrypted = decrypt(cipher, encryptionKey);

    System.out.println("decrypt: " + decrypted);
  }

  public static byte[] encrypt(String plainText, String encryptionKey) throws Exception {
    Cipher cipher = cipher();
    cipher.init(Cipher.ENCRYPT_MODE, secretKey(encryptionKey), iv(IV));
    return cipher.doFinal(plainText.getBytes(UTF_8));
  }

  public static String decrypt(byte[] cipherText, String encryptionKey) throws Exception {
    Cipher cipher = cipher();
    cipher.init(Cipher.DECRYPT_MODE, secretKey(encryptionKey), iv(IV));
    return new String(cipher.doFinal(cipherText), UTF_8);
  }

  public static Cipher cipher() throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException {
    return Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
  }

  public static SecretKeySpec secretKey(String encryptionKey) throws UnsupportedEncodingException {
    return new SecretKeySpec(encryptionKey.getBytes(UTF_8), "AES");
  }

  public static IvParameterSpec iv(String vi) {
    return new IvParameterSpec(vi.getBytes(UTF_8));
  }

  public static String hex(int c) {
    return Integer.toHexString(c & 0xFF);
  }

}