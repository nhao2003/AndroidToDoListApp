package com.example.todo.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.*;
import android.widget.*;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.todo.AddTodoActivity;
import com.example.todo.Data.LocalData;
import com.example.todo.Models.ToDo;
import com.example.todo.R;
import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {
    private final Context context;
    private List<ToDo> todoList;
    ActivityResultLauncher<Intent> resultLauncher;

    public TodoAdapter(Context context, List<ToDo> todoList, ActivityResultLauncher<Intent> resultLauncher) {
        this.context = context;
        this.todoList = todoList;
        this.resultLauncher = resultLauncher;
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }


    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TodoAdapter.ViewHolder holder, int position) {
        ToDo toDo = todoList.get(position);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        holder.title.setText(toDo.getTitle());
        holder.timeCreated.setText(dateFormat.format(toDo.getExpiryDate()));
        holder.description.setText(toDo.getDescription());
        holder.checkBox.setChecked(toDo.isDone());

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                todoList.get(holder.getAdapterPosition()).setDone(isChecked);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, PopupMenu.OnMenuItemClickListener {
        // Khai báo các thành phần giao diện của một mục trong danh sách
        TextView title, timeCreated, description;
        CheckBox checkBox;

        public int getItemPosition() {
            return getAdapterPosition();
        }

        public ViewHolder(View view) {
            super(view);
            // Ánh xạ các thành phần giao diện
            title = view.findViewById(R.id.tv_title);
            timeCreated = view.findViewById(R.id.tv_time);
            description = view.findViewById(R.id.tv_description);
            checkBox = view.findViewById(R.id.cb_done);
            view.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View view) {
            PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
            popupMenu.inflate(R.menu.context_menu);
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.show();
            return false;
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            Toast.makeText(itemView.getContext(), "Hi: " + getItemPosition(), Toast.LENGTH_SHORT).show();
            switch (item.getItemId()) {
                case R.id.edit_todo:
                    Intent intent = new Intent(context, AddTodoActivity.class);
                    intent.putExtra("itemPos", getItemPosition());
                    resultLauncher.launch(intent);
                    return true;
                case R.id.delete_todo:
                    LocalData.getInstance().getTodos().remove(getItemPosition());
                    notifyItemRemoved(getItemPosition());
                    return true;
                case R.id.mark_as_done_todo:
                    LocalData.getInstance().getTodos().get(getItemPosition()).setDone(true);
                    notifyItemChanged(getItemPosition());
                    return true;
                default:
                    return false;
            }
        }
    }
}
