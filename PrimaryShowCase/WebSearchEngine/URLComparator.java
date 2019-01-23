/**
 * This class contains the code to compute the relevance score of a
 * web page, in response to a query.  It also contains a method to
 * compare the scores of two web pages.
 *
 * @author John Donaldson (Spring 2016)
 */

import java.util.*;

class URLComparator implements Comparator<WebPageIndex> {

   List<String> query;
   
   URLComparator(List<String> query){
      this.query = query;
   }
   
   public int score(WebPageIndex idx){
	   int scoreSum = 0;
	   Iterator it = idx.words();
	   while (it.hasNext()) {
		   	String temp = (String) it.next();
	    	if (query.contains(temp)) {
	    		scoreSum += idx.getCount(temp) + Math.round(100 * idx.getFrequency(temp));
	    	}
	      }
	   return scoreSum;
	   
   }
      
    public int compare(WebPageIndex idx1, WebPageIndex idx2){ 
       return score(idx2) - score(idx1);
    }

}
