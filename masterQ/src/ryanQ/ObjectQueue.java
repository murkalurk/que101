package ryanQ;
/**
 * Template for the ObjectQueue used in the simulation.
 * @author Ryan Wagner
 *
 */
public class ObjectQueue implements ObjectQueueInterface{
private Object[] item;
private int front;
private int rear;
private int size;
/**
 * Empty constructor for ObjectQueue.
 */
public ObjectQueue() {
size = 100;
item = new Object[size];
front = size-1;
rear = size-1;
}
/**
 * Allows the user to designate the max Queue size.
 * @param max Determines the max Queue size.
 */
public ObjectQueue(int max) {
size = max;
item = new Object[size];
front = size-1;
rear = size-1;
}
/**
 * Checks to see if the Queue is empty.
 * @return true or false
 */
public boolean isEmpty() {
return front == rear;
}
/**
 * Checks to see if the Queue is full.
 * @return true or false
 */
public boolean isFull() {
return rear == size-1 ? front == 0 : front == rear+1;
}
/**
 * Clears the Object Queue.
 */
public void clear() {
item = new Object[size];
front = size-1;
rear = size-1;
}
/**
 * Inserts an Object to the rear of the Queue.
 * @param x the item to be inserted.
 */
public void insert(Object x) {
if (isFull()) {
System.out.println("Insert Runtime Error: Queue Overflow");
System.exit(1);
}
if (rear == size-1) // or rear = (rear+1) % size;
rear = 0;
else
rear++;
item[rear] = x;
}
/**
 * Removes an Object from the front of the Queue.
 * @return returns the value of the object to be removed.
 */
public Object remove() {
if (isEmpty()) {
System.out.println("Remove Runtime Error: Queue Underflow");
System.exit(1);
}
if (front == size-1) // or front = (front+1) % size;
front = 0;
else
front++;
Object temp = item[front];
item[front] = null;
return temp;
}
/**
 * Checks the item at the front of the Queue without removing it.
 * @return returns the value of the object at the front of the Queue.
 */
public Object query() {
if (isEmpty()) {
System.out.println("Query Runtime Error: Queue Underflow");
System.exit(1);
}
if (front == size-1)
return item[0];
else
return item[front+1];
}
}