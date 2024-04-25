package edu.ncsu.csc216.wolf_tasks.model.tasks;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tasks.model.notebook.Notebook;

/**
 * Tests the Task class
 * @author Rohan Patel
 * @author Daniel Li
 */
class TaskTest {


	/**
	 * Tests the getTaskName() method
	 */
	@Test
	void testGetTaskName() {
		Task t1 = new Task("1", "test 1", false, false);
		assertEquals("1", t1.getTaskName());
		t1.setTaskName("10");
		assertEquals("10", t1.getTaskName());
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> t1.setTaskName(null));
		assertEquals("Incomplete task information.", e1.getMessage());
		
	}

	/**
	 * Tests the getTaskDescription() methood
	 */
	@Test
	void testGetTaskDescription() {
		Task t1 = new Task("1", "test 1", false, false);
		assertEquals("test 1", t1.getTaskDescription());
		t1.setTaskDescription("testing");
		assertEquals("testing", t1.getTaskDescription());
		
	}
	
	/**
	 * Tests the setTaskDescription() method
	 */
	@Test
	void testSetTaskDescription() {
		Task t1 = new Task("1", "test 1", false, false);
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> t1.setTaskDescription(null));
		assertEquals("Incomplete task information.", e1.getMessage());
	}

	/**
	 * Tests the setIsRecurring() method
	 */
	@Test
	void testSetIsRecurring() {
		Task t1 = new Task("1", "test 1", false, false);
		t1.setIsRecurring(true);
		assertTrue(t1.isRecurring());
	}

	/**
	 * Tests the setActive() method
	 */
	@Test
	void testSetActive() {
		Task t1 = new Task("1", "test 1", false, false);
		t1.setActive(true);
		assertTrue(t1.isActive());
	}

//	/**
//	 * Tests the getTaskListName() method
//	 */
//	@Test
//	void testGetTaskListName() {
//		fail("Not yet implemented");
//	}

	/**
	 * Tests the addTaskList() method
	 */
	@Test
	void testAddTaskList() {
		Task t1 = new Task("1", "test 1", false, false);
		Task t2 = new Task("2", "test 2", true, false);
		Task t3 = new Task("3", "test 3", false, true);
		Task t4 = new Task("4", "test 4", true, true);
		AbstractTaskList a = new TaskList("test", 1);	
		AbstractTaskList b = new TaskList("test2", 0);	
		t1.addTaskList(a);
		t2.addTaskList(b);
		t3.addTaskList(b);
		t4.addTaskList(b);
		assertEquals("test", t1.getTaskListName());	
		assertEquals("test2", t2.getTaskListName());
		assertEquals("test2", t3.getTaskListName());
		assertEquals("test2", t4.getTaskListName());
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
		AbstractTaskList b = new TaskList("test2", 0);	
		t1.addTaskList(a);
		b.addTask(t2);
		b.addTask(t3);
		b.addTask(t4);
		t3.completeTask();
		assertEquals(2, b.getTasks().size());
		t2.completeTask();
		assertEquals("2", b.getTask(1).getTaskName());
		
 		Notebook notebook = new Notebook("Notebook");
 		TaskList c = new TaskList("TaskList1", 0);
 		Task t5 = new Task("Task1", "Task1Description", true, true);
 		c.addTask(t5);
 		c.addTask(new Task("Task2", "Task2Description", true, false));
 		c.addTask(new Task("Task3", "Task3Description", false, true));
 		notebook.addTaskList(c);
 		TaskList d = new TaskList("TaskList2", 0);
 		d.addTask(new Task("Task4", "Task41Description", true, true));
 		d.addTask(new Task("Task5", "Task5Description", false, false));
 		notebook.addTaskList(d);
 		notebook.setCurrentTaskList("Active Tasks");
 		t5.completeTask();
 		assertEquals(notebook.getCurrentTaskList().getTaskListName(), "Active Tasks");
 		assertEquals(1, notebook.getCurrentTaskList().getCompletedCount());
 		assertEquals("Task1", notebook.getCurrentTaskList().getTask(2).getTaskName());
	}
	
//	/**
//	 * Tests the clone() method
//	 */
//	@Test
//	void testClone() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Tests the toString() method
//	 */
//	@Test
//	void testToString() {
//		fail("Not yet implemented");
//	}

}
