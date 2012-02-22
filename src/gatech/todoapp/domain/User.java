package gatech.todoapp.domain;

import java.util.ArrayList;

/**
 * This class represents a user of the application.
 */
public class User {
	
	private Integer ID;
	private String name;
	private String email;
	private String username;
	private String password;
	private ArrayList<Task> tasks;
	
	public User() {
		this.ID = null;
	}
	/**
	 * Creates a new user with the given parameters
	 * @param name The name of the user
	 * @param email The user's email address
	 * @param username The username to login with
	 * @param password The password
	 */
	public User(String name, String email, String username, String password) {
		this();
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
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
	public ArrayList<Task> getTasks() {
		return tasks;
	}
	
	/**
	 * @param tasks the tasks to set
	 */
	public void setTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}

}
