/**
 * 
 */
package edu.ncsu.csc216.wolf_tasks.model.notebook;

import java.io.File;

import edu.ncsu.csc216.wolf_tasks.model.io.NotebookWriter;
import edu.ncsu.csc216.wolf_tasks.model.tasks.AbstractTaskList;
import edu.ncsu.csc216.wolf_tasks.model.tasks.ActiveTaskList;
import edu.ncsu.csc216.wolf_tasks.model.tasks.Task;
import edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList;
import edu.ncsu.csc216.wolf_tasks.model.util.ISortedList;
import edu.ncsu.csc216.wolf_tasks.model.util.SortedList;

/**
 * Creates a new notebook object to store tasks 
 * @author Daniel Li
 * @author Rohan Patel
 */
public class Notebook {
	
	/** Name of the notebook */
	private String notebookName; 
	/** Checks if the notebook is changed */
	private boolean isChanged;
	/** List of all the task lists */
	private ISortedList<TaskList> taskLists;
	/** Task list that holds active tasks */
	private ActiveTaskList activeTaskList;
	/** Current task list */
	private AbstractTaskList currentTaskList;
	
	
	/**
	 * Creates a new Notebook object
	 * @param name name of the new notebook
	 * @throws IllegalArgumentException if the name is null, empty, or matches ACTIVE_TASKS_NAME
	 */
	public Notebook(String name) {
		taskLists = new SortedList<>();
		activeTaskList = new ActiveTaskList();
		currentTaskList = activeTaskList;
		isChanged = true;
		if (name == null || name.isEmpty() || name.equals(activeTaskList.getTaskListName()))
			throw new IllegalArgumentException("Invalid name.");
		setNotebookName(name);
	}
	
	/**
	 * Saves the notebook to a file
	 * @param outputFile the file being saved to
	 * @throws IllegalArgumentException if the notebook is unable to be saved to a file
	 */
	public void saveNotebook(File outputFile) {
		try {
			NotebookWriter.writeNotebookFile(outputFile, getNotebookName(), taskLists);
			isChanged = false;
		}
		catch (Exception e) {
			throw new IllegalArgumentException("Unable to save file.");
		}
	}
	
	/**
	 * Gets the notebook name
	 * @return the name of the notebook
	 */
	public String getNotebookName() {
		return notebookName;
	}
	
	/**
	 * Sets the name of the notebook
	 * @param name name of notebook
	 */
	private void setNotebookName(String name) {
		this.notebookName = name;
	}
	
	/**
	 * Checks if the notebook has been changed
	 * @return true if it's been changed, false if not
	 */
	public boolean isChanged() {
		return isChanged;
	}
	
	/**
	 * Sets whether or not the notebook has been changed
	 * @param changed true if changed, false if not
	 */
	public void setChanged(boolean changed) {
		this.isChanged = changed;
	}
	
	/**
	 * Adds a task list to the notebook
	 * @param taskList task list being added to the notebook
	 * @throws IllegalArgumentException if the name is the same as active task list or a duplicate name
	 */
	public void addTaskList(TaskList taskList) {
		if (taskList.getTaskListName().equalsIgnoreCase("Active Tasks"))
			throw new IllegalArgumentException("Invalid name.");
		if (taskLists.size() != 0) {
			for (int i = 0; i < taskLists.size(); i++) {
				if (taskList.getTaskListName().equalsIgnoreCase(taskLists.get(i).getTaskListName()))
					throw new IllegalArgumentException("Invalid name.");
			}
		}
		taskLists.add(taskList);
		currentTaskList = taskList;
		getActiveTaskList();
		isChanged = true;
	}
	
	/**
	 * Gets the different task lists names that are inside of the notebook
	 * @return an array of the different task lists inside the notebook
	 */
	public String[] getTaskListsNames() {
		String[] names = new String[taskLists.size() + 1];
		getActiveTaskList();
		names[0] = activeTaskList.getTaskListName();
		for(int i = 0; i < taskLists.size(); i++) {
			names[i + 1] = taskLists.get(i).getTaskListName();
		}
		return names;
	}
	
	/**
	 * Gets the active tasks in the notebook
	 */
	private void getActiveTaskList() {
		activeTaskList.clearTasks();
		for (int i = 0; i < taskLists.size(); i++) {
			for(int j = 0; j < taskLists.get(i).getTasks().size(); j++) {	
				if (taskLists.get(i).getTasks().get(j).isActive())
					activeTaskList.addTask(taskLists.get(i).getTasks().get(j));
			}
		}
	}
	
	/**
	 * Sets the current task list with name
	 * @param name name that the current task list will have
	 */
	public void setCurrentTaskList(String name) {
		getActiveTaskList();
		boolean set = false;
		for (int i = 0; i < taskLists.size(); i++) {
			if (name.equals(taskLists.get(i).getTaskListName())) {
				currentTaskList = taskLists.get(i);
				set = true;
			}	
		}
		if (!set)
//			getActiveTaskList();
			currentTaskList = activeTaskList;
	}
	
	/**
	 * Gets the current task list
	 * @return the current task list
	 */
	public AbstractTaskList getCurrentTaskList() {
		return currentTaskList;
	}
	
	/**
	 * Edits current task list 
	 * @param name name of task list being edited
	 * @throws IllegalArgumentException if the currentTaskList is an ActiveTaskList, 
	 * if the new name matches “Active Tasks”, or is a duplicate of the name of another TaskList
	 */
	public void editTaskList(String name) {
		getActiveTaskList();
		if (currentTaskList instanceof ActiveTaskList)
			throw new IllegalArgumentException("The Active Tasks list may not be edited.");
		if (name.toUpperCase().equals("ACTIVE TASKS"))
			throw new IllegalArgumentException("Invalid name.");
		if (taskLists.size() != 0) {
			for (int i = 0; i < taskLists.size(); i++) {
				if (name.toUpperCase().equals(taskLists.get(i).getTaskListName().toUpperCase()))
					throw new IllegalArgumentException("Invalid name.");
			}
		}
		int index = 0;
		if (taskLists.size() != 0) {
			for (int i = 0; i < taskLists.size(); i++) {
				if (currentTaskList.getTaskListName().toUpperCase().equals(taskLists.get(i).getTaskListName().toUpperCase()))
					index = i;
			}
		}
		taskLists.remove(index);
		currentTaskList.setTaskListName(name);
		taskLists.add((TaskList)currentTaskList);
		isChanged = true;
	}
	
	/**
	 * Removes the current task list
	 * @throws IllegalArgumentException if the current list is the active task list
	 */
	public void removeTaskList() { 
		if (currentTaskList instanceof ActiveTaskList)
			throw new IllegalArgumentException("The Active Tasks list may not be deleted.");
		int index = 0;
		if (taskLists.size() != 0) {
			for (int i = 0; i < taskLists.size(); i++) {
				if (currentTaskList.getTaskListName().toUpperCase().equals(taskLists.get(i).getTaskListName().toUpperCase()))
					index = i;
			}
		}
		taskLists.remove(index);
		currentTaskList = activeTaskList;
		isChanged = true;
	}
	
	/**
	 * Adds task to notebook
	 * @param t task being added
	 */
	public void addTask(Task t) {
		if (currentTaskList instanceof TaskList) {
			currentTaskList.addTask(t);
			if(t.isActive())
				activeTaskList.addTask(t);
			isChanged = true;
		}
	}
	
	/**
	 * Edits task
	 * @param idx task being edited
	 * @param taskName name of task being edited
	 * @param taskDescription description of task being edited 
	 * @param recurring whether task is recurring or not
	 * @param active whether task is active or not
	 */
	public void editTask(int idx, String taskName, String taskDescription, boolean recurring, boolean active) {
		if (currentTaskList instanceof TaskList) {
			currentTaskList.getTask(idx).setTaskName(taskName);
			currentTaskList.getTask(idx).setTaskDescription(taskDescription);
			currentTaskList.getTask(idx).setIsRecurring(recurring);
			currentTaskList.getTask(idx).setActive(active);
			if (active)
				getActiveTaskList();
			isChanged = true;
		}
	}
}
