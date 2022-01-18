import java.util.Scanner;
import java.io.*;
import java.nio.file.Files;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Desktop.*;
import java.awt.event.*;
import java.util.*;
import java.security.*;

public class cryptor extends JFrame{
    
    private static String select;
    private static File file;
    private static SecretKey key;
    private static byte [] content;
    private JButton button;
    private JButton button2;
    private JLabel label;
    private JPanel panel;

    // Constructor
    public cryptor(){


        this.setTitle("File Encrption");
        this.setSize(300,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panels();
        this.add(panel);
        this.setVisible(true);

    }

    //sets the contents
    public void panels(){

        panel = new JPanel();
        label = new JLabel("Select a file and hit encrypt");
        button = new JButton("select file");
        button.addActionListener(new getfile());
        button2 = new JButton("encrypt");
        button2.addActionListener(new writeBytesToFile());

        panel.add(label);
        panel.add(button);
        panel.add(button2);

    }
    
    //sets the file names
    //public static void setFileName(){

        //file = new File(fileSelected());

    //}

    //gets the byte array of the file
    public static byte[] getFileByte()throws Exception{


        return content = Files.readAllBytes(file.toPath());

    }

    //encrypts the file, called in the next one
    public static byte[] encryptFile(byte[] fileContent)throws Exception{

        fileContent = content;

        key = KeyGenerator.getInstance("AES").generateKey();
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(content);

    }

    //writes bytes to the new file.
    
private class writeBytesToFile implements ActionListener{
    public void actionPerformed(ActionEvent e){

    try{
        DataOutputStream stream = new DataOutputStream( new FileOutputStream(file.getAbsolutePath()+".PWNED"));
        for(int x = 0; x < getFileByte().length; x++){
            stream.writeByte(encryptFile(getFileByte())[x]);
        }
        stream.close();
        file.delete();
        }

        catch(Exception e1 ){
            e1.printStackTrace();
        }

    }

}


    //gets the file name.

    private class getfile implements ActionListener{
    
        public void actionPerformed(ActionEvent e){

            try{
        JFileChooser choose = new JFileChooser();
        int res = choose.showOpenDialog(null);
        if(res == JFileChooser.APPROVE_OPTION){
        select = choose.getSelectedFile().getAbsolutePath();
        file = new File(select);
            }
        }   
        catch(Exception e1){
            e1.printStackTrace();
        }
    
    }
}






    public static void main(String[] args)throws Exception{
        
            new cryptor();

    }

}
