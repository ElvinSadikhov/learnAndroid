package StringsFirstAssignments;


/**
 * Write a description of class Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Part1 {
    public String findSimpleGene(String dna){
        int nStart=-1,nEnd=-1;
        for(int i=0;i<dna.length()-3;i++){
            if(dna.substring(i,i+3).equals("ATG")){
                nStart=i;
                break;
        }
    }
        if(nStart==-1|nStart>=dna.length()-3){
        return "";
    }
        for(int i = nStart; i<dna.length();i++){
            if(dna.substring(i,i+3).equals("TAA")){
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
        Part1 dna=new Part1() ;
        String res=dna.findSimpleGene("AGCATGCCTATCTACTTCTTATAATCG");
        System.out.println(res);
    }
}