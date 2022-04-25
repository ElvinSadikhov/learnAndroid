package Week1;

import edu.duke.*;


/**
 * Write a description of class Implementing_the_Caesar_Cipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Implementing_the_Caesar_Cipher {
    //Assignment 1
    static boolean isVowel(char ch){
        char[] lst = {'A','E','U','I','O'};
        for (char vow:lst) if (Character.compare(Character.toUpperCase(ch),vow)==0) return true;
        return false;
    }
    static String replaceVowels(String phrase, char ch){
        StringBuilder newVersion = new StringBuilder(phrase);
        for (int i=0;i<newVersion.length();i++){
            if (isVowel(phrase.charAt(i))) newVersion.setCharAt(i,ch);
        }
        return newVersion.toString();
    }
    static String emphasize(String phrase, char ch){
        StringBuilder newVersion = new StringBuilder(phrase);
        for (int i=0;i<newVersion.length();i++){
            //even position BUT ODD INDEX
            if (i%2==1){
                if (Character.compare(Character.toUpperCase(newVersion.charAt(i)),Character.toUpperCase(ch))==0){
                    newVersion.setCharAt(i,'+');
                }
            }
            else{
                if (Character.compare(Character.toUpperCase(newVersion.charAt(i)),Character.toUpperCase(ch))==0){
                    newVersion.setCharAt(i,'*');
                }
            }
        }
        return newVersion.toString();
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
    static void testCaesar(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key = 23;
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
    static String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder result = new StringBuilder();
        for (int i=0;i<input.length();i++){
            if (i%2==0) result.append(encrypt(input.substring(i,i+1),key1));
            else result.append(encrypt(input.substring(i,i+1),key2));
        }
        System.out.println(result.toString());
        return result.toString();
    }
}

