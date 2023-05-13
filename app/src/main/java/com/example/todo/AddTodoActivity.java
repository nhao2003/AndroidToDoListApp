package com.example.todo;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.todo.Data.LocalData;
import com.example.todo.Models.ToDo;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AddTodoActivity extends AppCompatActivity {
    private static final int ADD_TODO_REQUEST_CODE = 1;
    TextInputLayout titleInputLayout, descriptionInputLayout, expiryDateInputLayout;
    private EditText titleEditText, descriptionEditText, expiryDateEditText;
    private CheckBox checkBox;
    private Button addButton;
    private ToDo todo;

    private boolean isValidInput() {
        boolean isValidDescription = true;
        boolean isValidDate = true;
        if (descriptionEditText.getText().toString().trim().equals("")) {
            descriptionInputLayout.setHelperText("Vui lòng nhập mô tả!");
            descriptionInputLayout.setHelperTextEnabled(true);
            isValidDescription = false;
        }
        if (expiryDateEditText.getText().toString().trim().equals("")) {
            expiryDateInputLayout.setHelperText("Vui lòng nhập ngày kết thúc!");
            expiryDateInputLayout.setHelperTextEnabled(true);
            isValidDate = false;
        } else {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date date = format.parse(expiryDateEditText.getText().toString());
                System.out.println(date);
            } catch (ParseException e) {
                expiryDateInputLayout.setHelperText("Ngày kết thúc không hợp lệ!");
                expiryDateInputLayout.setHelperTextEnabled(true);
                isValidDate = false;
            }
        }
        return isValidDate && isValidDescription;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Intent intent = new Intent();
            intent.putExtra("title", titleEditText.getText().toString());
            intent.putExtra("description", descriptionEditText.getText().toString());
            setResult(RESULT_CANCELED);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_to_do);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        titleEditText = findViewById(R.id.title_edittext);
        titleInputLayout = findViewById(R.id.title_input_layout);
        descriptionEditText = findViewById(R.id.description_edittext);
        descriptionInputLayout = findViewById(R.id.description_input_layout);
        expiryDateEditText = findViewById(R.id.timeCreated_edittext);
        expiryDateInputLayout = findViewById(R.id.timeCreated_input_layout);
        checkBox = findViewById(R.id.checkBox);
        addButton = findViewById(R.id.add_button);

        int data = getIntent().getIntExtra("itemPos", -1);

        if (data != -1) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            todo = LocalData.getInstance().getTodos().get(data);
            titleEditText.setText(todo.getTitle());
            descriptionEditText.setText(todo.getDescription());
            expiryDateEditText.setText(dateFormat.format(todo.getExpiryDate()));
            checkBox.setChecked(todo.isDone());
            addButton.setText("Cập nhật");
        }
        descriptionEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                descriptionInputLayout.setHelperTextEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        expiryDateEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                expiryDateInputLayout.setHelperTextEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidInput()) {
                    Toast.makeText(getApplicationContext(), "Hợp lệ", Toast.LENGTH_SHORT).show();
                    String title = titleEditText.getText().toString();
                    String description = descriptionEditText.getText().toString();
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    Date date;
                    try {
                        date = format.parse(expiryDateEditText.getText().toString());
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    if(todo == null){
                        LocalData.getInstance().getTodos().add(new ToDo(title, description, date, checkBox.isChecked()));
                        setResult(RESULT_OK);
                    } else {
                        int index = LocalData.getInstance().getTodos().lastIndexOf(todo);
                        Intent intent = new Intent();
                        intent.putExtra("itemPos", index);
                        todo.setTitle(title);
                        todo.setDescription(description);
                        todo.setExpiryDate(date);
                        todo.setDone(checkBox.isChecked());
                        setResult(RESULT_OK,intent);

                    }
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Không hợp lệ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void showDatePickerDialog(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (datePicker, year, month, day) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            TextInputEditText dateEditText = findViewById(R.id.timeCreated_edittext);
            dateEditText.setText(sdf.format(calendar.getTime()));
        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}
