import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

public class MyStackTest {



	@Test
	public void testPush() {
		MyStack<Integer> stack = new MyStack<Integer>();
		Stack<Integer> stack2 = new Stack<Integer>();
		
		stack.push(1);
		stack2.push(1);
		
		assertEquals("test empty", stack.peek(), stack2.peek());
	}

	@Test
	public void testPop() {
		MyStack<Integer> stack = new MyStack<Integer>();
		
		stack.add(1);
		
		stack.pop();

		
		assertEquals("test empty", stack.isEmpty(), true);
	}

	@Test
	public void testRemove() {
		MyStack<Integer> stack = new MyStack<Integer>();
		
		stack.add(1);
		

		assertEquals("test empty", stack.peek(), (Integer) 1);
	}

	@Test
	public void testPeek() {
		MyStack<Integer> stack = new MyStack<Integer>();
		Stack<Integer> stack2 = new Stack<Integer>();
		
		stack.push(1);
		stack2.push(1);
		
		assertEquals("test empty", stack.peek(), stack2.peek());
	}

	@Test
	public void testIsEmpty() {
		MyStack<Integer> stack = new MyStack<Integer>();
		Stack<Integer> stack2 = new Stack<Integer>();
		
		assertEquals("test empty", stack.isEmpty(), stack2.isEmpty());
		
		stack.push(1);
		stack2.push(1);
		
		assertEquals("test empty", stack.isEmpty(), stack2.isEmpty());
	}

	@Test
	public void testClear() {
		MyStack<Integer> stack = new MyStack<Integer>();
		Stack<Integer> stack2 = new Stack<Integer>();
		
		stack.push(1);
		stack2.push(1);
		
		stack.clear();
		stack2.clear();
		
		assertEquals("test empty", stack.isEmpty(), stack2.isEmpty());
	}

}
