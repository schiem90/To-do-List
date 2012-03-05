package gatech.todoapp;

import java.util.ArrayList;

import gatech.todoapp.domain.Task;
import gatech.todoapp.domain.User;
import gatech.todoapp.util.DatabaseUtil;
import android.app.Activity;
import android.content.SharedPreferences;
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
	    
	    SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
	    int userID = settings.getInt("userID", -1);
	    currentUser = db.getActiveSession();
	    
	    ArrayList<Task> allTasks = (ArrayList<Task>) db.getAllTasks();
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