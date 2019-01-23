import java.util.*;
import java.io.*;

public class WebPageIndex {
   
   //TODO: Insert the instance variables here
   private RBTreeMap<String, LinkedList> map = new RBTreeMap<String, LinkedList>();
   private String url = "";
   private int count = 0;
   private ArrayList<String> contains = new ArrayList<String>();

   // WebPageIndex constructor
   WebPageIndex(String url) {
	   		this.url = url;
	        try {
	            HTMLScanner scanner = new HTMLScanner(url);
	            
	            while(scanner.hasNext() == true) {
	            	String token = (String) scanner.next().toLowerCase();
	                //System.out.println(token);
	                if (contains.contains(token) == false) {
	                	LinkedList<Integer> list = new LinkedList<Integer>();
	                	list.add(count);
	                	map.put(token, list);
	                	contains.add(token);
	                }
	                else {
	                	map.get(token).add(count);
	                }
	                count++;
	            }
	            

	        } catch (FileNotFoundException e) {
	            System.out.println(e);
	        } catch (IOException e) {
	            System.out.println(e);
	        }
	        
   }     
   
   // Returns a count of the words in this web page
   public int getWordCount() {
	   return count;
   }
   
   public String getUrl() {
      return this.url;
   }
   
   public boolean contains(String s) {
	   return contains.contains(s);
   }
   
   public int getCount(String s) {
	   if (this.contains(s)) {
		   return map.get(s).size();
	   }
	   
	   else {
		   return 0;
	   }
   }
   
   public double getFrequency(String s) {
	   //return (double) (this.getCount(s)/count);
	   return this.getCount(s)/ (double) count;
   }
   
   public List<Integer> getLocations(String s) {
	   List<Integer> l = new LinkedList<Integer>();
	   if (this.contains(s)) {
		   l = map.get(s);
	   }
	   return l;
   }

   // return an Iterator over all the words in the index
   public Iterator<String> words() {
      return map.keys();
   }
   
   public String toString() {
      return map.toString();
   }

   // The main method is an application using a WebPageIndex
   /*
   public static void main(String[] args) throws IOException {
	  String arg = args[0];
      WebPageIndex test = new WebPageIndex(arg);
      Iterator it = test.words();

	  System.out.println("Frequency and index of words in " + test.getUrl());
      while (it.hasNext()) {
    	  String temp = (String) it.next();
    	  System.out.printf("%-20s %.5f %s \n", temp, test.getFrequency(temp), test.getLocations(temp).toString());
      }
      System.out.println("height: " + test.map.getHeight());
      //System.out.println(test.getPhraseLocations("data structures"));
      
   }
*/
   
   /* 
    * additional features to support multi-word phrases 
    * work on these after you have the previous methods working correctly
    */
   
   public boolean containsPhrase(String s) {
	   String[] sArray = s.split("\\s+");
	   String first = sArray[0].toLowerCase();
	   
	   if (contains.contains(first)) {
		   List<Integer> indexOne = map.get(first);
		   //loops through indices of the list of indices of the first index of s
		   for (Integer index : indexOne) {
			   if (sArray.length == 1) {
				   return true;
			   }
			   
			   int counter = 1;
			   for (String word :sArray) {
				   //checks the end of s has been reached
				   if (word.equals(first) && counter == 1) {
					   continue;
				   }
				   //checks if word is even in the map
				   if (contains.contains(word)){
					   //System.out.println(word);
					   //if word has an index at the position after the previous word add to the count
					   if (map.get(word).contains(index + counter)) {
						   //System.out.println(map.get(word).contains(index + counter));
						   counter += 1;
						   //System.out.println(counter);
						   
					   }
					   else {
						   break;
					   }
				   }
				   
				   if (counter == sArray.length) {
					   
					   return true;
				   }
				   
				   //phrase not found at this index
				   //else {
					   //break;
				   //}
		   }
		   }
		   
		   
	   }
	   return false;
   }
   
   public int getPhraseCount(String s) {
	   String[] sArray = s.split("\\s+");
	   String first = sArray[0].toLowerCase();
	   
	   int phrasecount = 0;
	   
	   if (contains.contains(first)) {
		   List<Integer> indexOne = map.get(first);
		   //loops through indices of the list of indices of the first index of s
		   for (Integer index : indexOne) {
			   if (sArray.length == 1) {
				   phrasecount += 1;
			   }
			   
			   int counter = 1;
			   for (String word :sArray) {
				   //checks the end of s has been reached
				   if (word.equals(first) && counter == 1) {
					   continue;
				   }
				   //checks if word is even in the map
				   if (contains.contains(word)){
					   //System.out.println(word);
					   //if word has an index at the position after the previous word add to the count
					   if (map.get(word).contains(index + counter)) {
						   //System.out.println(map.get(word).contains(index + counter));
						   counter += 1;
						   //System.out.println(counter);
						   
					   }
					   else {
						   break;
					   }
				   }
				   
				   if (counter == sArray.length) {
					   
					   phrasecount += 1;
				   }
				   
				   //phrase not found at this index
				   //else {
					   //break;
				   //}
		   }
		   }
		   
		   
	   }
	   return phrasecount;
   }
   
   public double getPhraseFrequency(String s) {
	   return getPhraseCount(s)/getWordCount();
   }

   public List<Integer> getPhraseLocations(String s) {
	   String[] sArray = s.split("\\s+");
	   String first = sArray[0].toLowerCase();
	   
	   List<Integer> phraseloco = new ArrayList<Integer>();
	   
	   if (contains.contains(first)) {
		   List<Integer> indexOne = map.get(first);
		   //loops through indices of the list of indices of the first index of s
		   for (Integer index : indexOne) {
			   if (sArray.length == 1) {
				   phraseloco.add(index);
			   }
			   
			   int counter = 1;
			   for (String word :sArray) {
				   //checks the end of s has been reached
				   if (word.equals(first) && counter == 1) {
					   continue;
				   }
				   //checks if word is even in the map
				   if (contains.contains(word)){
					   //System.out.println(word);
					   //if word has an index at the position after the previous word add to the count
					   if (map.get(word).contains(index + counter)) {
						   //System.out.println(map.get(word).contains(index + counter));
						   counter += 1;
						   //System.out.println(counter);
						   
					   }
					   else {
						   break;
					   }
				   }
				   
				   if (counter == sArray.length) {
					   
					   phraseloco.add(index);
				   }
				   
				   //phrase not found at this index
				   //else {
					   //break;
				   //}
		   }
		   }
		   
		   
	   }
	   return phraseloco;
   }
   
   
}
