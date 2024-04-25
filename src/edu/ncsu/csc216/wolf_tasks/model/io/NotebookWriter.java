/**
 * 
 */
package edu.ncsu.csc216.wolf_tasks.model.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList;
import edu.ncsu.csc216.wolf_tasks.model.util.ISortedList;

/**
 * IO class to write a notebook to files
 * @author Rohan Patel
 * @author Daniel Li 
 */
public class NotebookWriter {
	
	/**
	 * Creates an object that can write notebooks to file
	 */
	public NotebookWriter() {
		//Purposely left empty
	}
	
	/**
	 * Writes a notebook file
	 * @param outputfile file being written to
	 * @param name name of the notebook being written
	 * @param list list of tasks within the notebook
	 * @throws IllegalArgumentException if it is unable to save to file
	 */
	public static void writeNotebookFile(File outputfile, String name, ISortedList<TaskList> list) {
		try (PrintWriter writer = new PrintWriter(outputfile)){
			writer.print("! " + name + "\n");
			for (int i = 0; i < list.size(); i++) {
				writer.print("# " + list.get(i).getTaskListName() + "," + list.get(i).getCompletedCount() + "\n");
				for (int j = 0; j < list.get(i).getTasks().size(); j++) {
					writer.print(list.get(i).getTask(j).toString() + "\n");
				}
			}
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to save file.");
		}
	}
}
