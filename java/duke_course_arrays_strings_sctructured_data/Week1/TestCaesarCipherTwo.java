package Week1;

import edu.duke.*;

/**
 * Write a description of class TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCaesarCipherTwo {
    public int indexOfMax(int[] values){
        int maxIndex=0;
        for (int i=0;i<values.length;i++){
            if (values[i]>values[maxIndex]) maxIndex=i;
        }
        return maxIndex;
    }
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
    public String halfOfString(String message, int start){
        StringBuilder res = new StringBuilder();
        for (int i=start;i<message.length();i+=2){
            res.append(message.charAt(i));
        }
        return res.toString();
    }
    public void decryptTwoKeys(String input){
        int maxIndex = indexOfMax(countLetters(input));
        int key;
        if (maxIndex>=4) key = maxIndex-4;
        key = 26 -(4-maxIndex);
        
        CaesarCipher cc = new CaesarCipher(key);
        String decrypted = cc.decrypt(input);
    }
    public void simpleTests(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCipherTwo cc = new CaesarCipherTwo(17,3);
        String encryptedMessage = cc.encryptTwoKeys(message);
        System.out.println("The encrypted version is: "+encryptedMessage);
        String decryptedMessage = cc.decryptTwoKeys(encryptedMessage);
        System.out.println("The decrypted version is: "+decryptedMessage);
    }
    
    public int getKey(String s){
        int maxIndex = indexOfMax(countLetters(s));
        System.out.println(maxIndex);
        if (maxIndex>=4) return maxIndex-4;
        return 26 -(4-maxIndex);
    }
    public void breakCeasarCipher(String input){
        String firstHalf = halfOfString(input,0);
        String secondHalf = halfOfString(input,1);
        int firstKey = getKey(firstHalf);
        int secondKey = getKey(secondHalf);
        
        
        CaesarCipherTwo ccT = new CaesarCipherTwo(firstKey,secondKey);
        String res = ccT.decryptTwoKeys(input);
    }
}
