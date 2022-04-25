package StringsFirstAssignments;


/**
 * Write a description of class Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


public class Part2 {
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
    public static void main(String[] args){
        Part2 test=new Part2();
        String res1=test.findSimpleGene("ACGTATGCAGAGATGCTAAG","AtG","tAa");
        String res2=test.findSimpleGene("acgtatgcagagatcgtaag","AtG","TAA");
        System.out.println("ACGTATGCAGAGATGCTAAG"+"\n"+res1);
        System.out.println("acgtatgcagagatcgtaag"+"\n"+res2);
        
    }
}
