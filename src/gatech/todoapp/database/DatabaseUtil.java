package gatech.todoapp.database;

import java.util.Random;

import gatech.todoapp.domain.Category;
import gatech.todoapp.domain.User;

public class DatabaseUtil {
	
	//USER MANAGEMENT
	public static User registerUser(User user) {
		Random rand = new Random();
		//get ID from databse
		user.setID(rand.nextInt());
		
		return user;
	}
	
	//TASK MANAGEMENT
	
	//CATEGORY MANAGEMENT
	public static Category createCategory(Category category) {
		Category newCategory = new Category("School Stuff");
		return newCategory;
	}
	
	//SESSION MANAGEMENT
	public static User getLastSession() {
		User user = new User("Trey", "trey@email.com", "trey", "123");
		
		return user;
	}
	
}
