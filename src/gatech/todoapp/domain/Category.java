package gatech.todoapp.domain;

/**
 * This class represents a category of a Task.
 *
 */
public class Category {

	private Integer ID;
	private String name;
	
	/**
	 * Constructor that creates a new category with a null ID.
	 */
	public Category() {
		this.ID = null;
	}
	
	@Override
	public boolean equals(Object other) {
	    if (other == null) 
	    	return false;
	    if (other == this) 
	    	return true;
	    if (!(other instanceof Category))
	    	return false;
	    Category otherCategory = (Category) other;
	    if (ID.equals(otherCategory.getID()))
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
