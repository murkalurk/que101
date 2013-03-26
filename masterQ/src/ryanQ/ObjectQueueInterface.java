package ryanQ;
/**
 * Interface for ObjectQueue
 * @author Ryan Wagner
 *
 */
public interface ObjectQueueInterface {
	/**
	 * Checks to see if the Queue is empty.
	 * @return true or false
	 */
	public boolean isEmpty();
	/**
	 * Checks to see if the Queue is full.
	 * @return true or false
	 */
	public boolean isFull();
	/**
	 * Clears the Object Queue.
	 */
	public void clear();
	/**
	 * Inserts an Object to the rear of the Queue.
	 * @param x the item to be inserted.
	 */
	public void insert(Object x);
	/**
	 * Removes an Object from the front of the Queue.
	 * @return returns the value of the object to be removed.
	 */
	public Object remove();
	/**
	 * Checks the item at the front of the Queue without removing it.
	 * @return returns the value of the object at the front of the Queue.
	 */
	public Object query();
	}
