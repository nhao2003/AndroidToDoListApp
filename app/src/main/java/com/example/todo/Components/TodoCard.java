package com.example.todo.Components;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import com.example.todo.Models.ToDo;
import com.example.todo.R;
import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;

public class TodoCard extends CardView {
    private final TextView title;
    private final TextView expiryDate;
    private final TextView description;
    private final CheckBox checkBox;

    public TodoCard(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray attributes;
        attributes = context.obtainStyledAttributes(attrs, R.styleable.TodoCardAttrs);
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.todo_card, this, true);

        title = this.findViewById(R.id.tv_title);
        expiryDate = this.findViewById(R.id.tv_time);
        description = this.findViewById(R.id.tv_description);
        checkBox = this.findViewById(R.id.cb_done);

        String title = attributes.getString(R.styleable.TodoCardAttrs_title);
        String timeCreate = attributes.getString(R.styleable.TodoCardAttrs_timeCreated);
        String description = attributes.getString(R.styleable.TodoCardAttrs_description);
        boolean isDone = attributes.getBoolean(R.styleable.TodoCardAttrs_isDone, false);

        if (title != null && !title.isEmpty()) {
            this.title.setText(title);
        }
        if (timeCreate != null && !timeCreate.isEmpty()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            this.expiryDate.setText(dateFormat.format(timeCreate));
        }
        if (description != null && !description.isEmpty()) {
            this.description.setText(description);
        }
        checkBox.setChecked(isDone);
    }

    public void setTodo(ToDo toDo){
        this.title.setText(toDo.getTitle());
        this.expiryDate.setText(toDo.getExpiryDate().toString());
        this.description.setText(toDo.getDescription());
        checkBox.setChecked(toDo.isDone());
    }
}
