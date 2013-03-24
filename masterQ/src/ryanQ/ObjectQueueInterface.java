package ryanQ;

public interface ObjectQueueInterface {
	public boolean isEmpty();
	public boolean isFull();
	public void clear();
	public void insert(Object x);
	public Object remove();
	public Object query();
	}
