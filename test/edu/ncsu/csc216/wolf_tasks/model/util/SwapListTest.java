package edu.ncsu.csc216.wolf_tasks.model.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the SwapList class
 * @author Rohan Patel
 * @author Daniel Li
 */
class SwapListTest {

	/**
	 * Tests the SwapList constructor
	 */
	@Test
	void testSwapList() {
		SwapList<String> s = new SwapList<String>();
		assertEquals(0, s.size());
	}

	/** 
	 * Tests the add() method
	 */
	@Test
	void testAdd() {
		SwapList<String> s = new SwapList<String>();
		s.add("Banana");
		s.add("Apple");
		s.add("Cherry");
		s.add("Date");
		assertEquals(4, s.size());
		
		Exception e1 = assertThrows(NullPointerException.class, 
				() -> s.add(null));
		assertEquals("Cannot add null element.", e1.getMessage());
		
		s.add("Grape");
		s.add("Pineapple");
		s.add("Strawberry");
		s.add("Pear");
		s.add("Dragon Fruit");
		s.add("Mango");
		s.add("Blueberry");
		
		assertEquals("Blueberry", s.get(10));
	}

	/**
	 * Tests the remove() method
	 */
	@Test
	void testRemove() {
		SwapList<String> s = new SwapList<String>();
		s.add("Banana");
		s.add("Apple");
		s.add("Cherry");
		s.add("Date");
		assertEquals("Cherry", s.remove(2));
		assertEquals(3, s.size());
		
		Exception e1 = assertThrows(IndexOutOfBoundsException.class, 
				() -> s.remove(-1));
		
		Exception e2 = assertThrows(IndexOutOfBoundsException.class, 
				() -> s.remove(4));
		
		assertEquals("Invalid index.", e1.getMessage());
		assertEquals(e1.getMessage(), e2.getMessage());
	}

	/** 
	 * Tests the moveUp() method
	 */
	@Test
	void testMoveUp() {
		SwapList<String> s = new SwapList<String>();
		s.add("Banana");
		s.add("Apple");
		s.add("Cherry");
		s.add("Date");
		s.moveUp(1);
		assertEquals("Apple", s.get(0));
		assertEquals("Banana", s.get(1));
	}

	/**
	 * Tests the moveDown() method
	 */
	@Test
	void testMoveDown() {
		SwapList<String> s = new SwapList<String>();
		s.add("Banana");
		s.add("Apple");
		s.add("Cherry");
		s.add("Date");
		s.moveDown(0);
		assertEquals("Banana", s.get(1));
		assertEquals("Apple", s.get(0));
	}

	/**
	 * Tests the moveToFront() method
	 */
	@Test
	void testMoveToFront() {
		SwapList<String> s = new SwapList<String>();
		s.add("Banana");
		s.add("Apple");
		s.add("Cherry");
		s.add("Date");
		s.moveToFront(3);
		assertEquals("Date", s.get(0));
		assertEquals("Cherry", s.get(3));
		assertEquals("Apple", s.get(2));
		assertEquals("Banana", s.get(1));
	}

	/**
	 * Tests the moveToBack() method
	 */
	@Test
	void testMoveToBack() {
		SwapList<String> s = new SwapList<String>();
		s.add("Banana");
		s.add("Apple");
		s.add("Cherry");
		s.add("Date");
		s.moveToBack(1);
		assertEquals("Date", s.get(2));
		assertEquals("Cherry", s.get(1));
		assertEquals("Apple", s.get(3));
		assertEquals("Banana", s.get(0));
	}
	
//	/** 
//	 * Tests the get() method
//	 */
//	@Test
//	void testGet() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Tests the size() method
//	 */
//	@Test
//	void testSize() {
//		fail("Not yet implemented");
//	}

}
