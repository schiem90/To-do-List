package gatech.todoapp.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import gatech.todoapp.domain.Category;
import gatech.todoapp.domain.Task;

/**
 * This class provides utilities for filtering a list of tasks.
 */
public class TaskFilter {

	/**
	 * Returns a filtered list of tasks from a list and filter conditions.  Use null
	 * to ignore a filter condition.
	 * @param taskList The list of tasks to filter
	 * @param date The date to filter by
	 * @param category The category to filter by
	 * @param location The location to filter by
	 * @param searchTerm The term to search for in task description
	 * @param completed Filter by completed or incomplete tasks
	 * @return The filtered list of tasks
	 */
	public static List<Task> filterTaskList(List<Task> taskList, Date date, Category category, 
			String location, String searchTerm, Boolean completed) {
		List<Task> filteredList = new ArrayList<Task>();
		
		for (Task t : taskList) {
			if (compareDate(date, t) || compareCategory(category, t) || compareLocation(location, t) ||
					searchForTerm(searchTerm, t)) {
				filteredList.add(t);
			}
		}
		
		return filteredList;
	}
	
	/**
	 * Compares date of task and date from filter.
	 * @param date The date to filter by
	 * @param task The task to compare to
	 * @return A boolean on whether or not the task is selected by filter
	 */
	private static boolean compareDate(Date date, Task task) {
		if (date == null)
			return false;
		//We will probably have to do more filtering than just tasks on the same day
		if (date.getDay() == task.getDate().getDay())
			return true;
		
		return false;
	}
	
	/**
	 * Compares category of task and category from filter.
	 * @param category The category to filter by
	 * @param task The task to compare to
	 * @return A boolean on whether or not the task is selected by filter
	 */
	private static boolean compareCategory(Category category, Task task) {
		if (category == null)
			return false;
		
		if (category.equals(task.getCategory()))
			return true;
		
		return false;
	}
	
	/**
	 * Compares location of task and location from filter.
	 * @param location The location to filter by
	 * @param task The task to compare to
	 * @return A boolean on whether or not the task is selected by filter
	 */
	private static boolean compareLocation(String location, Task task) {
		if (location == null)
			return false;
		//Probably have to filter a lot more with this, stuff in range etc
		if (location.equals(task.getLocation()))
			return true;
		
		return false;
	}
	
	/**
	 * Searches for a term in the task description.
	 * @param searchTerm The term to search for
	 * @param task The task to compare to
	 * @return A boolean on whether or not the task is selected by filter
	 */
	private static boolean searchForTerm(String searchTerm, Task task) {
		if (searchTerm == null)
			return false;
		if (task.getDescription().contains(searchTerm))
			return true;
		
		return false;
	}
}
