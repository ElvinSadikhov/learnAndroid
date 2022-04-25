package Week2;

import edu.duke.*;
import java.util.*;
import java.io.File;

/**
 * Write a description of class WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


public class WordsInFiles {
    private HashMap<String, ArrayList> map;
    
    public WordsInFiles(){
        map = new HashMap<String, ArrayList>();
    }
    
    
    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        for (String word:fr.words()){
            if (this.map.containsKey(word)){
                if (!(this.map.get(word)).contains(f.getName())) {
                    ArrayList<String> fileNames = new ArrayList<String>(this.map.get(word));
                    fileNames.add(f.getName());
                    this.map.put(word,fileNames);
                }
            }
            else{
                ArrayList<String> fileNames = new ArrayList<String>();
                fileNames.add(f.getName());
                this.map.put(word,fileNames);
            }
        }
    }
    public void buildWordFileMap(){
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f:dr.selectedFiles()){
            this.addWordsFromFile(f);
        }
    }
    public int maxNumber(){
        int max=0;
        for (String word:map.keySet()){
            if (map.get(word).size()>max) max = map.get(word).size();
        }
        return max;
    }  
    public ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> list = new ArrayList<String>();
        for (String word:map.keySet()){
            if (map.get(word).size()==number) list.add(word);
        }
        return list;
    }
    public void printFilesIn(String word){
        ArrayList<String> list = new ArrayList<String>(map.get(word));
        for (String fileName:list){
            System.out.println(fileName);
        }
    }
    public void tester(){
        this.buildWordFileMap();
        System.out.println(this.maxNumber());
        int count=0;
        for (String word:this.wordsInNumFiles(5)){//
            System.out.println(word);
            ArrayList<String> list = new ArrayList<String>(map.get(word));
            for (String fileName:list){
                System.out.println(String.format("\"%s\"",fileName));
            }
            count+=1;
        } 
        System.out.println(count);
        for (String word:this.map.keySet()){
            if (word.equals("red")){
                System.out.println(this.map.get(word));
            }
        }
    }
}
