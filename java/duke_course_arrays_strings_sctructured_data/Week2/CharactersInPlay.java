package Week2;

import edu.duke.*;
import java.util.*;

/**
 * Write a description of class CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class CharactersInPlay {
    private ArrayList<String> names;
    private ArrayList<Integer> counts;
    
    public CharactersInPlay(){
        names = new ArrayList();
        counts = new ArrayList();
    }
    
    
    public void update(String person){
        if (!(names.contains(person))){
            names.add(person);
            counts.add(1);
        }
        else{
            int index = names.indexOf(person);
            counts.set(index,counts.get(index)+1);
        }
    }
    public void findAllCharacters(){
        names.clear();
        counts.clear();
        FileResource fr = new FileResource();
        for (String line:fr.lines()){
            int index = line.indexOf(".");
            if (index>=0) update(line.substring(0,index));
        }
    }
    public void tester(){
        findAllCharacters();
        int indexOfMax = 0;
        for (int i=0;i<counts.size();i++){
            if (counts.get(i)>counts.get(indexOfMax)) indexOfMax=i;
        }
        System.out.println("The main character is "+names.get(indexOfMax)+" "+indexOfMax);
        
        for (int i=0;i<counts.size();i++){
            if (counts.get(i)>=10 && counts.get(i)<=15){
                System.out.println(names.get(i)+counts.get(i));
            }
        }
    }
    
}
