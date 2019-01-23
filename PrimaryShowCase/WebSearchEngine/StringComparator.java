/**
 * Compare two string objects using lexicographic ordering.
 */

import java.util.Comparator;

class StringComparator implements Comparator<String> {
    // the compare method must return
    //   a negative integer if s1 < s2
    //   0 if s1.equals(s2)
    //   a positive integer if s1 > s2
    //
    // for Strings, we can just call their compareTo method
    public int compare(String s1, String s2){
        return s1.compareTo(s2);
    }

}
