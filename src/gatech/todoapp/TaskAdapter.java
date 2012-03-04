package gatech.todoapp;

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

	private Activity activity;
    private List<Task> data;
    private static LayoutInflater inflater = null;
    
    /**
     * TaskAdapter constructor.
     * @param a activity 
     * @param d data
     */
    public TaskAdapter(Activity a, List<Task> d) {
        activity = a;
        data = d;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    
    /**
     * Gets the count of tasks.
     * @return count of tasks
     */
    public int getCount() {
        return data.size();
    }

    /**
     * Gets the item at the position specified.
     * @param position 
     * @return the item at that position
     * 
     */
    public Object getItem(int position) {
        return position;
    }

    /**
     * Gets the id of the item at the specified position.
     * @param position - the position of the item
     * @return the id of the item
     */
    public long getItemId(int position) {
        return position;
    }
    
    /**
     * Gets the view for the list.
     * 
     * @param position - position to start at
     * @param convertView - whether to convert the view to anything special
     * @param parent - the parent of the view
     * @return vi - the view for the list
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        
        if (convertView == null)
            vi = inflater.inflate(R.layout.tasklist_item, null);

        TextView text = (TextView) vi.findViewById(R.id.text);
        text.setText("item " + position);
        return vi;
    }
}

