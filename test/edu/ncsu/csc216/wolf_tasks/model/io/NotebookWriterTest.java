package edu.ncsu.csc216.wolf_tasks.model.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tasks.model.tasks.Task;
import edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList;
import edu.ncsu.csc216.wolf_tasks.model.util.ISortedList;
import edu.ncsu.csc216.wolf_tasks.model.util.SortedList;

/**
 * Tests the NotebookWriter class
 * @author Rohan Patel
 * @author Daniel Li
 */
class NotebookWriterTest {

	/** Test file name */
	private static final String OUTPUT_FILE = "test-files/testNotebook.txt";

	/** File to output to */
    private File outputFile;

    @BeforeEach
    void setUp() throws Exception {
        outputFile = new File(OUTPUT_FILE);
    }

    @AfterEach
    void tearDown() throws Exception {
        if (outputFile.exists()) {
            Files.delete(outputFile.toPath());
        }
    }

    @Test
    void testWriteNotebookFile() {
        // Create a sample task list
        TaskList taskList = new TaskList("Sample Task List", 0);
        taskList.addTask(new Task("Task 1", "Description 1", false, true));
        taskList.addTask(new Task("Task 2", "Description 2", true, false));

        // Create a sorted list of task lists
        ISortedList<TaskList> list = new SortedList<>();
        list.add(taskList);

        // Write the notebook file
        try {
            NotebookWriter.writeNotebookFile(outputFile, "Sample Notebook", list);
        } catch (Exception e) {
            fail("Exception thrown when writing notebook file: " + e.getMessage());
        }

        // Verify that the file was created
        assertTrue(outputFile.exists());

        // Read the content of the file and verify its correctness
        try (Scanner scanner = new Scanner(outputFile)) {
            assertEquals("! Sample Notebook", scanner.nextLine());
            assertEquals("# Sample Task List,0", scanner.nextLine());
            assertEquals("* Task 1,active", scanner.nextLine());
            assertEquals("Description 1", scanner.nextLine());
            assertEquals("* Task 2,recurring", scanner.nextLine());
            assertEquals("Description 2", scanner.nextLine());
            assertFalse(scanner.hasNext());
        } catch (IOException e) {
            fail("Exception thrown when reading notebook file: " + e.getMessage());
        }
    }
    
    /**
     * Tests if Notebook Writer is created
     */
    @Test
    public void testIssueWriterIsCreated() {
    	NotebookWriter nW = new NotebookWriter();
        assertTrue(nW instanceof NotebookWriter);
    }
}
