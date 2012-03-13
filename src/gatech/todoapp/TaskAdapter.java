package gatech.todoapp;

import java.util.ArrayList;

import gatech.todoapp.domain.Task;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Adapter class for the tasklist that allows us to build the tasklist given a user's tasks.
 */
public class TaskAdapter extends BaseAdapter {

	 private static ArrayList<Task> taskList;
	 
	 private LayoutInflater mInflater;

	 /**
	  * TaskAdapter Constructor. Takes in the context and the user's tasks.
	  * @param context - the context the adapter is operating in
	  * @param tasks - the user's tasks
	  */
	 public TaskAdapter(Context context, ArrayList<Task> tasks) {
	  taskList = tasks;
	  mInflater = LayoutInflater.from(context);
	 }

	 /**
	  * Gets the number of tasks in the list.
	  * @return count
	  */
	 public int getCount() {
	  return taskList.size();
	 }

	 /**
	  * Gets the item at the specified position.
	  * @param position - item's position
	  * @return the item at that position
	  */
	 public Object getItem(int position) {
	  return taskList.get(position);
	 }

	 /**
	  * Gets the item id.
	  * @param position - item's position
	  * @return the item's position
	  */
	 public long getItemId(int position) {
	  return position;
	 }

	 /**
	  * Gets the view for the tasklist.
	  * @param position - the item's position
	  * @param convertView - the view to convert to
	  * @param parent - the group of views that this view belongs to
	  * @return view
	  */
	 public View getView(int position, View convertView, ViewGroup parent) {
	  ViewHolder holder;
	  if (convertView == null) {
	   convertView = mInflater.inflate(R.layout.tasklist_row, null);
	   holder = new ViewHolder();
	   holder.txtDescription = (TextView) convertView.findViewById(R.id.descriptionText);
	   holder.txtLocation = (TextView) convertView.findViewById(R.id.locationText);
	   holder.txtDate = (TextView) convertView.findViewById(R.id.dateText);

	   convertView.setTag(holder);
	  } else {
	   holder = (ViewHolder) convertView.getTag();
	  }
	  
	  holder.txtDescription.setText(taskList.get(position).getDescription());
	  holder.txtLocation.setText(taskList.get(position).getLocation());
	  holder.txtDate.setText(taskList.get(position).getDate().toLocaleString());

	  return convertView;
	 }

	 /**
	  * Holds the views we need for other classes.
	  *
	  */
	 static class ViewHolder {
	  TextView txtDescription;
	  TextView txtLocation;
	  TextView txtDate;
	 }
	}

