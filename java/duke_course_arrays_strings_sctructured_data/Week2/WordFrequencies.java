package Week2;

import edu.duke.*;
import java.util.ArrayList;

/**
 * Write a description of class WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies(){
        myWords = new ArrayList();
        myFreqs = new ArrayList();
    }
    
    
    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for (String word:fr.words()){
            word = word.toLowerCase();
            int index = myWords.indexOf(word);
            if (myWords.contains(word)) {
                myFreqs.set(index,myFreqs.get(index)+1);
            }
            else {
                myWords.add(word);
                myFreqs.add(1);//
            }
        }
    }
    public void tester1(){
        findUnique();
        System.out.println("Number of unique words: "+myWords.size());
        for (int i=0;i<myWords.size();i++){
            System.out.println(String.format("%s-%d",myWords.get(i),myFreqs.get(i)));
        }
    
    }
    
    public int findIndexOfMax(){
        int index = 0;
        for (int i=0;i<myFreqs.size();i++){
            if (myFreqs.get(i)>myFreqs.get(index)) index=i;
        }
        return index;
    }
    public void tester2(){
        findUnique();
        int indexOfMax = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are "+myWords.get(indexOfMax)+" "+indexOfMax);
        
    }
}   