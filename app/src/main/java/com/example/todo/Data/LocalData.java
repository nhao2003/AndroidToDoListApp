package com.example.todo.Data;

import com.example.todo.Models.ToDo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class LocalData {
    private static LocalData instance = null;
    public static synchronized LocalData getInstance() {
        if(instance == null)
            instance = new LocalData();
        return instance;
    }
    private List<ToDo> todos = new ArrayList<ToDo>(){
        {
            add(new ToDo("Làm bài tập toán", "Làm bài tập toán trang 11", new Date()));
            add(new ToDo("Mua thực phẩm", "Đi mua thực phẩm tại siêu thị gần nhà", new Date()));
            add(new ToDo("Đi bơi", "Đi bơi tại hồ bơi công cộng", new Date()));
            add(new ToDo("Gọi điện thoại cho mẹ", "Gọi điện thoại về nhà cho mẹ", new Date()));
        }
    };
    public List<ToDo> getTodos(){
        return todos;
    }
}
