package gatech.todoapp;

import com.android.demo.notepad1.NotesDbAdapter;
import com.android.demo.notepad1.R;

import gatech.todoapp.domain.Task;
import gatech.todoapp.domain.User;
import gatech.todoapp.util.DatabaseUtil;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Creates the TaskList view.
 *
 */
public class TaskListActivity extends ListActivity {
	
	private DatabaseUtil db;
    private User currentUser;
    private int taskNumber = 1;
	
    public static final int INSERT_ID1 = Menu.FIRST;
    public static final int INSERT_ID2 = Menu.FLAG_ALWAYS_PERFORM_CLOSE;
	
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasklist_item);
        db = new DatabaseUtil(this);
        fillData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	boolean result = super.onCreateOptionsMenu(menu);
        menu.add(0, INSERT_ID1, 0, R.string.menu_add);
        menu.add(0, INSERT_ID2, 0, R.string.menu_delete);
        return result;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
        
    	case INSERT_ID1:
            createTask();
            return true;
        
    	case INSERT_ID2:
    		deleteTask();
    		
    	}
    	
        return super.onOptionsItemSelected(item);
    }
    
    private void createTask() {
        String taskName = "Task " + taskNumber++;
        Task t = new Task(taskName, );
        db.saveTask(t, currentUser.getID());
        fillData();
    }
    
//    TODO
      private void deleteTask() {
//    	
//        db.deleteTask(t, currentUser.getID());
      }
    
    private void fillData() {
        // Get all of the notes from the database and create the item list
        Cursor c = mDbHelper.fetchAllNotes();
        startManagingCursor(c);

        String[] from = new String[] { NotesDbAdapter.KEY_TITLE };
        int[] to = new int[] { R.id.text1 };
        
        // Now create an array adapter and set it to display using our row
        SimpleCursorAdapter notes =
            new SimpleCursorAdapter(this, R.layout.notes_row, c, from, to);
        setListAdapter(notes);
    }
}