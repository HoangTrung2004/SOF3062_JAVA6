//package org.example.lab5.Bai4.controller;
//
//import org.example.lab5.Bai4.entity.Student;
//import org.example.lab5.Bai4.service.StudentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@CrossOrigin("*")
//@RestController
//public class StudentRestApi {
//    @Autowired
//    StudentService studentService;
//
//    @GetMapping("students") // => [student1, student2, ...]
//    public List<Student> findAll() {
//        return studentService.findAll();
//    }
//
//    @GetMapping("students/{id}") // => student
//    public Student findById(@PathVariable("id") String id) {
//        return studentService.findById(id);
//    }
//
//    @PostMapping("students") // => student
//    public Student create(@RequestBody Student student) {
//        return studentService.create(student);
//    }
//
//    @PutMapping("students/{id}") // => student
//    public Student update(@PathVariable("id") String id, @RequestBody Student student) {
//        student.setId(id);
//        return studentService.update(student);
//    }
//
//    @DeleteMapping("students/{id}") // => nothing
//    public void delete(@PathVariable("id") String id) {
//        studentService.deleteById(id);
//    }
//
//}
