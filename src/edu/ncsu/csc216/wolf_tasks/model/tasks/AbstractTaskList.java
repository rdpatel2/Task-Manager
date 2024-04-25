package edu.ncsu.csc216.wolf_tasks.model.tasks;

import edu.ncsu.csc216.wolf_tasks.model.util.ISwapList;
import edu.ncsu.csc216.wolf_tasks.model.util.SwapList;

/**
 * The AbstractTaskList class is an abstract class at the top of the hierarchy for task lists. 
 * The AbstractTaskList knows its taskListName, the ISwapList of Tasks, and the number of completed tasks.
 * @author Rohan Patel
 * @author Daniel Li
 */
public abstract class AbstractTaskList {
	
	/** Name of task list */
	private String taskListName;
	/** Number of completed tasks */
	private int completedCount;
	/** List of tasks **/
	private ISwapList<Task> tasks;
	
	/**
	 * Constructs an abstract task list for tasks objects with a name and number of completed tasks
	 * @param name name of task list
	 * @param completed number of completed tasks
	 * @throws IllegalArgumentException if name is empty or null or if completed count is less than 0
	 */
	public AbstractTaskList(String name, int completed) {
		if (name == null || name.isEmpty())
			throw new IllegalArgumentException("Invalid name.");
		if (completed < 0)
			throw new IllegalArgumentException("Invalid completed count.");
		setTaskListName(name);
		completedCount = completed;
		tasks = new SwapList<Task>();
		
	}
	
	/**
	 * Returns the name  of the task list
	 * @return name of the task list
	 */
	public String getTaskListName() {
		return taskListName;
	}
	
	/**
	 * Sets the name of the task list
	 * @param name name of the task list
	 */
	public void setTaskListName(String name) {
		this.taskListName = name;
	}
	
	/**
	 * Returns list of tasks
	 * @return list of tasks
	 */
	public ISwapList<Task> getTasks() {
		return tasks;
	}
	
	/**
	 * Returns the number of completed tasks
	 * @return the number of completed tasks
	 */
	public int getCompletedCount() {
		return completedCount;
	}
	
	/**
	 * Adds tasks to the task list while adding the list to the task object
	 * @param t Task to be added
	 */
	public void addTask(Task t) {
		tasks.add(t);
		t.addTaskList(this);
	}
	
	/**
	 * Removes task at a given index
	 * @param idx index to remove
	 * @return task that was removed at index
	 */
	public Task removeTask(int idx) {
		return tasks.remove(idx);
	}
	
	/**
	 * Gets task at a given index
	 * @param idx index to retrieve
	 * @return task at index
	 */
	public Task getTask(int idx) {
		return tasks.get(idx);
	}
	
	/**
	 * Removes task and increases completed task count if given task is in the current list
	 * @param t Task to mark as completed
	 */
	public void completeTask(Task t) {
		for(int i = 0; i < tasks.size(); i++) {
			if (t == tasks.get(i)) {
				tasks.remove(i);
				completedCount++;
				break;
			}
		}
	}
	
	/** 
	 * Returns the tasks and its fields in current list as a 2D array
	 * @return 2D array of tasks and fields
	 */
	public abstract String[][] getTasksAsArray();
}
