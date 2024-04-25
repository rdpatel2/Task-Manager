/**
 * 
 */
package edu.ncsu.csc216.wolf_tasks.model.util;

/**
 * Creates a sorted list of tasks
 * @author Rohan Patel
 * @author Daniel Li
 * @param <E> generic element of list
 */
public class SortedList<E extends Comparable<E>> implements ISortedList<E>  {
	
	/**
	 * Keeps track of how many elements are inside of the SortedList
	 */
	private int size;
	
	/**
	 * Keeps track of the front node in the SortedList
	 */
	private ListNode front;

	@Override
	public E remove(int idx) {
		checkIndex(idx);
		if (idx == 0) {
			E value = front.data;
			front = front.next;
			size--;
			return value;
		} else {
			ListNode current = front;
			for (int i = 0; i < idx - 1; i++) {
				current = current.next;
			}
			E value = current.next.data;
			current.next = current.next.next;
			size--;
			return value;
		}
	}
	
	/**
	 * Checks if a given element can be found within the SortedList
	 * @param element element to check if it contains
	 * @return true if element is in list and false if not
	 */
	public boolean contains(E element) {
		if (size() > 0) {
			ListNode current = front;
			if (current.data == element) {
				return true;
			}
			for (int i = 0; i < size - 1; i++) {
				current = current.next;
				if (element == current.data) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public E get(int idx) {
		checkIndex(idx);
		ListNode current = front;
		for (int i = 0; i < idx; i++) {
			current = current.next;
		}
		return current.data;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void add(E element) {
		if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		ListNode current = front;
		while (current != null) {
			if (element.equals(current.data))
				throw new IllegalArgumentException("Cannot add duplicate element.");
			else
				current = current.next;
		}
		
		current = front;
		if (current != null && current.next != null) {
			
			if (element.compareTo(front.data) < 0) {
				front = new ListNode(element, front);
			}
			else {
				boolean added = false;
				for (int i = 0; i < size - 1; i++) {
					if (!(element.compareTo(current.data) > 0 && element.compareTo(current.next.data) < 0)) {
						current = current.next;
					} else {
						current.next = new ListNode(element, current.next);
						added = true;
						break;
					}
				} 
			
				if (!added) {
					current = front;
					for(int i = 0; i < size - 1; i++) {
						current = current.next;
					}
					current.next = new ListNode(element, null);
				}
			}
			
		}
		else if (current != null && current.next == null) {
			if (element.compareTo(current.data) < 0)
				front = new ListNode(element, front);
			else 
				current.next = new ListNode(element, null);
		}
		else {
			front = new ListNode(element, null);
		}
		size++;
	}
	
	/**
	 * Creates a new Sorted List object
	 */
	public SortedList() {
		size = 0;
		front = null;
	}
	
	/**
	 * Checks the index of the sorted list
	 * @param idx index being checked
	 */
	private void checkIndex(int idx) {
		if (idx < 0 || idx > size - 1) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
	}

	/**
	 * List node object used to create SortedLists
	 * @author Rohan Patel
	 * @author Daniel Li
	 */
	private class ListNode {
		
		/**
		 * data stored in a list node
		 */
		public E data;
		
		/**
		 * Next listnode
		 */
		public ListNode next;
		
		/**
		 * Creates a new ListNode
		 * @param data object being used to create list node
		 * @param next list node being created
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}
}
