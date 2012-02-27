/**
 * 
 */
package gatech.todoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class CreateAccountActivity extends Activity {
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.createaccount);
	        Button Register=(Button) findViewById(R.id.register);
	        final EditText usernameTextBox  = (EditText)findViewById(R.id.username);
	        final EditText nameTextBox  = (EditText)findViewById(R.id.name);
	        final EditText passwordTextBox  = (EditText)findViewById(R.id.password);
	        final EditText repasswordTextBox  = (EditText)findViewById(R.id.repassword);
	        final EditText emailTextBox  = (EditText)findViewById(R.id.email);
	        
	        
	        //Register procedure
	        Register.setOnClickListener(new View.OnClickListener(){
	        	public void onClick(View v){
	        		// TODO go to create account scre	en
	        		String username = usernameTextBox.getText().toString();
	        		String name= nameTextBox.getText().toString();
			    	String password= passwordTextBox.getText().toString();
			    	String repassword= repasswordTextBox.getText().toString();
			    	String email=emailTextBox.getText().toString();
	        	
	        	}
	            });
	    }
}
