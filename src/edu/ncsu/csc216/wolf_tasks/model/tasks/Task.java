/**
 * 
 */
package edu.ncsu.csc216.wolf_tasks.model.tasks;

import edu.ncsu.csc216.wolf_tasks.model.util.ISwapList;
import edu.ncsu.csc216.wolf_tasks.model.util.SwapList;

/**
 * Creates a task object 
 * @author Rohan Patel
 * @author Daniel Li
 */
public class Task implements Cloneable {
	
	/**
	 * Name of the task
	 */
	private String taskName;
	
	/**
	 * Description of the task
	 */
	private String taskDescription;
	
	/**
	 * Whether or not task is recurring
	 */
	private boolean recurring;
	
	/**
	 * Whether or not task is active
	 */
	private boolean active;

	/** List that holds the task lists */
	private ISwapList<AbstractTaskList> taskLists;
	
	/**
	 * Creates a new task object
	 * @param name name of the task
	 * @param taskDescription description of the task
	 * @param recurring whether or not task is recurring
	 * @param active whether or not task is active
	 */
	public Task(String name, String taskDescription, boolean recurring, boolean active) {
		setTaskName(name);
		setTaskDescription(taskDescription);
		setIsRecurring(recurring);
		setActive(active);
		taskLists = new SwapList<AbstractTaskList>();
	}
	
	/**
	 * Gets the name of a task
	 * @return name of task
	 */
	public String getTaskName() {
		return taskName;
	}
	
	/**
	 * Sets the name of a task
	 * @param name name of task
	 */
	public void setTaskName(String name) {
		if (name == null || name.isEmpty()) 
			throw new IllegalArgumentException("Incomplete task information.");
		this.taskName = name;
	}
	
	/**
	 * Gets the description of the task
	 * @return description of the task
	 */
	public String getTaskDescription() {
		return taskDescription;
	}
	
	/**
	 * Sets the description of the task
	 * @param description description of the task
	 */
	public void setTaskDescription(String description) {
		if (description == null) 
			throw new IllegalArgumentException("Incomplete task information.");
		this.taskDescription = description;
	}
	
	/**
	 * Checks if task is recurring
	 * @return whether or not the task is recurring
	 */
	public boolean isRecurring() {
		return recurring;
	}
	
	/**
	 * Sets the task to recurring or not recurring
	 * @param recurring whether or not it will be recurring
	 */
	public void setIsRecurring(boolean recurring) {
		this.recurring = recurring;
	}
	
	/**
	 * Checks if the task is active
	 * @return whether or not the task is active
	 */
	public boolean isActive() {
		return active;
	}
	
	/**
	 * Sets the task to active or inactive
	 * @param active sets to active if true, inactive if false
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	
	/**
	 * Gets the task list name
	 * @return name of the task list
	 */
	public String getTaskListName() {
		if (taskLists == null || taskLists.size() == 0)
			return "";
		else 
			return taskLists.get(0).getTaskListName();
	}
	
	/**
	 * Adds a task list of tasks
	 * @param list being added
	 * @throws IllegalArgumentException if the list is null
	 */
	public void addTaskList(AbstractTaskList list) {
		if (list == null)
			throw new IllegalArgumentException("Incomplete task information.");
		if(taskLists.size() != 0) {
			boolean duplicate = false;
			for(int i = 0; i < taskLists.size(); i++) {
				if (list.getTaskListName().equals(taskLists.get(i).getTaskListName())){
					duplicate = true;
				}
			}
			if (!duplicate)
				taskLists.add(list);
		}
		else {
			taskLists.add(list);
		}
	} 
	
	/**
	 * Sets a task to completed
	 */
	public void completeTask() {
		Task clone;
		if (isRecurring()) {
			try {
				clone = (Task) clone();
				for (int i = 0; i < taskLists.size(); i++) {
					taskLists.get(i).completeTask(this);
					taskLists.get(i).addTask(clone);
				}
			} catch (CloneNotSupportedException e) {
				//Catch
			}
		} else {
			for (int i = 0; i < taskLists.size(); i++) {
				taskLists.get(i).completeTask(this);
			}
		}
	}

	
	/**
	 * Clones a task
	 * @return The cloned object
	 * @throws CloneNotSupportedException if it cannot clone
	 */
	public Task clone() throws CloneNotSupportedException{
		if (taskLists == null || taskLists.size() == 0)
			throw new CloneNotSupportedException("Cannot clone.");
		return new Task(getTaskName(), getTaskDescription(), isRecurring(), isActive());
	}
	
	/**
	 * Creates a formatted task string
	 * @return formatted string
	 */
	public String toString() {
		if (isRecurring() && isActive()) {
			return "* " + getTaskName() + ",recurring,active\n" + getTaskDescription();
		}
		if (isRecurring()) {
			return "* " + getTaskName() + ",recurring\n" + getTaskDescription();
		}
		if (isActive()) {
			return "* " + getTaskName() + ",active\n" + getTaskDescription();
		}
		return "* " + getTaskName() + "\n" + getTaskDescription();
	}
}
