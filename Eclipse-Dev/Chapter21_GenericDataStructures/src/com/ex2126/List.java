package com.ex2126;

public class List<T extends Comparable<T>> {

	private ListNode<T> firstNode;
	private ListNode<T> lastNode;
	
	public List() {
		super();
		firstNode = lastNode = null;
	}
	
	public void insertAtFront(T insertNode) {
		if (isEmpty())
			firstNode = lastNode = new ListNode<T>(insertNode);
		else
			firstNode = new ListNode<T>(insertNode, firstNode);
	}
	
	public void insertAtBack(T insertItem) {
		if (isEmpty())
			firstNode = lastNode = new ListNode<T>(insertItem);
		else
			lastNode = lastNode.nextNode = new ListNode<T>(insertItem);
	}
	
	public void insertNodeAfter(T insert, T after) {
		ListNode<T> current = firstNode;
		
		while (current != null) {
			if (current.data.compareTo(after) == 0){
				ListNode<T> newNode = new ListNode<T>(insert);
				ListNode<T> temp = current.nextNode;
				current.nextNode = newNode;
				newNode.nextNode = temp;
			}

			current = current.nextNode;
		}
	}
	
	public void deleteNode(T value) {
		ListNode<T> currentNode = firstNode;
		ListNode<T> previousNode = currentNode;
		
		while (currentNode != null) {		

			if (currentNode.data.compareTo(value) == 0) {
				previousNode.nextNode = currentNode.nextNode;
			}
			
			previousNode = currentNode;
			currentNode = currentNode.nextNode;
		}
		
		
	}
	
	public T search(ListNode<T> node, T value) {
		if (firstNode == null)
			return null;
		
		if (firstNode.data.compareTo(value) == 0)
			return firstNode.data;
		else
			return search(node.nextNode, value);
		
	}
	
	public boolean isEmpty() {
		return firstNode == null;
	}
	
	public void print()	{
		ListNode<T> currentNode = firstNode;
		
		while(currentNode != null) {
			System.out.printf("%s ", currentNode.data);
			currentNode = currentNode.nextNode;
		}
	}
}
