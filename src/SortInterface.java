
/* Filename: SortInterface.java
 * Author: Samuel Hoffman
 * Date: 2/4/2018
 * Purpose: A simple framework for sort classes that track critical operations and runtime.
 */
public interface SortInterface {
    void recursiveSort(int[] list, int initial) throws UnsortedException;
    void iterativeSort(int[] list) throws UnsortedException;
    int getCount();
    long getTime();
}
