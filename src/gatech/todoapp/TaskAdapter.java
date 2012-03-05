package gatech.todoapp;

import java.util.ArrayList;
import java.util.List;

import gatech.todoapp.domain.Task;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Adapter class for the tasklist.
 */
public class TaskAdapter extends BaseAdapter {

	 private static ArrayList<Task> taskList;
	 
	 private LayoutInflater mInflater;

	 public TaskAdapter(Context context, ArrayList<Task> results) {
	  taskList = results;
	  mInflater = LayoutInflater.from(context);
	 }

	 public int getCount() {
	  return taskList.size();
	 }

	 public Object getItem(int position) {
	  return taskList.get(position);
	 }

	 public long getItemId(int position) {
	  return position;
	 }

	 public View getView(int position, View convertView, ViewGroup parent) {
	  ViewHolder holder;
	  if (convertView == null) {
	   convertView = mInflater.inflate(R.layout.tasklist_row, null);
	   holder = new ViewHolder();
	   holder.txtName = (TextView) convertView.findViewById(R.id.name);
	   holder.txtCityState = (TextView) convertView.findViewById(R.id.cityState);
	   holder.txtPhone = (TextView) convertView.findViewById(R.id.phone);

	   convertView.setTag(holder);
	  } else {
	   holder = (ViewHolder) convertView.getTag();
	  }
	  
	  holder.txtName.setText(taskList.get(position).getDescription());
	  holder.txtCityState.setText(taskList.get(position).getLocation());
	  holder.txtPhone.setText(taskList.get(position).getDate().toLocaleString());

	  return convertView;
	 }

	 static class ViewHolder {
	  TextView txtName;
	  TextView txtCityState;
	  TextView txtPhone;
	 }
	}

