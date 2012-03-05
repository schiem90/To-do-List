package gatech.todoapp.domain;

import java.util.List;

/**
 * This class represents a user of the application.
 */
public class User {

	private Integer ID;
	private String name;
	private String email;
	private String username;
	private String password;
	private List<Task> tasks;
	private List<Task> filteredTasks;
	
	/**
	 * Default constructor makes a new user with a null ID.
	 */
	public User() {
		this.ID = null;
	}
	
	/**
	 * Creates a new user with the given parameters.
	 * @param name The name of the user
	 * @param email The user's email address
	 * @param username The username to login with
	 * @param password The password
	 */
	public User(String name, String username, String email, String password) {
		this();
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
	}
	
	@Override
	public boolean equals(Object other) {
	    if (other == null) 
	    	return false;
	    if (other == this) 
	    	return true;
	    if (!(other instanceof User))
	    	return false;
	    User otherUser = (User) other;
	    if (ID.equals(otherUser.getID()))
	    	return true;
	    
	    return false;
	}
	
	@Override
	public String toString() {
		return "ID: " + ID + " Username: " + username + " Name: " + name;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return the tasks
	 */
	public List<Task> getTasks() {
		return tasks;
	}
	
	/**
	 * @param tasks the tasks to set
	 */
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	/**
	 * @return the filteredTasks
	 */
	public List<Task> getFilteredTasks() {
		return filteredTasks;
	}

	/**
	 * @param filteredTasks the filteredTasks to set
	 */
	public void setFilteredTasks(List<Task> filteredTasks) {
		this.filteredTasks = filteredTasks;
	}

}
