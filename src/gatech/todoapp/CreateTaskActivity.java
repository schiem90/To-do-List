
package gatech.todoapp;

import java.sql.Date;
import java.util.ArrayList;

import gatech.todoapp.domain.Category;
import gatech.todoapp.domain.Task;
import gatech.todoapp.domain.User;
import gatech.todoapp.util.DatabaseUtil;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * This class is the activity for the create account view.
 *
 */
public class CreateTaskActivity extends Activity {
	    
	    DatabaseUtil db;
	    User currentUser;

		/**
		 * Called when the activity is first created.
		 */
		 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.createtask);
	        
	        db = new DatabaseUtil(CreateTaskActivity.this);
	        
	        //Run once and delete
	   /*     Category newCategory = new Category("School Stuff");
		    db.createCategory(newCategory, 1);
		    Category newCategory2 = new Category("Work");
		    db.createCategory(newCategory2, 1);*/
		    
	        ArrayList<Category> categories = db.getCategories(1);
	        
	        
	        Button taskSubmitButton = (Button) findViewById(R.id.taskSubmit);
	        Button taskCancelButton = (Button) findViewById(R.id.taskCancel);
	        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
	        ArrayAdapter<Category> adapter = new ArrayAdapter<Category>(CreateTaskActivity.this, android.R.layout.simple_spinner_item, categories);
	        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
	        spinner.setAdapter(adapter);
	        Log.i("AAA","spinner0");
	        final DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker1);
	        final EditText taskNameTextBox  = (EditText) findViewById(R.id.taskName);
	        final EditText taskLocationTextBox  = (EditText) findViewById(R.id.taskLocation);
	      //  final EditText dueDateTextBox  = (EditText) findViewById(R.id.dueDate);
	        final EditText taskDescTextBox  = (EditText) findViewById(R.id.taskDesc);
	        
	       
	        
	        
	        //Register procedure
	        taskSubmitButton.setOnClickListener(new View.OnClickListener() {
	        	/**
	        	 * Registers and creates a new user if credentials are valid
	        	 * @param v needed to view the model
	        	 */
	        	public void onClick(View v) {
	        		String taskName = taskNameTextBox.getText().toString();
	        		String taskLocation = taskLocationTextBox.getText().toString();
			    	Date dueDate = new Date(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
			    	String taskDesc = taskDescTextBox.getText().toString();
			
			    	
/*			    	boolean registered = true; 
			    	
			    	//must input something for all text boxes
			    	if ((username.equals("")) &&
			    			(name.equals("")) &&
			    				(password.equals("")) &&
			    					(repassword.equals("")) &&
			    						(email.equals(""))) {
			    		registered = false; 
			    	}
			    
			    	if (!password.equals(repassword)) {
			    		//passwords must match
			    		registered = false; 
			        }
			    	
			    
			    	//username must not already be taken in database
			    	for (User users : db.getAllUsers()) {
			    		if (username.equals(users.getUsername())) {
			    			registered = false; 
			    		}
			    	}*/
			    
			    	
			    
			    	
			    	AlertDialog alertDialog = new AlertDialog.Builder(CreateTaskActivity.this).create();
			    	alertDialog.setTitle("New Task");

			    	
			 
			    		alertDialog.setMessage("Task Added");
			    		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
				    	      public void onClick(DialogInterface dialog, int which) {
				    	    	  dialog.dismiss();
				    	    } });
				    	alertDialog.show();
			
			    		Task newTask = new Task(taskName, taskLocation, dueDate, taskDesc);
				    	newTask = db.saveTask(newTask, 1);
				    	Log.v("New Account", "Created New User: " + db.getActiveSession());
				    	Intent i = new Intent(CreateTaskActivity.this, TaskListActivity.class);
		                startActivity(i);
			    	
			    	
	        	}
	        });
	        
	        
	      //  Button cancelButton = (Button) findViewById(R.id.cancelRegisterButton);
	        
	      
	        taskCancelButton.setOnClickListener(new View.OnClickListener() {
	        	/**
	        	 * Takes the user back to login screen when cancel button is clicked
	        	 * @param v needed to view the model
	        	 */
	        	public void onClick(View v) {
	        		Intent i = new Intent(CreateTaskActivity.this, TaskListActivity.class);
	                startActivity(i);
	        	}
	        
	    });
	        
		}
}