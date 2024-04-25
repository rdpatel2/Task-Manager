package edu.ncsu.csc216.wolf_tasks.model.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the SortedList class
 * @author Daniel Li
 * @author Rohan Patel
 */
class SortedListTest {

	/**
	 * Tests the remove() method
	 */
	@Test
	void testRemove() {
		SortedList<String> s = new SortedList<String>();
		s.add("Banana");
		s.add("Strawberry");
		s.add("Grape");
		s.add("Apple");
		s.remove(0);
		assertEquals(3, s.size());
	}

	/**
	 * Tests the contains() method
	 */
	@Test
	void testContains() {
		SortedList<String> s = new SortedList<String>();
		s.add("Banana");
		s.add("Grape");
		s.add("Apple");
		assertTrue(s.contains("Banana"));
		assertTrue(s.contains("Apple"));
		assertFalse(s.contains("Strawberry"));
	}

	/**
	 * Tests the get() method
	 */
	@Test
	void testGet() {
		SortedList<String> s = new SortedList<String>();
		s.add("Banana");
		s.add("Grape");
		assertEquals("Banana", s.get(0));
	}


	/**
	 * Tests the add() method
	 */
	@Test
	void testAdd() {
		SortedList<String> s = new SortedList<String>();
		s.add("Banana");
		assertEquals(1, s.size());
		assertEquals("Banana", s.get(0));
		s.add("Strawberry");
		assertEquals(2, s.size());
		assertEquals("Banana", s.get(0));
		assertEquals("Strawberry", s.get(1));
		s.add("Grape");
		assertEquals(3, s.size());
		assertEquals("Banana", s.get(0));
		assertEquals("Grape", s.get(1));
		assertEquals("Strawberry", s.get(2));
		s.add("Apple");
		assertEquals(4, s.size());
		assertEquals("Apple", s.get(0));
		assertEquals("Banana", s.get(1));
		assertEquals("Grape", s.get(2));
		assertEquals("Strawberry", s.get(3));
		s.add("Blueberry");
		assertEquals(5, s.size());
		assertEquals("Apple", s.get(0));
		assertEquals("Banana", s.get(1));
		assertEquals("Blueberry", s.get(2));
		assertEquals("Grape", s.get(3));
		assertEquals("Strawberry", s.get(4));
		s.add("Tomato");
		assertEquals(6, s.size());
		assertEquals("Apple", s.get(0));
		assertEquals("Banana", s.get(1));
		assertEquals("Blueberry", s.get(2));
		assertEquals("Grape", s.get(3));
		assertEquals("Strawberry", s.get(4));
		assertEquals("Tomato", s.get(5));
		
		Exception e1 = assertThrows(NullPointerException.class, 
				() -> s.add(null));
		assertEquals("Cannot add null element.", e1.getMessage());
		
		Exception e2 = assertThrows(IllegalArgumentException.class, 
				() -> s.add("Tomato"));
		assertEquals("Cannot add duplicate element.", e2.getMessage());
		
	}
	

	/**
	 * Tests the SortedList constructor 
	 */
	@Test
	void testSortedList() {
		SortedList<String> s = new SortedList<String>();
		assertEquals(0, s.size());
	}

}
