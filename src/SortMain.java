
/* Filename: SortMain.java
 * Author: Samuel Hoffman
 * Date: 2/4/2018
 * Purpose: Sets parameters for Benchmark sorts/runs main method
 */
public class SortMain {
    public static void main(String[] args) {
        //Runs to warmup the sorting algorithms
        int[] warmData = {10} ;
        for (int i = 0; i < 100000; i++) {
            BenchmarkSorts warmRun = new BenchmarkSorts(warmData);
            //displayReports doesn't need to be warmed as it isn't time sensitive
            warmRun.runSorts();
        }
        
        //main program run
         int[] dataSize = {10, 20, 40, 80, 160, 320, 640, 1280, 2560, 5120};
        
        BenchmarkSorts mainRun = new BenchmarkSorts(dataSize);
        //sorts arrays
        mainRun.runSorts();
        //calculates and displays results
        mainRun.displayReport();
    }
    
}
