/**
 * 
 */
package gatech.todoapp;

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

/**
 * This class is the activity for the create account view.
 *
 */
public class CreateAccountActivity extends Activity {

    DatabaseUtil db;

	@Override
	 public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createaccount);
        
        db = new DatabaseUtil(CreateAccountActivity.this);
        
        Button registerButton = (Button) findViewById(R.id.registerButton);
        final EditText usernameTextBox  = (EditText) findViewById(R.id.username);
        final EditText nameTextBox  = (EditText) findViewById(R.id.name);
        final EditText passwordTextBox  = (EditText) findViewById(R.id.password);
        final EditText repasswordTextBox  = (EditText) findViewById(R.id.repassword);
        final EditText emailTextBox  = (EditText) findViewById(R.id.email);
        
        
        //Register procedure
        registerButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		String username = usernameTextBox.getText().toString();
        		String name = nameTextBox.getText().toString();
		    	String password = passwordTextBox.getText().toString();
		    	String repassword = repasswordTextBox.getText().toString();
		    	String email = emailTextBox.getText().toString();
		    	
		    	boolean registered = true; 
		    	
		    	//checking to see if user inputs something for all text boxes
		    	if((username.equals("")) &&
		    			(name.equals("")) &&
		    				(password.equals("")) &&
		    					(repassword.equals("")) &&
		    						(email.equals(""))){
		    		registered = false; 
		    	}
		    	
		    	if (!password.equals(repassword)) {
		    		//passwords must match
		    		registered = false; 
		        }
		    	
		    
		    	
		    
		    	
		    	AlertDialog alertDialog = new AlertDialog.Builder(CreateAccountActivity.this).create();
		    	alertDialog.setTitle("New User");

		    	//login failed
		    	if (registered == false) {
		    		alertDialog.setMessage("Registration Failed");
		    	} else {
		    		User newUser = new User(name, username, email, password);
			    	newUser = db.registerUser(newUser);
		    		alertDialog.setMessage("User ID: " + newUser.getID() + "\n Name: " + newUser.getName());
		    	}    	

		    	  alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
		    	      public void onClick(DialogInterface dialog, int which) {
		    	    	  dialog.dismiss();
		    	    } });
		    	alertDialog.show();
        	}
        });
        
        //TODO Add cancel button to take user back to login screen
        Button cancelButton = (Button) findViewById(R.id.cancelRegisterButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		Intent i = new Intent(CreateAccountActivity.this, ToDoListActivity.class);
                startActivity(i);
        	}
        
    });
        
	}
}
