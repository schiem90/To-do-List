package gatech.todoapp;

import gatech.todoapp.domain.Category;
import gatech.todoapp.domain.Task;
import gatech.todoapp.domain.User;
import gatech.todoapp.util.DatabaseUtil;

import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * This class is used to launch and handle the create task screen.
 *
 */
public class CreateTaskActivity extends Activity {

	private DatabaseUtil db;
    private User currentUser;
    
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
	    setContentView(R.layout.createtask);
	    db = new DatabaseUtil(this);
	    currentUser = db.getActiveSession();
    
	    Button submitButton = (Button) findViewById(R.id.submitbutton);
	    Button cancelButton = (Button) findViewById(R.id.cancelbutton);
        final EditText sdescriptionTextBox  = (EditText) findViewById(R.id.sdescription);
        final EditText dueDateTextBox  = (EditText) findViewById(R.id.duedate);
        final EditText ddescriptionTextBox  = (EditText) findViewById(R.id.ddescription);
        final EditText locationTextBox  = (EditText) findViewById(R.id.location);
        final CheckBox repeatingDateCheckBox = (CheckBox) findViewById(R.id.checkBox1);
        final Spinner categorySpinner  = (Spinner) findViewById(R.id.category);
        
        submitButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		String sdescription = sdescriptionTextBox.getText().toString();
        		String dueDate = dueDateTextBox.getText().toString();
		    	String ddescription = ddescriptionTextBox.getText().toString();
		    	String location = locationTextBox.getText().toString();
		    	
		    	//convert string to date
		    	Date date = new Date();
		    	//add check for Spinner
		    	boolean repeatingDate = repeatingDateCheckBox.isChecked();
		    	
		    	
		    	AlertDialog alertDialog = new AlertDialog.Builder(CreateTaskActivity.this).create();
		    	alertDialog.setTitle("New Task");
		    	Task newTask = new Task(sdescription, date);
		    	newTask.setComments(ddescription);
		    	newTask.setLocation(location);
		    	newTask = db.saveTask(newTask, currentUser.getID());
		    	Log.v("New Task", "Created New Task: " + newTask.getDescription());
		    	Intent i = new Intent(CreateTaskActivity.this, TaskListActivity.class);
                startActivity(i);
        	}
        });
        
        
        cancelButton.setOnClickListener(new View.OnClickListener() {
        	/**
        	 * Takes the user back to tasklist screen when cancel button is clicked
        	 * @param v needed to view the model
        	 */
        	public void onClick(View v) {
        		Intent i = new Intent(CreateTaskActivity.this, TaskListActivity.class);
                startActivity(i);
        	}
        
    });
    
    }
}
