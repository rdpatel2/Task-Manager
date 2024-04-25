/**
 * 
 */
package edu.ncsu.csc216.wolf_tasks.model.util;

/**
 * Creates a SwapList containing generic type E
 * @author Rohan Patel
 * @author Daniel Li
 * @param <E> generic element of list
 */
public class SwapList<E> implements ISwapList<E> {
	
	/**
	 * The initial capacity for a new list
	 */
	private static final int INITIAL_CAPACITY = 10;
	
	/**
	 * array of object E
	 */
	private E[] list;
	
	/**
	 * size of the list
	 */
	private int size;
	
	/**
	 * Creates a new list of tasks
	 */
	@SuppressWarnings("unchecked")
	public SwapList() {
		list = (E[]) new Object[INITIAL_CAPACITY];
		size = 0;
	}
	
	/**
	 * Checks capacity of the list and grows the array if necessary
	 * @param capacity capacity of list
	 */
	@SuppressWarnings("unchecked")
	private void checkCapacity(int capacity) {
		if (capacity == list.length) {
			E[] list2 = (E[]) new Object[list.length * 2];
			for (int i = 0; i < size; i++) {
				list2[i] = list[i];
			}
			list = list2;
		}
	}
	
	/**
	 * Checks index of the list
	 * @param idx index to check
	 * @throws IndexOutOfBoundsException if idx is less than 0 or greater than size - 1
	 */
	private void checkIndex(int idx) {
		if (idx < 0 || idx > size - 1) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
	}
	
	@Override
	public void add(E element) {
		if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		checkCapacity(size + 1);
		list[size] = element;
		size++;
	}
	
	@Override
	public E remove(int idx) {
		checkIndex(idx);
		E removed = list[idx];
		for (int i = idx; i < size - 1; i++) {
			list[i] = list[i + 1];
		}
		list[size - 1] = null;
		size--;
		return removed;
	}

	@Override
	public void moveUp(int idx) {
		checkIndex(idx);
		if (idx != 0) {
			E tempTop = list[idx - 1];
			E tempBottom = list[idx];
			list[idx - 1] = tempBottom;
			list[idx] = tempTop;
		}
	}

	@Override
	public void moveDown(int idx) {
		checkIndex(idx);
		if (idx != size - 1) {
			E tempTop = list[idx];
			E tempBottom = list[idx + 1];
			list[idx] = tempBottom;
			list[idx + 1] = tempTop;
		}
		
	}

	@Override
	public void moveToFront(int idx) {
		checkIndex(idx);
		if (idx != 0) {
			for (int i = idx; i > 0; i--) {
				moveUp(i);
			}
		}
	}

	@Override
	public void moveToBack(int idx) {
		checkIndex(idx);
		if (idx != size - 1) {
			for (int i = idx; i < size; i++) {
				moveDown(i);
			}
		}
	}

	@Override
	public E get(int idx) {
		checkIndex(idx);
		return list[idx];
	}

	@Override
	public int size() {
		return size;
	}
	
}
