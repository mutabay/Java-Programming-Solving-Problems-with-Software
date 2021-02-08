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
        // Put code here
        
        int counter = 0;
        for(Point currPt : s.getPoints() )
        {
            counter++;
        }
        return counter;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double totalLength = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints())
        {
            double currDistance = prevPt.distance(currPt);
            totalLength += currDistance;
            
            prevPt = currPt;
        }
        return (totalLength / getNumPoints(s) );
    }

    public double getLargestSide(Shape s) {
        // Put code here
        
        double largestSide = 0.0;
        int flag = 0;
        double distance;
        Point prevPt = s.getLastPoint();
        for( Point currPt : s.getPoints() )
        {
            distance = prevPt.distance(currPt);
            //initializing value 
            if( flag == 0) 
                {   
                    largestSide = distance ;
                    flag = 1;
                }
                
            if( largestSide < distance ) 
            {
                largestSide  = distance ;
            }
            
            prevPt = currPt;
        
        }
        
        
        return largestSide;
    }

    public double getLargestX(Shape s) {
        // Put code here
        
        double largestX = s.getLastPoint().getX();
        for( Point currPt : s.getPoints() )
        {
            if(largestX < currPt.getX() )
                largestX = currPt.getX(); 
        }
        
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        
        double largestPerimeter = 0.0;
        int flag = 1;
        double newPerimeter;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            newPerimeter = getPerimeter(s);
            if(flag == 0)
            {   
                largestPerimeter = newPerimeter;        
                flag = 1;
            }
            
            if(largestPerimeter < newPerimeter )
            {
                largestPerimeter = newPerimeter;
            }
            
        }
        
        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        
        // Put code here
        
        File temp = null;
        double largestPerimeter = 0.0;
        int flag = 1;
        double newPerimeter;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            newPerimeter = getPerimeter(s);
            if(flag == 0)
            {   
                largestPerimeter = newPerimeter;
                temp = f;
                flag = 1;
            }
            
            if(largestPerimeter < newPerimeter )
            {
                largestPerimeter = newPerimeter;
                temp = f;
            }
            
        }
        
           // replace this code
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length 
        + "number of points are : " + getNumPoints(s) 
        + " average length of the shape sides : "+ getAverageLength(s) 
        + " Largest side is " +  getLargestSide(s)
        + " Largest points' x value of is "+ getLargestX(s));
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        System.out.println(" Largest perimeter in multiple files : " + getLargestPerimeterMultipleFiles());
        
       
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        System.out.println(" Largest file perimeter in multiple files : " + getFileWithLargestPerimeter());
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

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        
        pr.testPerimeter();



    }
}
