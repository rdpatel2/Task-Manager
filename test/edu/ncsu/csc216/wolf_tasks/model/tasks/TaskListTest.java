package edu.ncsu.csc216.wolf_tasks.model.tasks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the TaskList class
 * @author Rohan Patel
 * @author Daniel Li
 */
class TaskListTest {

	/**
	 * Tests the getTasksAsArray() method
	 */
	@Test
	void testGetTasksAsArray() {
		AbstractTaskList  taskList = new TaskList("test", 0);
        Task task1 = new Task("Task 1", "Description 1", false, true);
        Task task2 = new Task("Task 2", "Description 2", false, true);
        taskList.addTask(task1);
        taskList.addTask(task2);

        String[][] tasksArray = taskList.getTasksAsArray();

        assertEquals(2, tasksArray.length);
        assertEquals("1", tasksArray[0][0]); // Assuming Active Tasks is always the task list name
        assertEquals("Task 1", tasksArray[0][1]);
        assertEquals("2", tasksArray[1][0]);
        assertEquals("Task 2", tasksArray[1][1]);
	}

//	/**
//	 * Tests the comapreToTaskList() method
//	 */
//	@Test
//	void testCompareToTaskList() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Tests the compareToObject() method
//	 */
//	@Test
//	void testCompareToObject() {
//		fail("Not yet implemented");
//	}

}
