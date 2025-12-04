package org.example.lab5.Bai1;

import java.io.IOException;

public class RestClient {
    static String host = "https://demoproject-5a913-default-rtdb.firebaseio.com";

    private static void getAll() {
        var url = host + "/students.json";
        try {
            var connection = HttpClient.openConnection("GET", url);
            var response = HttpClient.readData(connection);
            System.out.println(new String(response));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void getByKey() {
        var url = host + "/students/<<key>>.json";
        try {
            var connection = HttpClient.openConnection("GET", url);
            var response = HttpClient.readData(connection);
            System.out.println(new String(response));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void post() {
        var url = host + "/students.json";
        var data = "{\"id\":\"SV09\",\"name\":\"Sinh viên 09\",\"mark\":5.5,\"gender\":true}".getBytes();
        try {
            var connection = HttpClient.openConnection("POST", url);
            var response = HttpClient.writeData(connection,data);
            System.out.println(new String(response));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    private static void put() {
        var url = host + "/students/<<key>>.json";
        var data = "{\"id\":\"SV09\",\"name\":\"Sinh viên 09\",\"mark\":5.5,\"gender\":true}".getBytes();
        try {
            var connection = HttpClient.openConnection("PUT", url);
            var response = HttpClient.writeData(connection,data);
            System.out.println(new String(response));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void delete() {
        var url = host + "/students/<<key>>.json";
        try {
            var connection = HttpClient.openConnection("DELETE", url);
            HttpClient.readData(connection);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
