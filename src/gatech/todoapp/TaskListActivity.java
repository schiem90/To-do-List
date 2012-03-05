package gatech.todoapp;

import java.util.ArrayList;
import java.util.Date;

import gatech.todoapp.domain.Task;
import gatech.todoapp.domain.User;
import gatech.todoapp.util.DatabaseUtil;
import android.app.Activity;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ListView;

/**
 * Creates the TaskList view.
 *
 */
public class TaskListActivity extends Activity {
	
	private DatabaseUtil db;
    private User currentUser;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
	    setContentView(R.layout.tasklist);
	    db = new DatabaseUtil(this);
	    
	    //currentUser = db.getActiveSession();
	    
	  //Testing Tasks, Uncomment and run once to put 2 tasks in for user with ID 1
	    /*
	    SQLiteDatabase testDB = db.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("description", "Some Task");
		values.put("location", "My House");
		values.put("date", (new Date()).toString());
		values.put("comments", "This is a task for userID 1");
		values.put("categoryID", 1);
		values.put("userID", 1);
		testDB.insert("task", "location", values);
		
		values = new ContentValues();
		values.put("description", "More Stuff");
		values.put("location", "Yo House");
		values.put("date", (new Date()).toString());
		values.put("comments", "This is another task for userID 1");
		values.put("categoryID", 1);
		values.put("userID", 1);
		testDB.insert("task", "location", values);
		*/
		
	    //This is just 1 until we get a logout button so we can not have one
	    //user logged in forever
	    //We also won't have to do this once we have a add task screen because
	    //the currentUser(the one logged in) will already have all its tasks
	    ArrayList<Task> userTasks = (ArrayList<Task>) db.getTasksForUser(1);
        
        final ListView lv1 = (ListView) findViewById(R.id.taskList);
        lv1.setAdapter(new TaskAdapter(this, userTasks));
	    
	    /*
	    lv1.setOnItemClickListener(new OnItemClickListener() {
	     @Override
	     public void onItemClick(AdapterView<?> a, View v, int position, long id) { 
	      Object o = lv1.getItemAtPosition(position);
	      Task fullObject = (Task)o;
	      Toast.makeText(ListViewBlogPost.this, "You have chosen: " + " " + fullObject.getName(), Toast.LENGTH_LONG).show();
	     }  
	    }); */
    }
}