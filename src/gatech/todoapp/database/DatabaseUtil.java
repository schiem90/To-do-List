package gatech.todoapp.database;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import gatech.todoapp.domain.Category;
import gatech.todoapp.domain.Task;
import gatech.todoapp.domain.User;

public class DatabaseUtil extends SQLiteOpenHelper {
	 
	public static final String DATABASE_NAME = "todoListDB";
		
		public DatabaseUtil(Context context) {
			super(context, DATABASE_NAME, null, 1);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			
			//Create user table
			String userSQL = "CREATE TABLE IF NOT EXISTS user (" +
							"id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
							"name TEXT, " +
							"username TEXT, " +
							"email TEXT, " +
							"password TEXT)";
			db.execSQL(userSQL);
			
			String taskSQL = "CREATE TABLE IF NOT EXISTS task (" +
					"id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
					"description TEXT, " +
					"location TEXT, " +
					"date TEXT, " +
					"comments TEXT, " +
					"categoryID TEXT, " +
					"userID TEXT)";
			db.execSQL(taskSQL);
			
			
			String categorySQL = "CREATE TABLE IF NOT EXISTS category (" +
				"id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
				"name TEXT, " +
				"userID TEXT)";
			db.execSQL(categorySQL);
			
			String sessionSQL = "CREATE TABLE IF NOT EXISTS currentSession (" +
					"userID TEXT)";
			db.execSQL(sessionSQL);
			
			ContentValues values = new ContentValues();

			//Test Data
			/*
			values.put("name", "Trey Moore");
			values.put("username", "Trey");
			values.put("email", "trey@email.com");
			values.put("password", "123");
			db.insert("user", "name", values);
			*/
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS user");
			db.execSQL("DROP TABLE IF EXISTS task");
			db.execSQL("DROP TABLE IF EXISTS caegory");
			db.execSQL("DROP TABLE IF EXISTS userID");
			onCreate(db);
		}
	
	//USER MANAGEMENT
		public List<User> getAllUsers() {
			
			SQLiteDatabase db=this.getReadableDatabase();
			List<User> users = new ArrayList<User>();
			
			Cursor cursor = db.rawQuery("SELECT * FROM user", null);
			
			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				User user = populateUserFromCursor(cursor);
				users.add(user);
				cursor.moveToNext();
			}
			// Make sure to close the cursor
			cursor.close();
			return users;
		}
		
	public User registerUser(User user) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put("name", user.getName());
		values.put("username", user.getUsername());
		values.put("email", user.getEmail());
		values.put("password", user.getPassword());
		db.insert("user", "name", values);
		db.close();
		
		return user;
	}
	
	//TASK MANAGEMENT
	/**
	 * Gets all tasks for a user with the specific ID
	 * @param userID The user to get tasks for
	 * @return A list of Tasks for the user
	 */
	public List<Task> getTasksForUser(Integer userID) {
		if (userID == null) {
			//ERROR
		}
		
		SQLiteDatabase db=this.getReadableDatabase();
		List<Task> tasks = new ArrayList<Task>();
		
		String[] params=new String[]{String.valueOf(userID)};
		Cursor cursor = db.rawQuery("SELECT * FROM task WHERE userID=?", params);
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Task task = cursorToTask(cursor);
			tasks.add(task);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return tasks;
	}

	/**
	 * Populates a task from a cursor
	 * @param cursor The cursor containing the Task's data
	 * @return The task the cursor contains
	 */
	private Task cursorToTask(Cursor cursor) {
		Task task = new Task();
		task.setID(cursor.getInt(0));
		task.setDescription(cursor.getString(1));
		task.setLocation(cursor.getString(2));
		task.setDate(new Date(Date.parse(cursor.getString(3))));		
		task.setComments(cursor.getString(4));
		//task.setCategory(populateCategory(cursor.getString(5)));
		return task;
	}
	
	//CATEGORY MANAGEMENT
	public Category createCategory(Category category, Integer userID) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put("name", category.getName());
		values.put("userID", userID);
		db.insert("category", "name", values);
		db.close();
		
		return category;
	}
	
	//SESSION MANAGEMENT
	public User getLastSession() {
		User user = new User("Trey", "trey@email.com", "trey", "123");
		
		return user;
	}
	
	public void setActiveSession() {
		
	}
	
	/**
	 * Attempts to log in a user, returning user if successful or null if failed
	 * @param username The username entered for login
	 * @param password The password entered for login
	 * @return A logged in user or null
	 */
	public User loginUser(String username, String password) {
		SQLiteDatabase db = this.getReadableDatabase();
		//check login
		String[] params=new String[]{username, password};
		Cursor cursor = db.rawQuery("SELECT * FROM user WHERE username=? AND password=?", params);
		
		//get user
		//If there are any results in the cursor (valid login)
		if (cursor.moveToFirst()) {
			User currentUser = populateUserFromCursor(cursor);
			//set current session
			//setActiveSession(currentUser)
			return currentUser;
		} else {
			return null;
		}
		
	}
	
	private User populateUserFromCursor(Cursor cursor) {
		User user = new User();
		user.setID(cursor.getInt(0));
		user.setName(cursor.getString(1));
		user.setUsername(cursor.getString(2));
		user.setEmail(cursor.getString(3));
		user.setPassword(cursor.getString(4));
		
		return user;
	}
	
}