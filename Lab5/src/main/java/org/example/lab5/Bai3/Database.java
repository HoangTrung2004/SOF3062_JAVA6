package org.example.lab5.Bai3;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Database {
    // Chứa danh sách sinh viên
    public static Map<String, Student> map = new HashMap<>();

    // Bổ sung dữ liệu mẫu
    static {
        map.put(getKey(), new Student("SV01", "Lý Thái Tổ", 9.5, true));
        map.put(getKey(), new Student("SV02", "Lê Trọng Tấn", 4.5, true));
        map.put(getKey(), new Student("SV03", "Nguyễn Thị Minh Khai", 8.5, false));
        map.put(getKey(), new Student("SV04", "Đoàn Trung Trực", 6.0, true));
    }

    // Sinh key ngẫu nhiên
    public static String getKey() {
        return Integer.toHexString(UUID.randomUUID().toString().hashCode());
    }

}
