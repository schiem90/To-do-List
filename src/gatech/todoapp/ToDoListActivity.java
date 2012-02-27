package gatech.todoapp;

import java.util.List;

import gatech.todoapp.database.DatabaseUtil;
import gatech.todoapp.domain.User;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ToDoListActivity extends Activity {
	
	DatabaseUtil db;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        //Register button
        Button registerButton=(Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){
        		// TODO go to create account screen
        	   Intent i = new Intent(ToDoListActivity.this, CreateAccountActivity.class);
               startActivity(i);
        	
        	}
            });
        Button loginButton = (Button)findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO attempt to login (used Trey's test code)
				//TREY'S TEST CODE FOR TESTING DB STUFF
		    	db = new DatabaseUtil(ToDoListActivity.this);
		    	
		    	EditText usernameTextBox = (EditText)findViewById(R.id.usernameTextBox);
		    	EditText passwordTextBox = (EditText)findViewById(R.id.passwordTextBox);
		    	
		    	//user the user you registered above to test
		    	User currentUser = db.loginUser(usernameTextBox.getText().toString(), 
		    			passwordTextBox.getText().toString()); // returns null b/c trey not in db
		    	//Dialog box to see what info DB is getting
		    	AlertDialog alertDialog = new AlertDialog.Builder(ToDoListActivity.this).create();
		    	alertDialog.setTitle("Logged In As");

		    	//login failed
		    	if (currentUser == null) {
		    		alertDialog.setMessage("Login Failed");
		    	} else {
		    		alertDialog.setMessage("User ID: " + currentUser.getID() + "\n Name: " + currentUser.getName());
		    	}    	

		    	  alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
		    	      public void onClick(DialogInterface dialog, int which) {
		    	    	  dialog.dismiss();
		    	    } });
		    	alertDialog.show();
				
			}
		});
     }
        	
        
    
    
    @Override
	public void onStart()
	{
    	super.onStart();
    	
    	db = new DatabaseUtil(ToDoListActivity.this);
    	
    	//TREY'S TEST CODE FOR TESTING DB STUFF    	
    	//This next line will reset the database
    	//db.onUpgrade(db.getWritableDatabase(), 2, 3);
    	//register a user here (it's all local so you have to register users on your machine)
    	//User newUser = new User("Trey Moore", "Trey", "trey@email.com", "123");
    	//db.registerUser(newUser);
    	//END TREY'S TEST CODE
    	
	}

    
    
    
}
