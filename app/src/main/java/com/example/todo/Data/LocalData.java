package com.example.todo.Data;

import com.example.todo.Models.ToDo;

import java.util.ArrayList;
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
            add(new ToDo("Làm bài tập toán", "Làm bài tập toán trang 11"));
            add(new ToDo("Mua thực phẩm", "Đi mua thực phẩm tại siêu thị gần nhà"));
            add(new ToDo("Đi bơi", "Đi bơi tại hồ bơi công cộng"));
            add(new ToDo("Gọi điện thoại cho mẹ", "Gọi điện thoại về nhà cho mẹ"));
            add(new ToDo("Đi làm", "Đi làm tại công ty ABC"));
            add(new ToDo("Tập thể dục", "Tập thể dục mỗi buổi sáng"));
            add(new ToDo("Đọc sách", "Đọc sách mới"));
            add(new ToDo("Nấu ăn", "Nấu cơm trưa"));
            add(new ToDo("Viết email", "Viết email gửi cho đối tác"));
            add(new ToDo("Đi chợ", "Đi chợ mua đồ cho gia đình"));
            add(new ToDo("Xem phim", "Xem phim mới tại rạp"));
            add(new ToDo("Làm việc nhà", "Dọn dẹp nhà cửa"));
            add(new ToDo("Học tiếng Anh", "Học tiếng Anh online"));
            add(new ToDo("Thiết kế web", "Hoàn thành bài tập thiết kế web"));
            add(new ToDo("Đi du lịch", "Đi du lịch tại thành phố Hồ Chí Minh"));
            add(new ToDo("Chăm sóc thú cưng", "Đưa thú cưng đến bác sĩ thú y kiểm tra sức khỏe"));
            add(new ToDo("Viết blog", "Viết bài blog về cuộc sống"));
            add(new ToDo("Học đàn guitar", "Học đàn guitar"));
            add(new ToDo("Đi khám sức khỏe", "Đi khám sức khỏe định kỳ"));
            add(new ToDo("Mua quần áo", "Mua quần áo mới"));
        }
    };
    public List<ToDo> getTodos(){
        return todos;
    }
}
