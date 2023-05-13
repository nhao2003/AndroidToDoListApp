package com.example.todo;

import android.content.Intent;
import android.net.Uri;
import android.view.*;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.todo.Adapters.TodoAdapter;
import com.example.todo.Data.LocalData;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    TodoAdapter todoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.lv_tTodo);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        todoAdapter = new TodoAdapter(this, LocalData.getInstance().getTodos(), activityResultLauncher);
        recyclerView.setAdapter(todoAdapter);
        registerForContextMenu(recyclerView);
        //
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // GetContent creates an ActivityResultLauncher<String> to let you pass
// in the mime type you want to let the user select
    private final ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult activityResult) {
                    int resultCode = activityResult.getResultCode();

                    if (resultCode == RESULT_OK) {
                        if (activityResult.getData() != null) {
                            int index = activityResult.getData().getIntExtra("itemPos", 0);
                            todoAdapter.notifyItemChanged(index);
                        } else {
                            todoAdapter.notifyItemInserted(LocalData.getInstance().getTodos().size() - 1);
                        }
                    }
                }
            }


    );

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this, "onOptionsItemSelected", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, AddTodoActivity.class);
        activityResultLauncher.launch(intent);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        return super.onContextItemSelected(item);
    }
}
