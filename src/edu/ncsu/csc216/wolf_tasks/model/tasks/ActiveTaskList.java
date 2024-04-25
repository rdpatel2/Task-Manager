/**
 * 
 */
package edu.ncsu.csc216.wolf_tasks.model.tasks;

/**
 * Creates the task list for active tasks
 * @author Rohan Patel
 * @author Daniel Li
 */
public class ActiveTaskList extends AbstractTaskList {
	
	/**
	 * Name of the active task list
	 */
	public static final String ACTIVE_TASKS_NAME = "Active Tasks";
	
	/**
	 * Creates a new active task list
	 */
	public ActiveTaskList() {
		super(ACTIVE_TASKS_NAME, 0);
	}
	
	/**
	 * Adds task to active list
	 * @param t task being added
	 * @throws IllegalArgumentException if task is not active
	 */
	public void addTask(Task t) {
		if (t.isActive()) 
			super.addTask(t);
		else 
			throw new IllegalArgumentException("Cannot add task to Active Tasks.");
	}

	/**
	 * Returns the tasks as a 2d array with first column as name of belonging task list and second column as task name
	 * @return the 2d array of tasks
	 */
	@Override
	public String[][] getTasksAsArray() {
		String[][] arr = new String[getTasks().size()][2];
		for(int i = 0; i < getTasks().size(); i++) {
			arr[i][0] = getTasks().get(i).getTaskListName();
			arr[i][1] = getTasks().get(i).getTaskName();
		}
		return arr;
	}
	
	/**
	 * Sets the active task list name
	 * @param name of task list
	 * @throws IllegalArgumentException if name is not as expected
	 */
	public void setTaskListName(String name) {
		if(!name.equals(ACTIVE_TASKS_NAME)) {
			throw new IllegalArgumentException("The Active Tasks list may not be edited.");
		} else {
			super.setTaskListName(name);
		}
	}
	
	/**
	 * Clears the active task list
	 */
	public void clearTasks() {
		int size = getTasks().size();
		for (int i = 0; i < size; i++) {
			removeTask(0);
		}
	}
}
