import java.util.NoSuchElementException;

public class MyQueue<T> implements QueueADT<T>, Worklist<T>
{
    private QNode<T> front;
    private QNode<T> rear;
    
    //nested class QNode:  nodes used in the queue
    class QNode<T>
    {
	//holds the data value and reference for a node
	private T item;
	private QNode<T> next;
	
	//constructors for node
	QNode(T item){
	    this(item,null);
	}
	
	QNode(T item, QNode<T> next){
	    this.item = item;
	    this.next = next;
	}
    }
    
    //initializes empty queue
    public MyQueue(){
    	front = null;
    	rear = null;
    }
    
    //adds element to the queue
    public void enqueue(T item){
    	QNode<T> prevrear = rear;
    	rear = new QNode<T>(item);
    	rear.next = null;
    	if (this.isEmpty() == true) {
    		front = rear;
    	}
    	else {
    		prevrear.next = rear;
    	}
    }
    
    public void add(T item){
	 this.enqueue(item);
    }
    
    // gets and removes the next thing in the queue that is supposed to be executed
    // throw a NoSuchElementException if the queue is empty
    public T dequeue() {
    	if (isEmpty() == true) {
    		throw new NoSuchElementException("no items in queue");
    	}
    	else {
    		T item = front.item;
    		front = front.next;
    	
    		return item;
    	}
    }
    
    public T remove(){
    	return this.dequeue();
    }
    
    // gets the first item in the queue without removing it
    // throw a NoSuchElementException if the queue is empty
    public T peek(){
    	if (this.isEmpty() == true) {
    		throw new NoSuchElementException("there are no items in queue");
    	}
    	else {
    		return front.item;
    	}
    }
    
    // tests to see if the queue is empty
    public boolean isEmpty(){
    	if (front == null) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    //clears the queue
    public void clear(){
    	this.front = null;
    	this.rear = null;
    }
}

