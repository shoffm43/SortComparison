
/* Filename: UnsortedException.java
 * Author: Samuel Hoffman
 * Date: 2/4/2018
 * Purpose: Extends exception and prints out a warning if arrays are inproperly sorted.
 */
public class UnsortedException extends Exception {

    UnsortedException(String string) {
        System.out.println(string); 
    }
    
}
