import java.util.NoSuchElementException;

/**
 * An interface that describes a queue abstract data type
 *
 * @author John Donaldson (Fall 2018)
 */


public interface QueueADT<T> {
    /**
     * Enqueue an item onto the queue
     * @param item the data item to add (of type T)
     */
    void enqueue(T item);

    /**
     * Remove the front item from the queue
     * @return the front item in the queue
     * @throws NoSuchElementException if the queue is empty
     */
    T dequeue() throws NoSuchElementException;

    /**
     * Return the front item from the queue without removing it
     * @return the front item in the queue
     * @throws NoSuchElementException if the queue is empty
     */
    T peek() throws NoSuchElementException;

    /**
     * Determine if the queue is empty
     * @return true if the size is 0, false otherwise
     */
    boolean isEmpty();

    /**
     * Clear out the data structure
     */
    void clear();

}
