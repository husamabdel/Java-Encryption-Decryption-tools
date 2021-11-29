import javax.swing.*;
import javax.crypto.*;
import java.security.*;
import java.io.*;
import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;
import java.util.Base64;
import javax.crypto.spec.*;

public class passwdenc{

    private static SecretKey key;


    public static Key returner()throws NoSuchAlgorithmException{

        key = KeyGenerator.getInstance("AES").generateKey();
        
        return key;
    }
public static void main(String[] args) throws Exception {
    
    while(true){
    String ANS =JOptionPane.showInputDialog("Please type E to encrypt or D to decrypt data", "E");

        if(ANS.equalsIgnoreCase("e")){
            ENCDIR();
                break;
        }
            else if(ANS.equalsIgnoreCase("d")){
                DECDIR();
                    break; 
            }
                else{
                    JOptionPane.showMessageDialog(null, "Error! please type a valid answer!", "AES 256", JOptionPane.ERROR_MESSAGE);
                }
            }
}

public static String FUNCTION_ENCRYPT_ELEMENTS(String data)throws Exception{

    byte[] dataToEnc = data.getBytes();
    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.ENCRYPT_MODE, returner());
    byte[] encr = cipher.doFinal(dataToEnc);
    String Enc = new String(encr);

    return Enc;
    }
    public static String FUNCTION_DECRYPT_ELEMENTS(String data, SecretKey key)throws Exception{

        byte[] dataToDec = data.getBytes();
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decrypted = cipher.doFinal(dataToDec);
        String Enc = new String(decrypted);
    
        return Enc;
        }
        
        
        
    public static void ENCDIR() throws Exception{
            String encodedKey = Base64.getEncoder().encodeToString(returner().getEncoded());
            PrintWriter output = new PrintWriter("C:/Packages/ENCRYPTED.txt");
            output.println(encodedKey);
            String dat = JOptionPane.showInputDialog("Please input password you wish to encrypt", "AES 256");
            
            JOptionPane.showMessageDialog(null, "The UTF-8 formatted text was successfully encrypted", "AES 256", JOptionPane.OK_OPTION);
            JOptionPane.showMessageDialog(null, FUNCTION_ENCRYPT_ELEMENTS(dat), "AES 256", JOptionPane.OK_OPTION);
            output.println(FUNCTION_ENCRYPT_ELEMENTS(dat));
            output.close();
        }


    public static void DECDIR()throws Exception{


        String ans = JOptionPane.showInputDialog("Please input the Secret key: ");
        byte[] decodedKey = Base64.getDecoder().decode(ans);
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
        String msg = JOptionPane.showInputDialog("Please input the encrypted data: ");
        FUNCTION_DECRYPT_ELEMENTS(msg, originalKey);
        JOptionPane.showMessageDialog(null, "The UTF-8 formatted text was successfully decrypted", "AES 256", JOptionPane.OK_OPTION);
        JOptionPane.showMessageDialog(null, FUNCTION_DECRYPT_ELEMENTS(msg, originalKey), "AES 256", JOptionPane.OK_OPTION);

    }    

    }