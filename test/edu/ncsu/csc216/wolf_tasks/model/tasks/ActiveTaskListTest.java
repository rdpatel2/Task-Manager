package edu.ncsu.csc216.wolf_tasks.model.tasks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ActiveTaskListTest {

	@Test
	void testSetTaskListName() {
		AbstractTaskList a = new ActiveTaskList();
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> a.setTaskListName("test"));
		assertEquals("The Active Tasks list may not be edited.", e1.getMessage());
		a.setTaskListName("Active Tasks");
		assertEquals("Active Tasks", a.getTaskListName());
	}

	@Test
	void testAddTask() {
		AbstractTaskList a = new ActiveTaskList();
		Task t2 = new Task("2", "test 2", true, false);
		Task t3 = new Task("3", "test 3", false, true);
		Task t4 = new Task("4", "test 4", true, true);
		a.addTask(t3);
		a.addTask(t4);
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> a.addTask(t2));
		assertEquals("Cannot add task to Active Tasks.", e1.getMessage());
	}

	@Test
	void testGetTasksAsArray() {
		AbstractTaskList  activeTaskList = new ActiveTaskList();
        Task task1 = new Task("Task 1", "Description 1", false, true);
        Task task2 = new Task("Task 2", "Description 2", false, true);
        activeTaskList.addTask(task1);
        activeTaskList.addTask(task2);

        String[][] tasksArray = activeTaskList.getTasksAsArray();

        assertEquals(2, tasksArray.length);
        assertEquals("Active Tasks", tasksArray[0][0]); // Assuming Active Tasks is always the task list name
        assertEquals("Task 1", tasksArray[0][1]);
        assertEquals("Active Tasks", tasksArray[1][0]);
        assertEquals("Task 2", tasksArray[1][1]);
	}

	@Test
	void testClearTasks() {
		ActiveTaskList activeTaskList = new ActiveTaskList();
		Task task1 = new Task("Task 1", "Description 1", false, true);
        Task task2 = new Task("Task 2", "Description 2", false, true);
        activeTaskList.addTask(task1);
        activeTaskList.addTask(task2);
        
        activeTaskList.clearTasks();
        assertEquals(0, activeTaskList.getTasks().size());
	}

}
