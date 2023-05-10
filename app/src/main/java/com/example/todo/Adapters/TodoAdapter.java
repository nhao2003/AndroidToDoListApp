package com.example.todo.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.example.todo.Data.LocalData;
import com.example.todo.Models.ToDo;
import com.example.todo.R;

import java.util.List;

public class TodoAdapter extends BaseAdapter {
    private final Context context;
    private List<ToDo> todoList;

    public TodoAdapter(Context context, List<ToDo> todoList) {
        this.context = context;
        this.todoList = todoList;
    }

    @Override
    public int getCount() {
        return todoList.size();
    }

    @Override
    public ToDo getItem(int position) {
        return todoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.todo_card, parent, false);
        }

        ToDo toDo = todoList.get(position);
        TextView title = view.findViewById(R.id.tv_title);
        TextView timeCreated = view.findViewById(R.id.tv_time);
        TextView description = view.findViewById(R.id.tv_description);
        CheckBox checkBox = view.findViewById(R.id.cb_done);;

        title.setText(toDo.getTitle());
        timeCreated.setText(toDo.getTimeCreate().toString());
        description.setText(toDo.getDescription());
        checkBox.setChecked(toDo.isDone());

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                LocalData.getInstance().getTodos().get(position).setDone(isChecked);
                for(ToDo val : LocalData.getInstance().getTodos()){
                    System.out.println(val.toString());
                }
            }
        });
        return view;
    }
}
