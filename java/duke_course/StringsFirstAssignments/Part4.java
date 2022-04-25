package StringsFirstAssignments;
import edu.duke.*;

/**
 * Write a description of class Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {
    static void findURL(String link){
        URLResource ur= new URLResource(link);
        for (String word:ur.words()){
            String temp=word.toLowerCase();
            if (temp.indexOf("youtube.com")>0){
                int nStart=word.indexOf("\"");
                int nEnd=word.indexOf("\"",nStart+1);//если писать nStart то выдаст егоже так как он ищет ВКЛЮЧИТЕЛЬНО нанчиная с nStart
                System.out.println(word.substring(nStart,nEnd));
            }
        }
    }   
    
    
    public static void main(String[] args){
        findURL("https://www.dukelearntoprogram.com/course2/data/manylinks.html");
    }
}
