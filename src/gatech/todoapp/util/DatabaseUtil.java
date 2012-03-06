package gatech.todoapp.util;

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

/**
 * This class is a utility for creating and using
 * with the database.
 *
 */
public class DatabaseUtil extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "todoListDB";

   /**
    * Constructor calls the superclass's constructor
    * with the current context.
    * @param context The context
    */
    public DatabaseUtil(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

		@Override
		public void onCreate(SQLiteDatabase db) {
			
			//Create database tables
			String userSQL = "CREATE TABLE IF NOT EXISTS user (" +
					"id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
					"name TEXT UNIQUE, " +
					"username TEXT NOT NULL, " +
					"email TEXT NOT NULL, " +
					"password TEXT NOT NULL, " +
					"activeSession INTEGER NOT NULL)";
			db.execSQL(userSQL);
			
			String taskSQL = "CREATE TABLE IF NOT EXISTS task (" +
					"id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
					"description TEXT NOT NULL, " +
					"location TEXT, " +
					"date TEXT, " +
					"comments TEXT, " +
					"categoryID TEXT, " +
					"userID TEXT NOT NULL)";
			db.execSQL(taskSQL);
			
			
			String categorySQL = "CREATE TABLE IF NOT EXISTS category (" +
					"id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
					"name TEXT, " +
					"userID TEXT)";
			db.execSQL(categorySQL);
			
			String sessionSQL = "CREATE TABLE IF NOT EXISTS currentSession (" +
					"userID TEXT)";
			db.execSQL(sessionSQL);

			//Test Data
			/*
			ContentValues values = new ContentValues();
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
			db.execSQL("DROP TABLE IF EXISTS category");
			db.execSQL("DROP TABLE IF EXISTS userID");
			onCreate(db);
		}
	
		//USER MANAGEMENT
		
		//THIS IS JUST A TEST METHOD, DELETE AFTER TESTING
		/**
		 * Gets a list of all users in the database.
		 * @return A list of users in the database
		 */
		public List<User> getAllUsers() {
			
			SQLiteDatabase db = this.getReadableDatabase();
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
		
		/**
		 * Saves a new user into the database.
		 * @param user The new user to save
		 * @return The newly registered user
		 */
		public User registerUser(User user) {
			SQLiteDatabase db = this.getWritableDatabase();
	
			ContentValues values = new ContentValues();
			values.put("name", user.getName());
			values.put("username", user.getUsername());
			values.put("email", user.getEmail());
			values.put("password", user.getPassword());
			values.put("activeSession", 0);
			db.insert("user", "name", values);			
			db.close();
			
			user = populateUserByUsername(user.getUsername());
			
			//Get the ID from the database
			
			return user;
		}
		
		/**
		 * Populates a user's information.
		 * @param username The username of the user to populate
		 * @return The populated user
		 */
		private User populateUserByUsername(String username) {
			User user = new User();
			SQLiteDatabase db = this.getReadableDatabase();
			String[] params = new String[]{username};
			Cursor cursor = db.rawQuery("SELECT * FROM user WHERE username=?", params);
			cursor.moveToFirst();
			user = populateUserFromCursor(cursor);
			cursor.close();		
			
			
			db.close();
			return user;
		}
		
		/**
		 * Populates a user from a cursor.
		 * @param cursor The cursor result from a query
		 * @return The populated user
		 */
		private User populateUserFromCursor(Cursor cursor) {
			User user = new User();
			user.setID(cursor.getInt(0));
			user.setName(cursor.getString(1));
			user.setUsername(cursor.getString(2));
			user.setEmail(cursor.getString(3));
			user.setPassword(cursor.getString(4));
			user.setTasks(getTasksForUser(user.getID()));
			
			return user;
		}
		
		//TASK MANAGEMENT
		/**
		 * Saves a task to the database for the specified user.
		 * @param task The task to save
		 * @param userID The user the task belongs to
		 * @return The saved task
		 */
		public Task saveTask(Task task, Integer userID) {
			SQLiteDatabase db = this.getWritableDatabase();
			
			ContentValues values = new ContentValues();
			values.put("description", task.getDescription());
			values.put("location", task.getLocation());
			values.put("date", task.getDate().toString());
			values.put("comments", task.getComments());
			if (task.getCategory() != null)
				values.put("categoryID", task.getCategory().getID());
			else
				values.put("categoryID", -1);
			values.put("userID", userID);
			db.insert("task", "location", values);
			db.close();
			
			//Get task ID
			
			return task;
		}
		
		/**
		 * Deletes a task from the database.
		 * @param taskID The ID of the task to delete
		 * @param userID The user the task belongs to
		 */
		public void deleteTask(Integer taskID, Integer userID) {
			SQLiteDatabase db = this.getWritableDatabase();
			String[] params = new String[]{taskID.toString(), String.valueOf(userID)};
			db.delete("task", "id=? AND userID=?", params);
		}
		
		/**
		 * Gets all tasks for a user with the specific ID.
		 * @param userID The user to get tasks for
		 * @return A list of Tasks for the user
		 */
		public List<Task> getTasksForUser(Integer userID) {
			if (userID == null) {
				//ERROR
			}
			
			SQLiteDatabase db = this.getReadableDatabase();
			List<Task> tasks = new ArrayList<Task>();
			
			String[] params = new String[]{String.valueOf(userID)};
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
		 * Populates a task from a cursor.
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
			task.setCategory(new Category());
			//task.setCategory(populateCategory(cursor.getString(5)));
			return task;
		}
		
		//CATEGORY MANAGEMENT
		/**
		 * Creates a category for the specific user.
		 * @param category The category to create
		 * @param userID The ID of the user creating the category
		 * @return The new category 
		 */
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
		/**
		 * Gets the User that is the currently active session.
		 * @return The user that is currently logged in
		 */
		public User getActiveSession() {
			//activeSession
			SQLiteDatabase db = this.getReadableDatabase();
			String[] params = new String[]{((Integer) 1).toString()};  //1 is boolean true in SQLite
			Cursor cursor = db.rawQuery("SELECT * FROM user WHERE activeSession=?", params);
			
			//If there are any results in the cursor (valid login)
			if (cursor.moveToFirst()) {
				User currentUser = populateUserFromCursor(cursor);
				//set current session
				setActiveSession(currentUser.getID());
				return currentUser;
			} else {
				return null;
			}
		}
		
		/**
		 * Attempts to log in a user, returning user if successful or null if failed.
		 * @param username The username entered for login
		 * @param password The password entered for login
		 * @return A logged in user or null
		 */
		public User loginUser(String username, String password) {
			SQLiteDatabase db = this.getReadableDatabase();
			//check login
			String[] params = new String[]{username, password};
			Cursor cursor = db.rawQuery("SELECT * FROM user WHERE username=? AND password=?", params);
			
			//get user
			//If there are any results in the cursor (valid login)
			if (cursor.moveToFirst()) {
				User currentUser = populateUserFromCursor(cursor);
				//set current session
				setActiveSession(currentUser.getID());
				return currentUser;
			} else {
				return null;
			}			
		}
		
		/**
		 * Logs out the currently logged in user.
		 */
		public void logoutUser() {
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put("activeSession", 0);  //0 is Boolean false
			String[] params = new String[]{((Integer) 1).toString()};
			db.update("user", values, "activeSession=?", params);
			db.close();
		}
		
		/**
		 * Sets the given user as the current active session.
		 * @param userID The Id of the user that is currently logged in
		 */
		private void setActiveSession(Integer userID) {
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put("activeSession", 1);  //1 is Boolean true
			String[] params = new String[]{userID.toString()};
			db.update("user", values, "id=?", params);
			db.close();
		}
}
