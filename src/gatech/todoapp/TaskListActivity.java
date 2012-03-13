package gatech.todoapp;

import java.util.ArrayList;

import gatech.todoapp.domain.Task;
import gatech.todoapp.domain.User;
import gatech.todoapp.util.DatabaseUtil;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Creates the TaskList view.
 *
 */
public class TaskListActivity extends Activity {
	
	private DatabaseUtil db;
    private User currentUser;
    private ArrayList<Task> userTasks;
    
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
	    setContentView(R.layout.tasklist);
	    db = new DatabaseUtil(this);
	    
	    currentUser = db.getActiveSession();
	    if (currentUser == null) {
	    	Intent iLogout = new Intent(TaskListActivity.this, ToDoListActivity.class);
            startActivity(iLogout);
	    }
	    
	    TextView titleText = (TextView) findViewById(R.id.titleText);
	    titleText.setText(currentUser.getName() + "'s ToDo List");
	    
	    buildList();
    }
    
    /**
     * This method builds and rebuilds the list if necessary. Gets the users tasks and populates the list.
     */
    public void buildList() {
    	userTasks = currentUser.getTasks();
    	final ListView lv1 = (ListView) findViewById(R.id.taskList);
    	lv1.setAdapter(new TaskAdapter(this, userTasks));
    	registerForContextMenu(lv1);
    	
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo)
    {
    	super.onCreateContextMenu(menu, v, menuInfo);  
    	if (v.getId() == R.id.taskList)
    	{
    		 AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
    		 menu.setHeaderTitle(userTasks.get(info.position).toString());
    		 menu.add(Menu.NONE, 0, 0, "Edit");
    		 menu.add(Menu.NONE, 0, 0, "Delete");
    		 //menu.add();
    	}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.layout.tasklist_menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.createtask:
            	
            	Intent i1 = new Intent(TaskListActivity.this, CreateTaskActivity.class);
	            startActivity(i1);
                return true;
            case R.id.createcategory:
            	Intent i2 = new Intent(TaskListActivity.this, CreateCategoryActivity.class);
	            startActivity(i2);
                return true;
            case R.id.filterlist:
                
                return true;
            case R.id.getdirections:
                
                return true;
            case R.id.deletetask:
    
            	return true;
            case R.id.logout:
            	db.logoutUser();
            	currentUser = null;
            	Intent iLogout = new Intent(TaskListActivity.this, ToDoListActivity.class);
	            startActivity(iLogout);
            	return true;
    
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
