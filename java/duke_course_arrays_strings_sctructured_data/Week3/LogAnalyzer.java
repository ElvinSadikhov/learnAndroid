package Week3;


/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource("Week3\\"+filename+".txt");
         for (String line:fr.lines()){
             LogEntry le = WebLogParser.parseEntry(line);
             records.add(le);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs(){
         ArrayList<String> ips = new ArrayList<String>();
         for (LogEntry le:records){
             String ipAddr = le.getIpAddress();
             if (!ips.contains(ipAddr)){
                 ips.add(ipAddr);
             }
         }
         return ips.size();
     }
     
     public void printAllHigherThanNum(int num){
         for (LogEntry le:records){
             if (le.getStatusCode()>=num){
                 System.out.println(le.toString());
             }
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
         ArrayList<String> list = new ArrayList();
         for (LogEntry le:records){
             if(le.getAccessTime().toString().substring(4,10).equals(someday)){
                 list.add(le.toString());
             }
         }
         return list;
     }
     
     public int countUniqueIPsInRange(int low, int high){
         ArrayList<String> list = new ArrayList();
         for (LogEntry le:records){
             if (le.getStatusCode()>=low && le.getStatusCode()<=high){
                if (!list.contains(le.getIpAddress())) list.add(le.getIpAddress());
             }
         }
         return list.size();
     }
     
     public HashMap<String,Integer> countVisitsPerIP(){
         HashMap<String,Integer> counts = new HashMap<String,Integer>();
         for (LogEntry le:records){
             String ip = le.getIpAddress();
             if (!counts.keySet().contains(ip)) counts.put(ip,1);
             else counts.put(ip,counts.get(ip)+1);
         }
         return counts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String,Integer> counts){
         int max = 0;
         for (String ip:counts.keySet()){
             if (counts.get(ip)>max) max=counts.get(ip);
         }
         return max;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String,Integer> counts){
         ArrayList<String> list = new ArrayList<String>();
         int max = mostNumberVisitsByIP(counts);
         for (String ip:counts.keySet()){
             if (counts.get(ip)==max && ! list.contains(ip)){
                 list.add(ip);
             }
         }
         return list;
     }
     
     public HashMap<String,ArrayList<String>> iPsForDays(){
         HashMap<String,ArrayList<String>> map = new HashMap<String,ArrayList<String>>();
         for (LogEntry le:records){
             String day = le.getAccessTime().toString().substring(4,10);
             if (!map.keySet().contains(day)){
                 map.put(day,uniqueIPVisitsOnDay(day));
             }
         }
         return map;
     }
     
     public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> map){
         int max = 0;
         String maxDate = "";
         for (String date:map.keySet()){
             if (map.get(date).size()>max){
                 max = map.get(date).size();
                 maxDate = date;
             }
         }
         return maxDate;  
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> map, String date){
         HashMap<String,Integer> map2 = new HashMap<String,Integer>();
         for (String ip:map.get(date)){
             ip = ip.substring(0,ip.indexOf(" ")+1);
             if (!map2.keySet().contains(ip)) map2.put(ip,1);
             else map2.put(ip,map2.get(ip)+1);
         }
         
         int max = 0;
         for (int count:map2.values()){
             if (count>max) max=count;
         }
         
         ArrayList<String> list = new ArrayList<String>();
         for (String ip:map2.keySet()){
             if (map2.get(ip)==max) list.add(ip);
         }
         System.out.println(max);
         return list;
     }
}
