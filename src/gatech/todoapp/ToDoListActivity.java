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

public class ToDoListActivity extends Activity {
	
	DatabaseUtil db;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        //trying to make a button work.
        Button createAccount=(Button) findViewById(R.id.register);
        createAccount.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){
        		// TODO go to create account screen
        	   Intent i = new Intent(ToDoListActivity.this, CreateAccountActivity.class);
               startActivity(i);
        	
        	}
            });
        Button Login=(Button) findViewById(R.id.login);
        Login.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO attempt to login (used Trey's test code)
				//TREY'S TEST CODE FOR TESTING DB STUFF
		    	db= new DatabaseUtil(ToDoListActivity.this);
		    	//user the user you registered above to test
		    	User currentUser = db.loginUser("Trey", "12"); // returns null b/c trey not in db
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
    	
    	//TREY'S TEST CODE FOR TESTING DB STUFF
    	db = new DatabaseUtil(this);
    	//This next line will reset the database
    	//db.onUpgrade(db.getWritableDatabase(), 1, 2);
    	//register a user here (it's all local so you have to register users on your machine)
    	//User newUser = new User("Trey Moore", "Trey", "trey@email.com", "123");
    	//db.registerUser(newUser);
    	
    	//user the user you registered above to test
    	User currentUser = db.loginUser("Trey", "12");
    	
    	//Dialog box to see what info DB is getting
    	AlertDialog alertDialog = new AlertDialog.Builder(this).create();
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
    	//END TREY'S TEST CODE
    	
	}

    
    
    
}
