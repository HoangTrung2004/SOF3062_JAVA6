package org.example.lab5.Bai4.dao;

import org.example.lab5.Bai4.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDAO extends JpaRepository<Student,String> {
}
