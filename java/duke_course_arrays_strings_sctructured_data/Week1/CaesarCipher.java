package Week1;

import edu.duke.*;
/**
 * Write a description of class CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    public CaesarCipher(int key){
        mainKey=key;
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet = alphabet.substring(key)+alphabet.substring(0,key);  
    }
    
    
    public String encrypt(String input){
        StringBuilder encrypted = new StringBuilder(input);
        for (int i=0;i<encrypted.length();i++){
            if (!(Character.isAlphabetic(encrypted.charAt(i)))) continue;
            if (Character.isUpperCase(encrypted.charAt(i))) encrypted.setCharAt(i,Character.toUpperCase(shiftedAlphabet.charAt(alphabet.indexOf(Character.toLowerCase(encrypted.charAt(i))))));
            else encrypted.setCharAt(i,shiftedAlphabet.charAt(alphabet.indexOf(encrypted.charAt(i))));
        }
        System.out.println(encrypted.toString());
        return encrypted.toString();
    }
    
    public String decrypt(String input){
        CaesarCipher cc = new CaesarCipher(26-mainKey);
        System.out.println(cc.encrypt(input));
        return cc.encrypt(input);
    }
}
