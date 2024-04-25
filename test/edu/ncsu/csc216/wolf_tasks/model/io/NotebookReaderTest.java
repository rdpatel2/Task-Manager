package edu.ncsu.csc216.wolf_tasks.model.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tasks.model.notebook.Notebook;
import edu.ncsu.csc216.wolf_tasks.model.tasks.AbstractTaskList;
import edu.ncsu.csc216.wolf_tasks.model.tasks.Task;

/** 
 * Tests the NotebookReader class
 * @author Rohan Patel
 * @author Daniel Li
 */
class NotebookReaderTest {

	/**
	 * Tests the readNoteBookFile method in NotebookReader
	 */
	@Test
	void testReadNotebookFile() {
		File file = new File("test-files/notebook1.txt"); // Use the appropriate file path
        Notebook notebook = NotebookReader.readNotebookFile(file);

        // Check that the notebook is not null
        assertNotNull(notebook);

        // Check the notebook name
        assertEquals("School", notebook.getNotebookName());

        // Check the number of task lists in the notebook
        assertEquals(4, notebook.getTaskListsNames().length);

        // Check task list names
        String[] expectedTaskLists = {"Active Tasks", "CSC 216", "CSC 226", "Habits"};
        assertArrayEquals(expectedTaskLists, notebook.getTaskListsNames());
        
        notebook.setCurrentTaskList("Active Tasks");
        assertEquals(5, notebook.getCurrentTaskList().getTasks().size());
        
        // Check tasks in the first task list
        notebook.setCurrentTaskList("CSC 216");
        AbstractTaskList taskList216 = notebook.getCurrentTaskList();
        assertEquals(9, taskList216.getTasks().size()); // Expected number of tasks in CSC 216 task list

        Task task1 = taskList216.getTask(0);
        assertEquals("Read Project 2 Requirements", task1.getTaskName());
        assertEquals("Read Project 2 requirements\n(https://pages.github.ncsu.edu/engr-csc216-staff/CSC216-SE-Materials/projects/project2/project2-part1.html)\nand identify candidate classes and methods.", task1.getTaskDescription());

        Task task2 = taskList216.getTask(1);
        assertEquals("Create CRC Cards", task2.getTaskName());
        assertEquals("Identify the key classes and create CRC cards. Note\nresponsibilities, collaborators, and possible state.", task2.getTaskDescription());
	}

}
