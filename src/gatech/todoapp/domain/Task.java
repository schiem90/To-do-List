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
	 * @param location The location of the task
	 * @param date The date of the task
	 * @param comments Additional comments
	 */
	public Task(String description, String location, Date date, String comments) {
		this.description = description;
		this.location = location;
		this.date = date;
		this.comments = comments;
		complete = false;
	}
	
	/**
	 * Creates a new empty task without an ID.
	 */
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
	/**
	 * changes the boolean value of complete to other
	 */
	public void changeComplete(){
		if (this.complete == false){
			this.complete = true;
		}
		else{
			this.complete = false;
		}
	}
	/**
	 * @return the boolean complete
	 */
	public boolean getComplete(){
		return this.complete; 
	}
	
	@Override
	public String toString()
	{
		return description ;
	}

}
