package gatech.todoapp;

import java.util.ArrayList;

import gatech.todoapp.domain.User;
import gatech.todoapp.util.DatabaseUtil;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Activity for the main login view.
 */
public class ToDoListActivity extends Activity {
	
	DatabaseUtil db;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        db = new DatabaseUtil(ToDoListActivity.this);
        
        if (db.getActiveSession() != null) {
	        Intent intent = new Intent(ToDoListActivity.this, TaskListActivity.class);
	        startActivity(intent);
        }
        
        
        //Register button
        Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		/**
            	 * Takes the user to the register screen when button is clicked
            	 * @param v needed to view the model
            	 */
        	   Intent i = new Intent(ToDoListActivity.this, CreateAccountActivity.class);
               startActivity(i);
        	
        	}
            });
        
        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
        	/**
        	 * Logs the user in when button is clicked if credentials match with those found in database
        	 * @param v needed to view the model
        	 */
			public void onClick(View v) {
		    	
		    	EditText usernameTextBox = (EditText) findViewById(R.id.usernameTextBox);
		    	EditText passwordTextBox = (EditText) findViewById(R.id.passwordTextBox);
		    	
		    	//user the user you registered above to test
		    	User currentUser = db.loginUser(usernameTextBox.getText().toString(), 
		    			passwordTextBox.getText().toString());
		    	
		    	//Dialog box to see what info DB is getting, for testing only
		    	AlertDialog alertDialog = new AlertDialog.Builder(ToDoListActivity.this).create();
		    	alertDialog.setTitle("Logged In As");

		    	//login failed
		    	if (currentUser == null) {
		    		alertDialog.setMessage("Login Failed");
			    	  alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			    	      public void onClick(DialogInterface dialog, int which) {
			    	    	  dialog.dismiss();
			    	    } });
			    	alertDialog.show();
		    	} else {
		    		Log.v("Todo List Session", "Logged in as: " + db.getActiveSession());
		    		
		    		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		    		SharedPreferences.Editor editor = settings.edit();
		    		editor.putInt("userID", currentUser.getID());
		    		
		    		//Remove once we have a logout button
		    		//db.logoutUser();
		    		Intent i = new Intent(ToDoListActivity.this, TaskListActivity.class);
		               startActivity(i);
		    	}		
			}
		});
     }
    
    @Override
    /**
	 * called when activity becomes visible to user
	 */
	public void onStart()
	{
    	super.onStart();
    	   	
    	//This next line will reset the database, comment it out!!!!
    	//db.onUpgrade(db.getWritableDatabase(), 2, 3);
    	
	}
}
