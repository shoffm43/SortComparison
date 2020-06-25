

/* Filename: SelectionSort.java
 * Author: Samuel Hoffman
 * Date: 2/4/2018
 * Purpose: Has iterative and recursive sort methods as well as data verification and count and time get methods.
 *          Impliments SortInterface structure and throws UnsortedException errors
 */
public class SelectionSort implements SortInterface{
    int count ;
    long runtime ;
    
    public SelectionSort(){
        count = 0 ;
        runtime = 0 ;
    }
    
//recursive selection sort
//Modified from http://www.cs.kzoo.edu/cs210/Labs/Recursion/recursiveSelSort.html
        @Override
    public void recursiveSort(int[] array, int startIndex) throws UnsortedException{
    count = 0 ;
    long start = System.nanoTime();
    
    array = subRecursion( array, startIndex) ;
    
    long end = System.nanoTime();
    runtime = end - start ;
    
    try {
            checkSort(array);
        } catch (UnsortedException e) {
            System.out.println("in recursive");
        }
    }
    
    int[] subRecursion(int[] array, int startIndex){
    int minIndex = startIndex;
    for ( int index = startIndex + 1; index < array.length; index++ )
    {
        if (array[index] < array[minIndex] ){
            minIndex = index;
            //Critical operation swap
            count++ ;   
        }
    }
    int temp = array[startIndex];
    array[startIndex] = array[minIndex];
    array[minIndex] = temp;
    if ( startIndex < array.length - 1 )
        return subRecursion(array, startIndex + 1);
    else
        return array ;
    }
    
    //Iterative selection sort
    //Modified from https://www.geeksforgeeks.org/selection-sort/ java subsection
        @Override
    public void iterativeSort(int[] array) throws UnsortedException{
        count = 0 ;
        long start = System.currentTimeMillis();
        for (int i = 0; i < array.length-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < array.length; j++)
                if (array[j] < array[min_idx]){
                    min_idx = j;
                    count++;
                }
 
            // Swap the found minimum element with the first
            // element
            int temp = array[min_idx];
            array[min_idx] = array[i];
            array[i] = temp;
        }
        long end = System.currentTimeMillis();
        runtime = end - start ;
        
        try {
            checkSort(array);
        } catch (UnsortedException e) {
            System.out.println("in iterative");
        }
    }
        
        //returns critical operations of sort
        @Override
        public int getCount(){
            return count ;
        }
        
        //returns runtime of sort
        @Override
        public long getTime(){
            return runtime ;
        }
        
        //verifies sort called after each array is sorted
        void checkSort(int[] array) throws UnsortedException {
        for (int i = 1; i < array.length - 1; i++)
            if(array[i] > array[i + 1]) {
                throw new UnsortedException("The array was not corectly sorted the arrays value at: " + i + " is higher than " + (i+1)+ " ," + array[i] + array[i+1]);
            }
        }
}
