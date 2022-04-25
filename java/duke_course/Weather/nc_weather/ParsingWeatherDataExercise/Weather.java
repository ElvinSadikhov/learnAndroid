
/**
 * Write a description of class Weather here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.File;
import org.apache.commons.csv.*;

public class Weather {
    static CSVRecord coldestHourInFile (CSVParser parser){
        CSVRecord coldestSoFar=null;
        for(CSVRecord line:parser){
            if (coldestSoFar==null) coldestSoFar=line;
            else{
                coldestSoFar=compareTwoDays(line,coldestSoFar);
            }
        }
        return coldestSoFar;
    }
    static CSVRecord compareTwoDays(CSVRecord currentLine,
                                    CSVRecord coldestSoFar){
        if (currentLine.get("TemperatureF").equals("-9999")) return coldestSoFar;
        double curTemp=Double.parseDouble(currentLine.get("TemperatureF"));
        double coldestTemp=Double.parseDouble(coldestSoFar.get("TemperatureF"));
        if (curTemp<coldestTemp) return currentLine;
        return coldestSoFar;
    }   
    static void test(){
        FileResource fl=new FileResource();
        CSVParser parser=fl.getCSVParser();
        CSVRecord coldestH=coldestHourInFile(parser);
        System.out.println(String.format("The coldest temperature was %s at %s",coldestH.get("TemperatureF"),coldestH.get("TimeUTC  ")));
    }
    static File fileWithColdestTemperature(){
        DirectoryResource dr=new DirectoryResource();
        CSVRecord coldestDay=null;
        String coldestFileName="";
        for(File f :dr.selectedFiles()){
           FileResource fr = new FileResource(f);
           CSVParser parser = fr.getCSVParser();
           if (coldestDay==null) {
               coldestDay=coldestHourInFile(parser);
               coldestFileName=f.getName();
               continue;
           }
           else{
               double curTemp=Double.parseDouble(coldestHourInFile(parser).get("TemperatureF"));
               double coldestTemp=Double.parseDouble(coldestDay.get("TemperatureF"));
               if (curTemp<coldestTemp){
                   coldestDay=coldestHourInFile(parser);
                   coldestFileName=f.getName();
                }
            }
        }
        File coldestFile=new File(coldestFileName);
        return coldestFile;
    }
    static void testFileColdestTemperature(){
        File f=fileWithColdestTemperature();
        System.out.println("The coldest day was in the file"+f.getName());
        File f1= new File(String.format("C:\\Users\\Elvin\\Downloads\\%s",f));
        FileResource fl=new FileResource(f1);
        CSVParser parser=fl.getCSVParser();
        CSVRecord coldestHour=coldestHourInFile(parser);
        System.out.println(String.format("Coldest temperature on that day was %d",coldestHour.get("TemperatureF")));
        System.out.println("All the temperatures on the coldest day were:");
        for (CSVRecord line:parser){
            System.out.println(line.get("DateUTC")
                                +line.get("TemperatureF"));
        }
    }
}    
