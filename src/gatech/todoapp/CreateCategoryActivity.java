package gatech.todoapp;

import gatech.todoapp.domain.Category;
import gatech.todoapp.domain.Task;
import gatech.todoapp.domain.User;
import gatech.todoapp.util.DatabaseUtil;

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

public class CreateCategoryActivity extends Activity {
	
	private DatabaseUtil db;
    private User currentUser;
    
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
	    setContentView(R.layout.createtask);
	    db = new DatabaseUtil(this);
	    currentUser = db.getActiveSession();
    
	    final Button createButton = (Button) findViewById(R.id.createButton);
	    final Button cancelButton = (Button) findViewById(R.id.cancelbutton);
	    final EditText catNameField = (EditText) findViewById(R.id.categoryNameField);
        
	    createButton.setOnClickListener(new View.OnClickListener() {
        	/**
        	 * Creates the new item once the user enters info and hits submit
        	 * @param v needed to view the model
        	 */
        	public void onClick(View v) {
        		String catName = catNameField.getText().toString();	    	
		    	
		    	Category newCategory = new Category(catName);
		    	newCategory = db.createCategory(newCategory, currentUser.getID());
		    	Log.v("New Category", "Created New Category: " + newCategory.getName());
		    	Intent i = new Intent(CreateCategoryActivity.this, TaskListActivity.class);
                startActivity(i);
        	}
        });
        
        
        cancelButton.setOnClickListener(new View.OnClickListener() {
        	/**
        	 * Takes the user back to tasklist screen when cancel button is clicked
        	 * @param v needed to view the model
        	 */
        	public void onClick(View v) {
        		Intent i = new Intent(CreateCategoryActivity.this, TaskListActivity.class);
                startActivity(i);
        	}
        
    });
    
    }
}
