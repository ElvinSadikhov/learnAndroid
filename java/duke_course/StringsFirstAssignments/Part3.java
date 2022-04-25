package StringsFirstAssignments;


/**
 * Write a description of class Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurences(String stringa,String stringb){
        int count=0;
        for(int i =0;i<stringb.length()-stringa.length();i++){
            
            if(stringb.substring(i,i+stringa.length()).equals(stringa)){
                count+=1;
            }
        }
        if(count>=2){
            return true;
        }
        return false;
    }
    public String lastPart(String stringa, String stringb){
        for(int i=0;i<=stringb.length()-stringa.length();i++){
            if (stringb.substring(i,i+stringa.length()).equals(stringa)){
                if(i>=stringb.length()-stringa.length()){
                    return "";
                }
                return stringb.substring(i+stringa.length());
            }
        }
        return stringb;
    }
    public static void main(String[] args){
        //test1 for twoOccurences
        Part3 test1=new Part3();
        if(test1.twoOccurences("a","banana")){
            System.out.println("true");
        }
        else{
            System.out.println("false");
        }
        if(test1.twoOccurences("atg", "ctgtatgta")){
            System.out.println("true");
        }
        else{
            System.out.println("false");
        }
        //test2 for lastPart
        Part3 test2=new Part3();
        String a,b;
        a="an";b="banana";
        String res=test2.lastPart(a,b);
        System.out.println(String.format("The part of the string after %s in %s is %s",a,b,res));
        a="zoo";b="forest";
        res=test2.lastPart(a,b);
        System.out.println(String.format("The part of the string after %s in %s is %s",a,b,res));
        a="et";b="privet";
        res=test2.lastPart(a,b);
        System.out.println(String.format("The part of the string after %s in %s is %s",a,b,res));
        
    }
}
