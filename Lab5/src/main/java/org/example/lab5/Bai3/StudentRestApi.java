package org.example.lab5.Bai3;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@RestController
public class StudentRestApi {
    // GET(host/students.json) => {key1: student, key2: student, ...}
    @GetMapping("students.json")
    public Map<String, Student> findAll() {
        return Database.map;
    }

    // GET(host/students/{key}.json) => student
    @GetMapping("students/{key}.json")
    public Student findByKey(@PathVariable("key") String key) {
        return Database.map.get(key);
    }

    // POST(host/students.json, student) => {name: key}
    @PostMapping("students.json")
    public Map<String, String> create(@RequestBody Student student) {
        var key = Database.getKey();
        Database.map.put(key, student);
        return Map.of("name", key);
    }

    // PUT(host/students/{key}.json, student) => student
    @PutMapping("students/{key}.json")
    public Student update(@PathVariable("key") String key, @RequestBody Student student) {
        Database.map.put(key, student);
        return Database.map.get(key);
    }

    // DELETE(host/students/{key}.json)
    @DeleteMapping("students/{key}.json")
    public void delete(@PathVariable("key") String key) {
        Database.map.remove(key);
    }

}
