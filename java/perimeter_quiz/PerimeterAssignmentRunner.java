import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int count=0;
        for(Point side: s.getPoints()){
            count=count+1;       
        }
        return count;
    }

    public double getAverageLength(Shape s) {
        double average;
        average = getPerimeter(s)/getNumPoints(s);
        return average;
    }

    public double getLargestSide(Shape s) {
        double maxLen=0;
        Point prevPoint=s.getLastPoint();//it is the last point

        for(Point currPoint: s.getPoints()){
            double distance = currPoint.distance(prevPoint);
            if(distance>maxLen){
                maxLen=distance;
            }
            prevPoint=currPoint;        
        }
        return maxLen;
    }

    public double getLargestX(Shape s) {
        int maxX=0;
        for(Point p:s.getPoints()){
            int x = p.getX();
            if(x > maxX){
                maxX=x;
            }
        }
        return maxX;
    }

    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        double maxPerim=0.0;
        
        for(File f:dr.selectedFiles()){
            FileResource file = new FileResource(f);
            Shape shape = new Shape(file);
            double currPerim=getPerimeter(shape);
            if(currPerim>maxPerim){
                maxPerim=currPerim;
            }
        
        }
        return maxPerim;
    }

    public String getFileWithLargestPerimeter() {
        double maxPerim=0.0;
        File temp = null;
        DirectoryResource dr = new DirectoryResource();
        
        for(File f:dr.selectedFiles()){
            FileResource file = new FileResource(f);
            Shape shape = new Shape(file);
            double currPerim = getPerimeter(shape);
            if(currPerim>maxPerim){
                maxPerim=currPerim;
                temp=f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
    }
    
    public void testPerimeterMultipleFiles() {
        double maxPerim = getLargestPerimeterMultipleFiles();
        System.out.println("The largest perimeter is:"+maxPerim);
    }

    public void testFileWithLargestPerimeter() {
        String file = getFileWithLargestPerimeter();
        System.out.println("The file with the largest perimeter:"+file);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public void testAverage () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double average = getAverageLength(s);
        System.out.println("average = " + average);
    }
   
    public void testLargestSide () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double largest = getLargestSide(s);
        System.out.println("largest = " + largest);
    }
    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
