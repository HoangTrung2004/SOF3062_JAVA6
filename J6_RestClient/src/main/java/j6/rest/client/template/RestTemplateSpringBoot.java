package j6.rest.client.template;

import org.springframework.web.client.RestTemplate;

import j6.rest.dto.Student;
import j6.rest.dto.StudentList;

public class RestTemplateSpringBoot {
	public static void main(String[] args) {
		getAll();
		getById();
		post();
		put();
		delete();
	}
	
	static RestTemplate client = new RestTemplate();
	static String host = "http://localhost:8080";

	private static void getAll() {
		var url = host + "/students.json";
		var list = client.getForObject(url, StudentList.class);
		System.out.println(list.toString());
	}

	private static void getById() {
		var url = host + "/students/SV01";
		var student = client.getForObject(url, Student.class);
	}

	private static void post() {
		var data = new Student("SV01", "Sinh viên 01", true, 8.5);
		var url = host + "/students";
		var student = client.postForObject(url, data, Student.class);
		System.out.println(student.toString());
	}

	private static void put() {
		var data = new Student("SV006", "Sinh viên 01", false, 5.5);
		var url = host + "/students/" + data.getId();
		client.put(url, data);
	}

	private static void delete() {
		var url = host + "/students/SV01";
		client.delete(url);
	}
}