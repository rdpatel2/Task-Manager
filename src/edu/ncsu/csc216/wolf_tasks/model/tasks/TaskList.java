package edu.ncsu.csc216.wolf_tasks.model.tasks;

/**
 * Creates a TaskList object holding tasks
 * @author Rohan Patel
 * @author Daniel Li
 */
public class TaskList extends AbstractTaskList implements Comparable<TaskList> {
	
	/**
	 * Creates a new Task list object
	 * @param name name of the task list
	 * @param completedTasks how many tasks have been completed
	 */
	public TaskList(String name, int completedTasks) {
		super(name, completedTasks);
	}
	
	/**
	 * Returns the tasks as a 2d array with first column as priority and second column as task name
	 * @return the 2d array of tasks
	 */
	public String[][] getTasksAsArray() {
		String[][] arr = new String[getTasks().size()][2];
		for(int i = 0; i < getTasks().size(); i++) {
			arr[i][0] = Integer.toString(i + 1);
			arr[i][1] = getTasks().get(i).getTaskName();
		}
		return arr;
	}

	/**
	 * Compares two task lists
	 * @param t task list being compared to
	 * @return an integer dependent on how the two task lists compare
	 */
	public int compareTo(TaskList t) {
		return this.getTaskListName().compareTo(t.getTaskListName());
	}


	
	
}
