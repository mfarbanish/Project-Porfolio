import static org.junit.Assert.*;

import org.junit.Test;

public class MyQueueTest {

	@Test
	public void testMyQueue() {
		MyQueue<Square> q = new MyQueue<Square>();
		
		assertEquals("test if it creates an empty queue", q.isEmpty(), true);
	}

	@Test
	public void testEnqueue() {
		MyQueue<Integer> q = new MyQueue<Integer>();
		
		q.enqueue(1);
		
		assertEquals("enq test", q.peek(), (Integer) 1);
	}

	@Test
	public void testDequeue() {
		MyQueue<Integer> q = new MyQueue<Integer>();
		
		q.enqueue(1);
		
		assertEquals("check dq value", q.dequeue(), (Integer) 1);
		
		assertEquals("check if empty", q.isEmpty(), true);
	}

	@Test
	public void testPeek() {
		MyQueue<Integer> q = new MyQueue<Integer>();
		
		q.enqueue(1);
		assertEquals("check peek value", q.peek(), (Integer) 1);
		
	}

	@Test
	public void testIsEmpty() {
		MyQueue<Integer> q = new MyQueue<Integer>();
		
		assertEquals("check peek value", q.isEmpty(), true);
		
		q.enqueue(1);
		
		assertEquals("check peek value", q.isEmpty(), false);
	}

	@Test
	public void testClear() {
		MyQueue<Integer> q = new MyQueue<Integer>();
		
		q.enqueue(1);
		q.clear();
		
		assertEquals("check peek value", q.isEmpty(), true);
	}

}
