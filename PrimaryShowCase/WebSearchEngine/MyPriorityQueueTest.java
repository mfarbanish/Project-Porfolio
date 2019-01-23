import static org.junit.Assert.*;

import java.util.PriorityQueue;

import org.junit.Test;

public class MyPriorityQueueTest {

	@Test
	public void testAdd() {
		MyPriorityQueue<String> q = new MyPriorityQueue<String>(new StringComparator());
		PriorityQueue<String> q2 = new PriorityQueue<String>(new StringComparator());
		
		assertEquals("first heap test, isempty", q.isEmpty(), true);
		
		q.add("d");
		q2.add("d");
		
		
		q.add("f");
		q2.add("f");
		
		q.add("z");
		q2.add("z");
		
		q.add("q");
		q2.add("q");
		
		q.add("a");
		q2.add("a");
		
		q.add("b");
		q2.add("b");
		
		q.add("g");
		q2.add("g");
		
		assertEquals("second heap test, isempty", q.isEmpty(), false);
		
		q.add("c");
		q2.add("c");
		
		//System.out.print(q2.toString());
		
		assertEquals("test toString and add", q2.toString(), q.toString());
		
		assertEquals("third heap test, size", q.size(), 8);
		
		assertEquals("test, remove", q.remove(), "a");
		q.remove();
		q.remove();
		q.remove();
		q.remove();
		q.remove();
		q.remove();
		
		assertEquals("test, remove", q.remove(), "z");
		
		//System.out.println(q.toString());
		
		q.clear();
		
		assertEquals("heap test, clear", q.isEmpty(), true);
		
	}
}
