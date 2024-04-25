/**
 * 
 */
package edu.ncsu.csc216.wolf_tasks.model.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc216.wolf_tasks.model.notebook.Notebook;
import edu.ncsu.csc216.wolf_tasks.model.tasks.Task;
import edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList;

/**
 * IO class that reads in a notebook and tasks from a file
 * @author Rohan Patel
 * @author Daniel Li
 */
public class NotebookReader {
	
	/**
	 * Creates a new NotebookReader object
	 */
	public NotebookReader() {
		//Purposely left empty
	}
	
	
	 /**
     * Reads a notebook file and constructs the notebook with its task lists and tasks.
     * 
     * @param file the notebook file to read
     * @return the notebook object constructed from the file
     * @throws IllegalArgumentException if the file cannot be loaded or if there are any invalid task lists or tasks
     */
    public static Notebook readNotebookFile(File file) {
        try (Scanner fileScanner = new Scanner(file)) {
            StringBuilder fileContent = new StringBuilder();
            while (fileScanner.hasNextLine()) {
                fileContent.append(fileScanner.nextLine()).append("\n");
            }

            String content = fileContent.toString();
            
            if (!content.startsWith("!")) {
                throw new IllegalArgumentException("Unable to load file.");
            }

            String notebookName = content.substring(2, content.indexOf("\n"));
            content = content.substring(content.indexOf('\n') + 1);
            String[] taskListTokens = content.split("\\r?\\n?#");
            Notebook notebook = new Notebook(notebookName);
            
            for (String taskListToken : taskListTokens) {
                taskListToken = taskListToken.trim();
                try {
                    TaskList taskList = processTaskList(taskListToken);
                    notebook.addTaskList(taskList);
                }
                catch (Exception e) {
                	//Left empty
                }
               
            }
            notebook.setCurrentTaskList("Active Tasks");
            return notebook;
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Unable to load file.");
        }
    }

    /**
     * Parses a task list token and constructs a task list.
     * 
     * @param token the task list token to parse
     * @return the constructed task list
     */
    private static TaskList processTaskList(String token) {
        String[] lines = token.split("\\r?\\n?\\*");
        int completed = Integer.parseInt(lines[0].substring(lines[0].indexOf(',') + 1));
        String taskListName = lines[0].trim().substring(0, lines[0].indexOf(','));
        TaskList taskList = new TaskList(taskListName, completed);

        for (int i = 1; i < lines.length; i++) {
            String[] taskInfo = lines[i].trim().split("\\r?\\n");
            try {
				if (taskInfo.length > 0) {
				    Task task = processTask(taskInfo);
				    taskList.addTask(task);
				}
			} catch (Exception e) {
				// Left Empty
			}
        }

        return taskList;
    }

    /**
     * Parses task information and constructs a task.
     * 
     * @param info the array of task information
     * @return the constructed task
     */
    private static Task processTask(String[] info) {
        String taskName = info[0];
        boolean active = false;
        boolean recurring = false;
        if (taskName.contains("active")) {
        	active = true;
        }
        if (taskName.contains("recurring")) {
        	recurring = true;
        }
        if (taskName.contains(",")) {
        	taskName = taskName.substring(0, taskName.indexOf(','));
        }
        
        StringBuilder taskDescription = new StringBuilder();
        for (int i = 1; i < info.length; i++) {
            taskDescription.append(info[i].trim()).append("\n");
        }
        return new Task(taskName, taskDescription.toString().trim(), recurring, active);
    }
}
