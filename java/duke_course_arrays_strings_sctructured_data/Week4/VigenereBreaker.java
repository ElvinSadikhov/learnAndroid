package Week4;

import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder res = new StringBuilder();
        for (int i=whichSlice;i<message.length();i+=totalSlices){
            res.append(message.charAt(i));
        }
        return res.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int i=0;i<klength;i++){
            String slice = sliceString(encrypted,i,klength);
            key[i] = cc.getKey(slice);
            System.out.println(key[i]);
        }
        return key;
    }

    public void breakVigenere () {
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        int[] keys = vb.tryKeyLength(encrypted,38,'e');
        VigenereCipher vc = new VigenereCipher(keys);
        String decrypted = vc.decrypt(encrypted);
        System.out.println(decrypted);
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> set = new HashSet<String>();
        for (String line:fr.lines()){
            line = line.toLowerCase();
            set.add(line);
        }
        return set;
    }
    
    public int countWords(String message, HashSet<String> dictionary){
        String[] words = message.split("\\W+");
        int count = 0;
        for (int i=0;i<words.length;i++){
            if (dictionary.contains(words[i].toLowerCase())) count+=1;
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        int max = 0;
        String res = "";
        StringBuilder validKeys = new StringBuilder();
        int lenOfKeys = 0;
        for (int i=1;i<=100;i++){
            int[] keys = tryKeyLength(encrypted,i,'e');
            VigenereCipher vc = new VigenereCipher(keys);
            String decrypted = vc.decrypt(encrypted);
            int count = countWords(decrypted, dictionary);
            //System.out.println(count);
            if (count>max){
                max=count;
                res = decrypted;
                validKeys=new StringBuilder();
                for (int key:keys){
                    validKeys.append(String.format("%s ",key));
                }
                lenOfKeys = keys.length;
            }
        }
        System.out.println(lenOfKeys);
        System.out.println(validKeys);
        System.out.println(max);
        return res;
    }
    
    public void breakVigenere2(){
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        FileResource dict = new FileResource();        
        HashSet<String> dictionary = vb.readDictionary(dict);
        String res = vb.breakForLanguage(encrypted,dictionary);
        System.out.println(res);
    }
    public static void main(String[] args) {
    	breakVigenere2();
    	
    }
}
