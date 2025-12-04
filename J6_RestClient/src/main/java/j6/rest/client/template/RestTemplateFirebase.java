package j6.rest.client.template;

import java.util.Map;

import org.springframework.web.client.RestTemplate;

import j6.rest.dto.Student;
import j6.rest.dto.StudentMap;

public class RestTemplateFirebase {
	public static void main(String[] args) {
		getAll();
		getById();
		post();
		put();
		delete();
	}
	
	static RestTemplate client = new RestTemplate();
	static String host = "https://fpolyedu.firebaseio.com";

	private static void getAll() {
		var url = host + "/students.json";
		var map = client.getForObject(url, StudentMap.class);
		System.out.println(map.toString());
	}

	private static void getById() {
		var url = host + "/students/-ORe_zNPXY0yhvSM9_uh.json";
		var student = client.getForObject(url, Student.class);
	}

	private static void post() {
		var data = new Student("SV007", "Sinh viên 007", true, 8.5);
		var url = host + "/students.json";
		var map = client.postForObject(url, data, Map.class);
		System.out.println(map.toString());
	}

	private static void put() {
		var entity = new Student("SV006", "Sinh viên 006", false, 5.5);
		var url = host + "/students/-ORe_zNPXY0yhvSM9_uh.json";
		client.put(url, entity);
	}

	private static void delete() {
		var url = host + "/students/-ORe_zNPXY0yhvSM9_uh.json";
		client.delete(url);
	}
}