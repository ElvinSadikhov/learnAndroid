package Week2;

import edu.duke.*;
import java.util.*;

/**
 * Write a description of class CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class CodonCount {
    private HashMap<String,Integer> map;
    public CodonCount(){
        map = new HashMap<String,Integer>();
    }
    
    
    public void buildCodonMap(int start, String dna){
        map.clear();
        for (int i=start;i<dna.length()-2;i+=3){
            String codon = dna.substring(i,i+3);
            if (map.containsKey(codon)) map.put(codon,map.get(codon)+1);
            else map.put(codon,1);
        }
    }
    public String getCommonCodon(){
        String commonCodon=(String) map.keySet().toArray()[0];
        for (String codon:map.keySet()){
            if (map.get(codon)>map.get(commonCodon)) commonCodon=codon;
        }
        return commonCodon;
    }
    public void printCodonCounts(int start, int end){
        for (String codon:map.keySet()){
            if (map.get(codon)>=start && map.get(codon)<=end){
                System.out.println(codon+" "+map.get(codon).toString());
            }
        }
    }
    public void tester(){
        FileResource fr = new FileResource();
        String dna = fr.asString().trim();//use trim in order to get rid of \n character
        dna.toUpperCase();
        CodonCount cc = new CodonCount();
        
        for (int frame=0;frame<3;frame++){
            cc.buildCodonMap(frame,dna);
            System.out.println("The number of unique codons "+cc.map.size());
            System.out.println("The most common codon is "+cc.getCommonCodon()+" and its number is "+cc.map.get(cc.getCommonCodon()));
            cc.printCodonCounts(1,6);
            System.out.println();
        }     
    }
    
}
