package Week1;

import edu.duke.*;
/**
 * Write a description of class TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCaesarCipher {
    public int[] countLetters(String s){
        int[] counting = new int[26];
        String alphabetUp = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLow = "abcdefghijklmnopqrstuvwxyz";
        for (int i=0;i<s.length();i++){
            if (!(Character.isLetter(s.charAt(i)))) continue;
            if (alphabetUp.indexOf(s.charAt(i))==-1 && alphabetLow.indexOf(s.charAt(i))==-1) continue;
            if (Character.isUpperCase(s.charAt(i))) counting[alphabetUp.indexOf(s.charAt(i))]+=1;
            else counting[alphabetLow.indexOf(s.charAt(i))]+=1;
        }
        return counting;
    }
    public int indexOfMax(int[] values){
        int maxIndex=0;
        for (int i=0;i<values.length;i++){
            if (values[i]>values[maxIndex]) maxIndex=i;
        }
        return maxIndex;
    }
    public void simpleTests(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCipher cc = new CaesarCipher(18);
        String encryptedMessage = cc.encrypt(message);
        System.out.println("The encrypted version is: "+encryptedMessage);
        String decryptedMessage = cc.decrypt(encryptedMessage);
        System.out.println("The decrypted version is: "+decryptedMessage);
    }
    public void breakCaesarCipher(String input){
        int maxIndex = indexOfMax(countLetters(input));
        int key;
        if (maxIndex>=4) key = maxIndex-4;
        key = 26 -(4-maxIndex);
        
        CaesarCipher cc = new CaesarCipher(key);
        String decrypted = cc.decrypt(input);
        System.out.println("The decrypted version is: "+decrypted);
    }
}
