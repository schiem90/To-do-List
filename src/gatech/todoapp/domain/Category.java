package gatech.todoapp.domain;

/**
 * This class represents a category of a Task.
 *
 */
public class Category {

	private Integer ID;
	private String name;
	
	public Category() {
		this.ID = null;
	}
	/**
	 * Creates a new category with a given name.
	 * @param name
	 */
	public Category(String name) {
		this();
		this.name = name;
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
	
}
