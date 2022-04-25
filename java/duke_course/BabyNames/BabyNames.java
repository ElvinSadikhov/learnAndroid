package BabyNames;

import edu.duke.*;
import java.io.File;
import org.apache.commons.csv.*;

/**
 * Write a description of class BabyNames here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class BabyNames {
    static void totalBirths(CSVParser parser){
        int totalBirths=0,totalNames=0,totalGirlNames=0,totalBoyNames=0;
        for (CSVRecord line:parser){
            totalNames+=1;
            totalBirths+=Integer.parseInt(line.get(2));
            if (line.get(1).equals("M")) totalBoyNames+=1;
            else totalGirlNames+=1;        
        }
        System.out.println(String.format("There were born %d babies in total",totalBirths));
        System.out.println(String.format("There were %d names in total",totalNames));
        System.out.println(String.format("There were %d boy names",totalBoyNames));
        System.out.println(String.format("There were %d girl names",totalGirlNames));
    }
    static void testTotalBirths(){
        FileResource fr = new FileResource();
        totalBirths(fr.getCSVParser());
    }
    
    static int getRank(int year,String name,String gender){
        //we can add SHORT
        FileResource fr = new FileResource(String.format("BabyNames\\yob%s",year)+".csv"); 
        //false because there is no header line in CSV file
        CSVParser parser = fr.getCSVParser(false);
        int rank = 0;
        for (CSVRecord line:parser){
            if (!(line.get(1).equals(gender))) continue;
            rank+=1;
            if (line.get(0).equals(name)) return rank;      
        }
        return -1;   
    }
    
    static String getName(int year, int rank, String gender){
        //we can add SHORT
        FileResource fr = new FileResource(String.format("BabyNames\\yob%s",year)+".csv"); 
        //false because there is no header line in CSV file
        CSVParser parser = fr.getCSVParser(false);
        int lineNum=0;
        for (CSVRecord line:parser){
            if (!(line.get(1).equals(gender))) continue;
            lineNum+=1;
            if (lineNum==rank) return line.get(0);
        }
        return "NO NAME";
    }
    
    static void whatIsNameInYear(String name, int year, int newYear, String gender){
       //Isabella born in 2012 would be Sophia if she was born in 2014.
       FileResource fr = new FileResource(String.format("BabyNames\\yob%s",newYear)+".csv");
       CSVParser parser = fr.getCSVParser();
       int neededRank=getRank(year,name,gender);
       int lineNum=0;
       for (CSVRecord line:parser){
           if (!(line.get(1).equals(gender))) continue;
           lineNum+=1;
           if (lineNum==neededRank){
               String newName = line.get(0);
               System.out.println(String.format("%s born in %d would be %s if she was born in %s",name,year,newName,newYear));
           }           
       }
    }
    
    static int yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int highestRank = -1;
        int neededYear=-1;
        for (File f:dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            int year = Integer.parseInt(f.getName().substring(3,7));
            int curRank = getRank(year,name,gender);
            if (curRank==compareTwoRanks(curRank,highestRank)) neededYear=year;
            highestRank = compareTwoRanks(curRank,highestRank);
        }
        return neededYear;
    }
    static int compareTwoRanks(int rank1, int rank2){
        if (rank1<0||rank2<0) return rank1+rank2+1;
        if (rank1<rank2) return rank1;
        return rank2;
    }
    
    static double getAverageRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        double sumOfRanks = 0;
        double numOfRankings = 0;
        for (File f:dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            int year = Integer.parseInt(f.getName().substring(3,7));
            double curRank = getRank(year, name, gender);
            if (curRank<0) continue;
            sumOfRanks+=curRank;
            numOfRankings+=1;
        }
        if (numOfRankings>0) return sumOfRanks/numOfRankings;
        return -1;
    }
    
    static int getTotalBirthsRankedHigher(int year, String name, String gender){
        int maxRank = getRank(year,name,gender);
        int sumOfBirths = 0;
        for (int rank=maxRank-1;rank>0;rank-=1){
            String newName = getName(year,rank,gender);
            System.out.println(newName);
            sumOfBirths+=numOfBirthsByName(year,name,gender);
            System.out.println(numOfBirthsByName(year,name,gender));
        }
        System.out.println(sumOfBirths);
        return sumOfBirths;
    }
    static int numOfBirthsByName(int year, String name, String gender){
        FileResource fr = new FileResource(String.format("BabyNames\\yob%s",year)+".csv");
        CSVParser parser = fr.getCSVParser();
        int num=0;
        for (CSVRecord line:parser){
            if (line.get(0).equals(name) && line.get(1).equals(gender)) {
                return Integer.parseInt(line.get(2));
            }
        }
        return num;
    }
}
