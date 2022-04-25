package Weather;

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
            coldestSoFar=compareTwoDays(line,coldestSoFar);
        }
        return coldestSoFar;
    }
    static CSVRecord compareTwoDays(CSVRecord currentLine,
                                    CSVRecord coldestSoFar){
        if (coldestSoFar==null) return currentLine;
        if (currentLine.get("TemperatureF").equals("-9999")) return coldestSoFar;
        double curTemp=Double.parseDouble(currentLine.get("TemperatureF"));
        double coldestTemp=Double.parseDouble(coldestSoFar.get("TemperatureF"));
        if (curTemp<coldestTemp) return currentLine;
        return coldestSoFar;
    }   
    static void testColdestHourInFile(){
        FileResource fl=new FileResource();
        CSVParser parser=fl.getCSVParser();
        CSVRecord coldestH=coldestHourInFile(parser);
        System.out.println(String.format("The coldest temperature was %s at %s",coldestH.get("TemperatureF"),coldestH.get("DateUTC")));
    }
    static String fileWithColdestTemperature(){
        DirectoryResource dr=new DirectoryResource();
        CSVRecord coldestDay=null;
        String coldestFileName="";
        for(File f :dr.selectedFiles()){
           FileResource fr = new FileResource(f);
           CSVParser parser = fr.getCSVParser();
           CSVRecord curDay = coldestHourInFile(parser);
           if (curDay.equals(compareTwoDays(curDay,coldestDay))) coldestFileName=f.getPath();
           coldestDay=compareTwoDays(curDay,coldestDay);
        }
        return coldestFileName;
    }
    static void testFileWithColdestTemperature() {
        String filename = fileWithColdestTemperature();
        FileResource fr = new FileResource(filename);
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        
        System.out.println("Coldest day was in file "+ filename);
        System.out.println("Coldest temperature on that day was " + smallest.get("TemperatureF"));
        System.out.println("All the Temperature on the coldest day were:");
        
        for (CSVRecord currentRow : fr.getCSVParser()) {
                System.out.println(currentRow.get("DateUTC") + ": " + currentRow.get("TemperatureF") );;
        }
    }
    static CSVRecord compareTwoDaysHumidity(CSVRecord curDay,CSVRecord lowestHumidityDay){
        if (lowestHumidityDay==null) return curDay;
        if (curDay.get("Humidity").equals("N/A")) return lowestHumidityDay;
        double curHumidity = Double.parseDouble(curDay.get("Humidity"));
        double lowestHumidity = Double.parseDouble(lowestHumidityDay.get("Humidity"));
        if (curHumidity<lowestHumidity) return curDay;
        return lowestHumidityDay;
    }
    static CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestHumidityDaySoFar=null;
        for (CSVRecord line:parser){
            lowestHumidityDaySoFar = compareTwoDaysHumidity(line,lowestHumidityDaySoFar);
        }    
        return lowestHumidityDaySoFar;
    }
    static void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord check = lowestHumidityInFile(parser);
        System.out.println(String.format("The lowest humidity(%s) was at %s",check.get("Humidity"),check.get("DateUTC")));
    }
    static CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowest=null;
        for (File f:dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord curDay = lowestHumidityInFile(parser);
            lowest = compareTwoDaysHumidity(curDay,lowest);
        }
        return lowest;
    }
    static void testLowestHumidityInManyFiles(){
        CSVRecord lowestDay = lowestHumidityInManyFiles();
        System.out.println(String.format("The lowest humidity(%s) was at %s",lowestDay.get("Humidity"),lowestDay.get("DateUTC")));
    }
    static double averageTemperatureInFile(CSVParser parser){
        double sumOfTemps = 0;
        int numOfLines = 0;
        for (CSVRecord line:parser){
            sumOfTemps+=Double.parseDouble(line.get("TemperatureF"));
            numOfLines+=1;
        }
        return sumOfTemps/numOfLines;//average temp
    }
    static void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double averageTemp = averageTemperatureInFile(parser);
        System.out.println(String.format("Average temperature in file is %s",averageTemp));
    }
    static double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double sumOfTemps = 0;
        double num = 0;
        for (CSVRecord line:parser){
            int curHumidity = Integer.parseInt(line.get("Humidity"));
            if (curHumidity>=value){
                sumOfTemps+=Double.parseDouble(line.get("TemperatureF"));
                num+=1;
            }
        }
        return sumOfTemps/num;
    }
    static void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        int humidity = 80;
        double AverageTempWithHighHumidity = averageTemperatureWithHighHumidityInFile(parser,humidity);
        System.out.println(String.format("Average Temp when high Humidity is %f",AverageTempWithHighHumidity));
    }
}
