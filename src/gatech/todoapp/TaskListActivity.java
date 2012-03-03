package gatech.todoapp;

import gatech.todoapp.domain.Task;
import gatech.todoapp.domain.User;
import gatech.todoapp.util.DatabaseUtil;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Creates the TaskList view.
 *
 */
public class TaskListActivity extends ListActivity {
	
	DatabaseUtil db;
    User currentUser;
    ListView list;
    TaskAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);

	  db = new DatabaseUtil(TaskListActivity.this);
	  
	  setListAdapter(new ArrayAdapter<Task>(this, R.layout.tasklist_item));

	  setContentView(R.layout.main);
      
      list=(ListView)findViewById(R.id.list);
      adapter=new TaskAdapter(this,db.getTasksForUser(currentUser.getID()));
      list.setAdapter(adapter);
      
	  };
	}
