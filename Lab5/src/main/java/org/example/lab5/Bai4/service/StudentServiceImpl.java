package org.example.lab5.Bai4.service;

import org.example.lab5.Bai4.dao.StudentDAO;
import org.example.lab5.Bai4.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentDAO dao;
    @Override
    public List<Student> findAll() {
        return dao.findAll();
    }
    @Override
    public Student findById(String id) {
        return dao.findById(id).orElse(null);
    }
    @Override
    public Student create(Student student) {
        return dao.save(student);
    }
    @Override
    public Student update(Student student) {
        return dao.save(student);
    }
    @Override
    public void deleteById(String id) {
        dao.deleteById(id);
    }
}
