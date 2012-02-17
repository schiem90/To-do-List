package gatech.todoapp.domain;

/**
 * This class represents a category of a Task.
 *
 */
public class Category {

	private Integer ID;
	private String name;
	
	
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
	
}
