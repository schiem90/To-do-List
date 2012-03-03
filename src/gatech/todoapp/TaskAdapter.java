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
import android.widget.ImageView;
import android.widget.TextView;


public class TaskAdapter extends BaseAdapter {

	private Activity activity;
    private List<Task> data;
    private static LayoutInflater inflater=null;
    
    public TaskAdapter(Activity a, List<Task> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.tasklist_item, null);

        TextView text=(TextView)vi.findViewById(R.id.text);
        text.setText("item "+position);
        return vi;
    }
}

