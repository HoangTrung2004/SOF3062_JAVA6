package org.example.lab5.Bai4.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity(name = "J6Students")
public class Student {
    @Id
    String id;
    String name;
    double mark;
    boolean gender;
}
