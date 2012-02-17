package gatech.todoapp.domain;

import java.util.Date;

/**
 * This class represents a Task of the to do list.
 *
 */
public class Task {
	
	private Integer ID;
	private String description;
	private String location; //We may want to change this to something other than a string later
	private Date date;
	private String comments;
	private Category category;
	
	/**
	 * Constructor that creates a new task with a description and
	 * a date.
	 * @param description The task's description
	 * @param date The date of the task
	 */
	public Task(String description, Date date) {
		this.description = description;
		this.date = date;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}
	
	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

}
