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
	private boolean complete; //true if task is completed, false if otherwise
	
	/**
	 * Constructor that creates a new task with a description and
	 * a date.
	 * @param description The task's description
	 * @param date The date of the task
	 */
	public Task(String description, String location, Date date, String comments) {
		this.description = description;
		this.location = location;
		this.date = date;
		this.comments = comments;
		complete = false;
	}
	
	public Task() {
		ID = null;
	}
	
	@Override
	public boolean equals(Object other) {
	    if (other == null) 
	    	return false;
	    if (other == this) 
	    	return true;
	    if (!(other instanceof Task))
	    	return false;
	    Task otherTask = (Task) other;
	    if (ID.equals(otherTask.getID()))
	    	return true;
	    
	    return false;
	}
	
	/**
	 * @return the ID
	 */
	public Integer getID() {
		return ID;
	}
	
	/**
	 * @param ID the ID to set
	 */
	public void setID(Integer ID) {
		this.ID = ID;
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
	
	/** Standard toString method that turns Task into a String
	 */
	public String toString()
	{
		return description ;
	}

}
