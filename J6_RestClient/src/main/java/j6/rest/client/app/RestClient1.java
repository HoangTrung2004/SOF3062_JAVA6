package j6.rest.client.app;

import java.io.IOException;

public class RestClient1 {
	static String host = "https://fpolyedu.firebaseio.com";

	private static void getAll() throws IOException {
		var url = host + "/students.json";
		var json = RestClient.get(url);
		System.out.println(json);
	}

	private static void getByKey() throws IOException {
		var url = host + "/students/<<key>>.json";
		var json = RestClient.get(url);
		System.out.println(json);
	}

	private static void post() throws IOException {
		var data = """
			{
				"id": "SV01",
				"name": "Sinh viên 01",
				"mark": 4.5,
				"gender": true
			}				
		""";
		var url = host + "/students.json";
		var json = RestClient.post(url, data);
		System.out.println(json);
	}

	private static void put() throws IOException {
		var data = """
				{
					"id": "SV01",
					"name": "Sinh viên 01",
					"mark": 4.5,
					"gender": true
				}
			""";
			var url = host + "/students/<<key>>.json";
			var json = RestClient.put(url, data);
			System.out.println(json);
	}

	private static void delete() throws IOException {
		var url = host + "/students/<<key>>.json";
		RestClient.delete(url);
	}
}