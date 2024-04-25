package edu.ncsu.csc216.wolf_tasks.model.tasks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the AbstractTaskList class
 * @author Rohan Patel
 * @author Daniel Li
 */
class AbstractTaskListTest {

	/**
	 * Tests the AbstractTaskList constructor
	 */
	@Test
	void testAbstractTaskList() {
		AbstractTaskList a = new TaskList("test", 1);
		assertEquals("test", a.getTaskListName());
		assertEquals(1, a.getCompletedCount());
		
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> new TaskList("", 1));
		assertEquals("Invalid name.", e1.getMessage());
		
		Exception e2 = assertThrows(IllegalArgumentException.class, 
				() -> new TaskList("test", -1));
		assertEquals("Invalid completed count.", e2.getMessage());
	}


	/**
	 * Tests the setTaskListName() method
	 */
	@Test
	void testSetTaskListName() {
		AbstractTaskList a = new TaskList("test", 1);
		a.setTaskListName("NotTest");
		assertEquals("NotTest", a.getTaskListName());
	}

	/**
	 * Tests the getTasks() method
	 */
	@Test
	void testGetTasks() {
		Task t1 = new Task("1", "test 1", false, false);
		Task t2 = new Task("2", "test 2", true, false);
		Task t3 = new Task("3", "test 3", false, true);
		Task t4 = new Task("4", "test 4", true, true);
		AbstractTaskList a = new TaskList("test", 1);	
		a.addTask(t1);
		a.addTask(t2);
		a.addTask(t3);
		a.addTask(t4);
		assertEquals(t1, a.getTasks().get(0));
		assertEquals(t2, a.getTasks().get(1));
		assertEquals(t3, a.getTasks().get(2));
		assertEquals(t4, a.getTasks().get(3));
	}


	/**
	 * Tests the removeTask() method
	 */
	@Test
	void testRemoveTask() {
		Task t1 = new Task("1", "test 1", false, false);
		Task t2 = new Task("2", "test 2", true, false);
		Task t3 = new Task("3", "test 3", false, true);
		Task t4 = new Task("4", "test 4", true, true);
		AbstractTaskList a = new TaskList("test", 1);	
		a.addTask(t1);
		a.addTask(t2);
		a.addTask(t3);
		a.addTask(t4);
		a.removeTask(2);
		assertEquals(3, a.getTasks().size());
		assertEquals(t1, a.getTask(0));
		assertEquals(t2, a.getTask(1));
		assertEquals(t4, a.getTask(2));
	}


	/**
	 * Tests the completeTask() method
	 */
	@Test
	void testCompleteTask() {
		Task t1 = new Task("1", "test 1", false, false);
		Task t2 = new Task("2", "test 2", true, false);
		Task t3 = new Task("3", "test 3", false, true);
		Task t4 = new Task("4", "test 4", true, true);
		AbstractTaskList a = new TaskList("test", 1);	
		a.addTask(t1);
		a.addTask(t2);
		a.addTask(t3);
		a.addTask(t4);
		a.completeTask(t2);
		assertEquals(2, a.getCompletedCount());
		assertEquals(3, a.getTasks().size());
	}

}
