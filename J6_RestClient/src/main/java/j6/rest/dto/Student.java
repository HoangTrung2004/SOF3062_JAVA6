package j6.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder

@Data
/**
 * Mô tả cấu trúc dữ liệu JSON
 */
public class Student {
	String id;
	String name;
	boolean gender;
	double mark;
}