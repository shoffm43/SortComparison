

/* Filename: BenchmarkSorts.java
 * Author: Samuel Hoffman
 * Date: 2/4/2018
 * Purpose: Creates arrays to be sorted using input from SortMain and calls from SelectionSort handles data processing
 */
public class BenchmarkSorts {
    final int numRuns = 50 ;
    int[] dataSizes;
    int[][][] counts;
    long[][][] times;
    SelectionSort sorter ;

    BenchmarkSorts(int[] data){
        dataSizes = data;
        //creates count array
        counts = new int[dataSizes.length][2][numRuns];
        //creates time array
        times = new long[dataSizes.length][2][numRuns];
        sorter = new SelectionSort();
    }
    
    public void runSorts(){
        for(int i = 0; i < dataSizes.length; i++) {
            for(int j = 0; j < numRuns; j++) {
                int[] testArray = arrayBuilder(dataSizes[i]), recursive = new int[dataSizes[i] + 1], iterative = new int[dataSizes[i]];
                try {
                    //recursive array copy
                    System.arraycopy(testArray, 0, recursive, 1, testArray.length);
                    sorter.recursiveSort(recursive, 0);
                    counts[i][1][j] = sorter.getCount();
                    times[i][1][j] = sorter.getTime();

                    //iterative array copy
                    System.arraycopy(testArray, 0, iterative, 0, testArray.length);
                    sorter.iterativeSort(iterative);
                    counts[i][0][j] = sorter.getCount();
                    times[i][0][j] = sorter.getTime();

                } catch (UnsortedException e) {
                }
            }
        }
    }
    
    //Gives report output in a format table
    public void displayReport(){
        double[] data;

        System.out.printf("|%-6s|%22s%22s|%22s%22s|%n", "Size N", "Iterative", "", "Recursive", "");
        System.out.println("--------------------------------------------------------------------------------------------------");

        System.out.printf("|%6s|", "");
        for(int i = 0; i < 2; i++) {
            System.out.printf("%11s|%10s|%10s|%10s|", "Average", "Standard", "Average", "Standard");
        }
        System.out.printf("%n|%6s|", "");
        for(int i = 0; i < 2; i++) {
            System.out.printf("%11s|%10s|%10s|%10s|", "Critical", "Deviation", "Execution", "Deviation");
        }
        System.out.printf("%n|%6s|", "");
        for(int i = 0; i < 2; i++) {
            System.out.printf("%11s|%10s|%10s|%10s|", "Operation", "of Count", "Time", "of Time");
        }
        System.out.printf("%n|%6s|", "");
        for(int i = 0; i < 2; i++) {
            System.out.printf("%11s|%10s|%10s|%10s|", "Count", "", "(Ns)", "(Ns)");
        }

        for(int i = 0; i < dataSizes.length; i++) {
            System.out.printf("%n|%6d|", dataSizes[i]);
            for(int j = 0; j < 2; j++) {
                data = getStats(counts[i][j], times[i][j]);
                System.out.printf("%,11.2f|%10.4f|%10.6f|%10.6f|", data[0], data[1], data[2], data[3]);
            }
            System.out.println("");
        }
    }

   private static int[] arrayBuilder(int size) {
        //creates arrays to be sorted with int values 0-100
        int[] data = new int[size];
        for(int i = 0; i < size; i++)
            data[i] = (int) (Math.random() * 100);
        return data;
    } 
   
   private double[] getStats(int[] counts, long[] times) {
        double avgCount = 0, avgTime = 0, countVar = 0, devCount = 0, timeVar = 0, devTime = 0;
        //sums count and time totals
        for(int i = 0; i < counts.length; i++) {
            avgCount += counts[i];
            avgTime += times[i] ;
        }
        //averages counts and times
        avgCount = avgCount / counts.length;
        avgTime = avgTime / times.length;
        for(int i = 0; i < counts.length; i++) {
            countVar += (avgCount - counts[i]) * (avgCount - counts[i]);
            timeVar += (avgTime - (times[i])) * (avgTime - (times[i]));
        }
        //caculates count and time variences
        countVar = countVar / counts.length;
        timeVar = timeVar / times.length;
        devCount = Math.sqrt(countVar);
        devTime = Math.sqrt(timeVar);
        
        double[] data = {avgCount, devCount, avgTime, devTime};
        return data;
    }
}
