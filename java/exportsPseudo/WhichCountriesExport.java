/**
 * Reads a chosen CSV file of country exports and prints each country that exports coffee.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
    static String countryInfo(CSVParser parser,String country){
        for (CSVRecord line:parser){
            if (line.get("Country").equals(country)){
                country=line.get("Country");
                String exports=line.get("Exports");
                String value=line.get("Value (dollars)");
                return String.format("%s: %s: %s",country,exports,value);
            }
        }
        return "NOT FOUND";
    }
    static void listExportersTwoProducts(CSVParser parser,
                                           String exportItem1,
                                           String exportItem2){   
        for (CSVRecord line:parser){
            if (line.get("Exports").contains(exportItem1)
                &&line.get("Exports").contains(exportItem2)){
                System.out.println(line.get("Country"));
            }
        }
    }
    static void numberOfExporters(CSVParser parser,String amount){
        int count=0;
        for (CSVRecord line:parser){
            if (line.get("Value (dollars)").length()>amount.length()){
                count+=1;
                System.out.println(String.format("%s %s",
                         line.get("Country"),line.get("Value (dollars)")));
            }
        }
        System.out.println(count);
    }
    static void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(countryInfo(parser, "Nauru"));//2
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser,"cocoa","cocoa");//3
        parser = fr.getCSVParser();
        numberOfExporters(parser,"$999,999,999,999");//4
    }
    public static void main(String[] args){
        tester();
    }
}
