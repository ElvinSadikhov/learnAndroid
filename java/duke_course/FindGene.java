
/**
 * Write a description of class FindGene here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FindGene { 
    static String myFunc(String gene){
    int nStart=-1,nEnd=-1,flag=0;
    String res="";
    for(int i=0;i<gene.length()-3;i++){
        if(gene.substring(i,i+3).equals("ATG")){
            flag=1;
        }
        if(gene.substring(i,i+3).equals("TAA")&&flag==1){
            flag=0;    
            res+="TAA";
        }
        if(flag>0){
        res+=gene.charAt(i);
        }    
    }
    return res;
}
    public static void main(String[] args){
        String res=myFunc("ATCTTCGATCATGATGGCTATGCTAGCTAAGCTAGCTAA");
        System.out.println(res);
    }
}
