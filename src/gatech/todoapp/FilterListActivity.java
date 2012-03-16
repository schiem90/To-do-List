package gatech.todoapp;

import gatech.todoapp.domain.Category;
import gatech.todoapp.domain.Task;
import gatech.todoapp.domain.User;
import gatech.todoapp.util.DatabaseUtil;

import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

public class FilterListActivity extends Activity implements AdapterView.OnItemSelectedListener{
	
	DatabaseUtil db;
    User currentUser;
    ArrayList<Category> categories;
    ArrayList<String> status;
    
    public FilterListActivity(){
    	 db =   new DatabaseUtil(FilterListActivity.this);
    	 currentUser = db.getActiveSession();
    	 categories = db.getCategories(currentUser.getID());
    	 status = new ArrayList<String>();
    	 status.add("Completed");
    	 status.add("Incompleted");
    }
  
   


	 @Override
	 public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createtask);
        
       
        
        
        Button filterSubmitButton = (Button) findViewById(R.id.submitFilterButton);
        Button filterCancelButton = (Button) findViewById(R.id.cancelFilterButton);
        
        final Spinner categorySpinner = (Spinner) findViewById(R.id.spinnerCategoryFilter);
        categorySpinner.setOnItemSelectedListener(this);
        ArrayAdapter<Category> adapter1 = new ArrayAdapter<Category>(FilterListActivity.this, android.R.layout.simple_spinner_item, categories);
        adapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        categorySpinner.setAdapter(adapter1);
        Log.i("AAA", "categorySpinner");
       // categorySpinner.setOnItemSelectedListener(new MyOnItemSelectedListener1());
        

        
        final Spinner completeSpinner = (Spinner) findViewById(R.id.spinnerCompleteFilter);
        completeSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(FilterListActivity.this, android.R.layout.simple_spinner_item, status);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        completeSpinner.setAdapter(adapter2);
        Log.i("AAA", "completeSpinner");
        
        
        final DatePicker datePicker = (DatePicker) findViewById(R.id.datePickerFilter);
        
        final CheckBox dateCheckBox = (CheckBox) findViewById(R.id.checkBoxDateFilter);
        final CheckBox categoryCheckBox = (CheckBox) findViewById(R.id.checkBoxCategoryFilter);
        final CheckBox completeCheckBox = (CheckBox) findViewById(R.id.checkBoxCompleteFilter);
     
        
       
        
        
        //Register procedure
        filterSubmitButton.setOnClickListener(new View.OnClickListener() {
        	/**
        	 * Registers and creates a new user if credentials are valid
        	 * @param v needed to view the model
        	 */
        	public void onClick(View v) {
        	
        		DatePicker.OnDateChangedListener datePickerListener = new DatePicker.OnDateChangedListener() {
        			
					
				   private int year;
				   private int month;
					private int day;

					public void onDateChanged(DatePicker view, int year, int monthOfYear,
							int dayOfMonth) {
						// TODO Auto-generated method stub
						this.year = year; 
						this.month = monthOfYear; 
						this.day = dayOfMonth; 
						
						datePicker.init(year, month, day, null);
						
					}
					
				};
		
			

			
				
				
		    
				Date dueDate = new Date(datePicker.getYear() - 1900, datePicker.getMonth(), datePicker.getDayOfMonth());
				dueDate = new Date(dueDate.getTime());
		    		    
		    	
		    	
		    	
		
		            Log.v("Filtered List", "FilteredList");
			    	Intent i = new Intent(FilterListActivity.this, TaskListActivity.class);
	                startActivity(i);
		    	
        	}
        	
        });
        
        
      //  Button cancelButton = (Button) findViewById(R.id.cancelRegisterButton);
        
      
        filterCancelButton.setOnClickListener(new View.OnClickListener() {
        	/**
        	 * Takes the user back to login screen when cancel button is clicked
        	 * @param v needed to view the model
        	 */
        	public void onClick(View v) {
        		Intent i = new Intent(FilterListActivity.this, TaskListActivity.class);
                startActivity(i);
        	}
        
    });
        


        
	 


class YourItemSelectedListener implements OnItemSelectedListener {

		    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		        String selected = parent.getItemAtPosition(pos).toString();
		    }

		    public void onNothingSelected(AdapterView parent) {
		        // Do nothing.
		    }
		}
	}




	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
	}


public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
}
