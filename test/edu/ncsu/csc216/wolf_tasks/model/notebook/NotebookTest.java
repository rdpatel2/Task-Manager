package edu.ncsu.csc216.wolf_tasks.model.notebook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tasks.model.tasks.ActiveTaskList;
import edu.ncsu.csc216.wolf_tasks.model.tasks.Task;
import edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList;

/**
 * Tests the Notebook class
 * @author Rohan Patel
 * @author Daniel Li	
 */
class NotebookTest {

	/**
	 * Tests the Notebook constructor
	 */
	@Test
	void testNotebook() {
		Notebook n = new Notebook("test");
		assertEquals("test", n.getNotebookName());
	}

	/**
	 * Tests the isChanged() method
	 */
	@Test
	void testIsChanged() {
		Notebook n = new Notebook("test");
		n.setChanged(true);
		assertTrue(n.isChanged());
	}

	/**
	 * Tests the addTaskList() method
	 */
	@Test
	void testAddTaskList() {
		Notebook n = new Notebook("test");
		TaskList t = new TaskList("Active TASks", 0);
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> n.addTaskList(t));
		assertEquals("Invalid name.", e1.getMessage());
		TaskList q = new TaskList("test", 0);
		n.addTaskList(q);
		TaskList r = new TaskList("test", 0);
		Exception e2 = assertThrows(IllegalArgumentException.class, 
				() -> n.addTaskList(r));
		assertEquals("Invalid name.", e2.getMessage());
	}

	/**
	 * Tests the getTaskListsNames() method
	 */
	@Test
	void testGetTaskListsNames() {
		Notebook n = new Notebook("test");

        String[] expectedEmpty = { "Active Tasks" };
        assertArrayEquals(expectedEmpty, n.getTaskListsNames());

        TaskList t = new TaskList("Test Task List", 0);
        n.addTaskList(t);
        String[] expectedOne = { "Active Tasks", "Test Task List" };
        assertArrayEquals(expectedOne, n.getTaskListsNames());

	}

	/**
	 * Tests the setCurrentTaskList() method
	 */
	@Test
	void testSetCurrentTaskList() {
		 Notebook n = new Notebook("test");
	        TaskList t1 = new TaskList("Test Task List 1", 0);
	        TaskList t2 = new TaskList("Test Task List 2", 0);

	        n.addTaskList(t1);
	        n.addTaskList(t2);

	        // Set current task list to an existing task list
	        n.setCurrentTaskList("Test Task List 1");
	        assertEquals("Test Task List 1", n.getCurrentTaskList().getTaskListName());

	        // Set current task list to an existing task list with different case
	        n.setCurrentTaskList("tEsT tAsK LiSt 2");
	        assertEquals("Active Tasks", n.getCurrentTaskList().getTaskListName());

	        // Set current task list to a non-existing task list, should default to active task list
	        n.setCurrentTaskList("Non-existing Task List");
	        assertTrue(n.getCurrentTaskList() instanceof ActiveTaskList);
	}

	/**
	 * Tests the editTaskList() method
	 */
	@Test
	void testEditTaskList() {
		Notebook n = new Notebook("test");
        TaskList t1 = new TaskList("Test Task List 1", 0);
        TaskList t2 = new TaskList("Test Task List 2", 0);

        n.addTaskList(t1);

        // Edit existing task list
        n.editTaskList("Edited Task List Name");
        assertEquals("Edited Task List Name", n.getTaskListsNames()[1]);

        // Attempt to edit with a duplicate name
        n.addTaskList(t2);
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> n.editTaskList("Edited Task List Name"));
        assertEquals("Invalid name.", e.getMessage());

        // Attempt to edit the active task list
        Exception e2 = assertThrows(IllegalArgumentException.class, () -> n.editTaskList("Active Tasks"));
        assertEquals("Invalid name.", e2.getMessage());
	}

	/**
	 * Tests the removeTaskList() method
	 */
	@Test
	void testRemoveTaskList() {
		Notebook n = new Notebook("test");
        TaskList t1 = new TaskList("Test Task List 1", 0);
        TaskList t2 = new TaskList("Test Task List 2", 0);

        // Add task lists to the notebook
        n.addTaskList(t1);
        n.addTaskList(t2);

        // Remove an existing task list
        n.removeTaskList();
        assertEquals("Active Tasks", n.getCurrentTaskList().getTaskListName());

        // Attempt to remove the active task list
        Exception e = assertThrows(IllegalArgumentException.class, () -> n.removeTaskList());
        assertEquals("The Active Tasks list may not be deleted.", e.getMessage());

        // Verify that the task list is removed
        assertEquals(2, n.getTaskListsNames().length);
	}

	/**
	 * Tests the addTask() method
	 */
	@Test
	void testAddTask() {
		Notebook notebook = new Notebook("Test notebook");
		TaskList t = new TaskList("Test TL", 0);
		notebook.addTaskList(t);
		Task t1 = new Task("Test 1", "Description 1", false, true);
		notebook.setCurrentTaskList("Test TL");
		notebook.addTask(t1);
		assertEquals(1, notebook.getCurrentTaskList().getTasks().size());
	}
//
//	/**
//	 * Tests the editTask() method
//	 */
//	@Test
//	void testEditTask() {
//		fail("Not yet implemented");
//	}

}
