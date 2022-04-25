package StringsThirdAssignments;
import edu.duke.*;

/**
 * Write a description of class Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


public class Part2 {
    static double cgRatio(String dna){
        double ratio;
        int count=0;
        for (int i=0;i<dna.length();i++){
            if (dna.substring(i,i+1).toUpperCase().equals("C")||dna.substring(i,i+1).toUpperCase().equals("G")){
                count+=1;
            }
            
        }
        ratio = (double)count/dna.length();        
        return ratio;
    }
    public static void main(String[] args){
        //Part2 test=new Part2();
        System.out.println(cgRatio("AccATACg"));
    }
}
