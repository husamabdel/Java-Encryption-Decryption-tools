import javax.swing.*;
import javax.crypto.*;
import java.security.*;
import java.io.*;
import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;
import java.util.Base64;
import javax.crypto.spec.*;
public class crypterTest {
    
    public static SecretKeySpec returner(String key)throws NoSuchAlgorithmException{

        SecretKeySpec keyspec;
        keyspec = new SecretKeySpec(key.getBytes(), "AES");
        return keyspec;
        
    }

    public static void encrypt()throws Exception{
       
        Cipher cipher = Cipher.getInstance("AES");
        String key = JOptionPane.showInputDialog("Please enter a Password");
        SecretKeySpec spec = returner(key);
        String TEXT = JOptionPane.showInputDialog("Enter the text to encrypted: ");
        byte[] enc = TEXT.getBytes();
        cipher.init(Cipher.ENCRYPT_MODE, spec);
        byte[] after = cipher.doFinal(enc);
        String encrypted_text = new String(after);
        System.out.println(encrypted_text);
        JOptionPane.showMessageDialog(null, encrypted_text, "Encrypted", JOptionPane.PLAIN_MESSAGE);
    
    }

    public static void decrypt()throws Exception{

        Cipher cipher = Cipher.getInstance("AES");
        String key = JOptionPane.showInputDialog("Please enter the Password");
        SecretKeySpec spec = returner(key);
        String TEXT = JOptionPane.showInputDialog("Enter the text to decrypted: ");
        byte[] enc = TEXT.getBytes();
        cipher.init(Cipher.DECRYPT_MODE, spec);
        byte[] after = cipher.doFinal(enc);
        String encrypted_text = new String(after);
        JOptionPane.showMessageDialog(null, encrypted_text, "decrypted", JOptionPane.PLAIN_MESSAGE);
    }



    public static void main(String[] args) throws Exception {
        
        encrypt();
        decrypt();



    }


}
