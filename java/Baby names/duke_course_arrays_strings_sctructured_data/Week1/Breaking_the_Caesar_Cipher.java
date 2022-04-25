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
            if (!(Character.isLetter(word.charAt(0))||(Character.isLetter(word.charAt(-1))))) wordLen-=1;
            counts[wordLen]+=1;
        }
    }
    static void testCountWordLengths(){
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr,counts);
    }
}
