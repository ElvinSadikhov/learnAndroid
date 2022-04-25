package StringsThirdAssignments;
import edu.duke.*;

/**
 * Write a description of class Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */



public class Part1 {
    public String findSimpleGene(String dna,String startCodon, String stopCodon){
        //checking for the case(upper or lower)
        if(dna.equals(dna.toUpperCase())){
            startCodon=startCodon.toUpperCase();
            stopCodon=stopCodon.toUpperCase();
        }
        else{
            startCodon=startCodon.toLowerCase();
            stopCodon=stopCodon.toLowerCase();
        }
        int nStart=-1,nEnd=-1;
        for(int i=0;i<dna.length()-3;i++){
            if(dna.substring(i,i+3).equals(startCodon)){
                nStart=i;
                break;
        }
    }
        if(nStart==-1|nStart>=dna.length()-3){
        return "";
    }
        for(int i = nStart; i<dna.length();i++){
            if(dna.substring(i,i+3).equals(stopCodon)){
                nEnd=i;
                break;
            }
        }
        if(nEnd==-1){
            return "";
        }
        if((nEnd-nStart)%3==0){
            return dna.substring(nStart,nEnd+3);
        }
        return "";
}   
    public StorageResource getAllGenes(){
        Part1 test=new Part1();
        String res1=test.findSimpleGene("ACGTATGCAGAGATGCTAAG","AtG","tAa");
        String res2=test.findSimpleGene("acgtatgcagagatcgtaag","AtG","TAA");
        StorageResource lst=new StorageResource();
        lst.add(res1);
        lst.add(res2);
        return lst;
    }
    public static void main(String[] args){
        Part1 test=new Part1();
        for (String res:test.getAllGenes().data()){
            System.out.println(res);
        }
        
    }
}
