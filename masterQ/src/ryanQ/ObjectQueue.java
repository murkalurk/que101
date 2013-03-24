package ryanQ;
public class ObjectQueue implements ObjectQueueInterface{
private Object[] item;
private int front;
private int rear;
private int size;
public ObjectQueue() {
size = 100;
item = new Object[size];
front = size-1;
rear = size-1;
}
public ObjectQueue(int max) {
size = max;
item = new Object[size];
front = size-1;
rear = size-1;
}
public boolean isEmpty() {
return front == rear;
}
public boolean isFull() {
return rear == size-1 ? front == 0 : front == rear+1;
}
public void clear() {
item = new Object[size];
front = size-1;
rear = size-1;
}
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