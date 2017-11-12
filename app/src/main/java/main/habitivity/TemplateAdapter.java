package main.habitivity;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;

import java.util.List;


public class TemplateAdapter extends BaseAdapter implements ListAdapter {
    // setup model container
    private List<?> list = null;
    private final Context context;

    public TemplateAdapter(List<?> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // Use xml layout resource here
            // view = inflater.inflate(R.layout.?, null);
        }

        // Handle TextView and display attributes from model class
        // Example:

        // TextView listItemName = (TextView) view.findViewById(R.id.counter_name);
        // listItemName.setText(list.get(position).getName());

        //Handle buttons and add onClickListeners
        // Example:

        /*
         Button deleteBtn = (Button) view.findViewById(R.id.delete_btn);
         Button editBtn = (Button) view.findViewById(R.id.edit_btn);

         deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                list.remove(position);
                ((activity_using_this_adapter)context).activity_method();
                notifyDataSetChanged();
            }
         });

        /*
        editBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, activity_using_this_adapter.class);
                String name = list.get(position).getName();

                intent.putExtra("key_value", name);
                startActivity(intent);
            }
        });
        */

        return view;
    }
}