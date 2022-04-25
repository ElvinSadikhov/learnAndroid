package Week1;

import edu.duke.*;

/**
 * Write a description of class Breaking_the_Caesar_Cipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


public class Breaking_the_Caesar_Cipher {
    static void countWordLengths(FileResource resource, int[] counts){
        for (String word:resource.words()){
            int wordLen=word.length();
            if (!(Character.isLetter(word.charAt(0))&&(Character.isLetter(word.charAt(word.length()-1))))) wordLen-=1;
            counts[wordLen]+=1;
        }
    }
    static void testCountWordLengths(){
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr,counts);
        for (int i=0;i<counts.length;i++){
            if (counts[i]==0) continue;
            System.out.println(String.format("%d words of length %d",counts[i],i));
        }
        System.out.println(indexOfMax(counts));
    }
    static int indexOfMax(int[] values){
        int maxIndex=0;
        for (int i=0;i<values.length;i++){
            if (values[i]>values[maxIndex]) maxIndex=i;
        }
        return maxIndex;
    }
    
    static int[] countLetters(String s){
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
    static int getKey(String s){
        int maxIndex = indexOfMax(countLetters(s));
        System.out.println(maxIndex);
        if (maxIndex>=4) return maxIndex-4;
        return 26 -(4-maxIndex);
    }
    static String halfOfString(String message, int start){
        StringBuilder res = new StringBuilder();
        for (int i=start;i<message.length();i+=2){
            res.append(message.charAt(i));
        }
        return res.toString();
    }
    static void decryptTwoKeys(String encrypted){
        String firstHalf = halfOfString(encrypted,0);
        String secondHalf = halfOfString(encrypted,1);
        int firstKey = getKey(firstHalf);
        int secondKey = getKey(secondHalf);
        System.out.println("The two keys are "+firstKey+" and "+secondKey);
        String decrypted1 = encrypt(firstHalf,26-firstKey);
        String decrypted2 = encrypt(secondHalf,26-secondKey);
        StringBuilder res = new StringBuilder();
        for (int k=0;k<decrypted1.length()+decrypted2.length();k++) res.append(" ");
        int temp = 0;
        int index = 0;
        for (int i=0;i<res.length();i++){
            if (temp%2==0) {
                res.setCharAt(i,decrypted1.charAt(index));
                temp+=1;
            }
            else {
                res.setCharAt(i,decrypted2.charAt(index));
                index+=1;
                temp+=1;
            }
        }
        System.out.println("Derypted message\n"+res);
    }
    static String encrypt(String input, int key){
        StringBuilder encrypted = new StringBuilder(input);
        StringBuilder alphabetUp = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        StringBuilder alphabetLow = new StringBuilder("abcdefghijklmnopqrstuvwxyz");
        StringBuilder modifiedAlpUp = new StringBuilder(alphabetUp.substring(key)+alphabetUp.substring(0,key));
        StringBuilder modifiedAlpLow = new StringBuilder(alphabetLow.substring(key)+alphabetLow.substring(0,key));
        for (int i=0;i<encrypted.length();i++){
            if (!(Character.isAlphabetic(encrypted.charAt(i)))) continue;
            if (Character.isUpperCase(encrypted.charAt(i))){
                encrypted.setCharAt(i,modifiedAlpUp.charAt(alphabetUp.indexOf(encrypted.substring(i,i+1))));
            }
            else{
                encrypted.setCharAt(i,modifiedAlpLow.charAt(alphabetLow.indexOf(encrypted.substring(i,i+1))));
            }
        }
        System.out.println(encrypted.toString());
        return encrypted.toString();
    }
}
