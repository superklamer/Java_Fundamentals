package com.ex2115;
import com.figLinkedList.List;
import com.figLinkedList.EmptyListException;

public class Queue<T> {
	
	private List<T> queueList;
	private static int size;
	
	// constructor
	public Queue() {
		queueList = new List<T>("Queue");
	}
	
	// get queue size
	public int getSize() {
		return size;
	}

	// add object to queue
	public void enqueue(T object) {
		queueList.insertAtBack(object);
		size++;
	}
	
	// remove object from queue
	public T dequeue() throws EmptyListException {
		size--;
		return queueList.removeFromFront();	
	}
	
	// detemine if queue is empty
	public boolean isEmpty() {
		return queueList.isEmpty();
	}
	
	// output queue contents
	public void print() {
		queueList.print();
	}
	
	// view first element in queue
	public T peek() {
		return queueList.getFirstNode();
	}
}
