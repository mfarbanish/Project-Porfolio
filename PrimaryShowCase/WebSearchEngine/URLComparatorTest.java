import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class URLComparatorTest {

	@Test
	public void testScore() {
		List<String> test = new ArrayList<String>();
		test.add("handin");
		//test.add("w");
		
		
		URLComparator comp = new URLComparator(test);
		
		WebPageIndex web = new WebPageIndex("http://www.cs.oberlin.edu/~jdonalds/151/labs/lab8.html");
		
		assertEquals("test scoring", comp.score(web), 1);
		
		//assertEquals()
	}

	@Test
	public void testCompare() {
		List<String> test = new ArrayList<String>();
		test.add("handin");
		
		
		URLComparator comp = new URLComparator(test);
		
		WebPageIndex web = new WebPageIndex("http://www.cs.oberlin.edu/~jdonalds/151/labs/lab8.html");
		WebPageIndex web2 = new WebPageIndex("http://www.cs.oberlin.edu/~jdonalds/151/labSchedule.html");
		
		
		assertEquals("test compare",comp.compare(web, web2), -1); 
		
		test.add("october");
		URLComparator comp2 = new URLComparator(test);
		
		assertEquals("test compare",comp2.compare(web, web2), 29); 
		
		//assertEquals()
	}

}
