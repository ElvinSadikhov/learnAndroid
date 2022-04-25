package Week3;


/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }
    
    public void testUniqueIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println(la.countUniqueIPs());
        la.printAllHigherThanNum(400);   
        System.out.println(la.uniqueIPVisitsOnDay("Sep 24").size());
        System.out.println(la.countUniqueIPsInRange(400,499));
    }
    
    public void testVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int num = la.mostNumberVisitsByIP(la.countVisitsPerIP());
        System.out.println("Most number "+num);
        ArrayList<String> mostIp = la.iPsMostVisits(la.countVisitsPerIP());
        System.out.println("Most IP "+mostIp);
    }
    
    public void testDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        String day = la.dayWithMostIPVisits(la.iPsForDays());
        System.out.println("Day  "+day); 
        ArrayList<String> ip = new ArrayList<String>(la.iPsWithMostVisitsOnDay(la.iPsForDays(),"Sep 29"));
        System.out.println("IP  "+ip);
    }
}
