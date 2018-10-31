import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MyStack<T> implements StackADT<T>, Worklist<T> {

    private ArrayList<T> data;
    
    //initializes the stack
    public MyStack(){
	data = new ArrayList<T>();
    }

    //adds an item to the stack
    public void push(T item){
    	data.add(0, item);
    }
    
    public void add(T item){
    	this.push(item);
    }
    
    //returns and removes the top element of the stack unless the stack is empty
    public T pop() {
    	if (data.isEmpty() == true) {
    		throw new NoSuchElementException("no items in stack");
    	}
    	else {
	    	T temp = data.get(0);
	    	data.remove(0);
	    	return temp; // replace this line
    	}
    }
    
    public T remove(){
    	return this.pop();
    }

    // returns the top of the stack without removing it    
    public T peek(){	
    	return data.get(0); // replace this line
    }
    
    //determines if the stack is empty or not
    public boolean isEmpty() {
    	if (data.size() == 0) {
    		return true;
    	}
    	else {
    		return false;
    	} 
    }
    
    //clears the stack
    public void clear(){
	 this.data = new ArrayList<T>();
    }
    
}