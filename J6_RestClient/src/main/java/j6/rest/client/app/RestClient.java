package j6.rest.client.app;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestClient {
	public static String get(String url) throws IOException {
		var connection = RestClient.openConnection("GET", url);
		var response = RestClient.readData(connection);
		return new String(response);
	}
	
	public static String post(String url, String data) throws IOException {
		var connection = RestClient.openConnection("POST", url);
		var response = RestClient.writeData(connection, data.getBytes());
		return new String(response);
	}
	
	public static String put(String url, String data) throws IOException {
		var connection = RestClient.openConnection("PUT", url);
		var response = RestClient.writeData(connection, data.getBytes());
		return new String(response);
	}
	
	public static void delete(String url) throws IOException {
		var connection = RestClient.openConnection("DELETE", url);
		RestClient.readData(connection);
	}
	
	/**
	 * Mở kết nối
	 * 
	 * @param method là web method (GET, POST, PUT hay DELETE)
	 * @param url địa chỉ URL của REST API
	 */
	private static HttpURLConnection openConnection(String method, String url) throws IOException {
		var connection = (HttpURLConnection) new URL(url).openConnection();
		connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
		connection.setRequestMethod(method);
		return connection;
	}

	/**
	 * Đọc dữ liệu phản hồi từ server và đóng kết nối
	 */
	private static byte[] readData(HttpURLConnection connection) throws IOException {
		if (connection.getResponseCode() == 200) {
			var out = new ByteArrayOutputStream();
			var is = connection.getInputStream();
			var block = new byte[4 * 1024];
			while (true) {
				int n = is.read(block);
				if (n <= 0) {
					break;
				}
				out.write(block, 0, n);
			}
			connection.disconnect();
			return out.toByteArray();
		}
		connection.disconnect();
		throw new IOException("No response from server!");
	}

	/**
	 * Gửi dữ liệu lên server và đọc dữ liệu phản hồi từ server
	 */
	private static byte[] writeData(HttpURLConnection connection, byte[] data) throws IOException {
		connection.setDoOutput(true);
		connection.getOutputStream().write(data);
		return readData(connection);
	}
}
