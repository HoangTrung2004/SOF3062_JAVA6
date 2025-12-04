package org.example.lab5.Bai3;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Student {
    private String id;
    private String name;
    private double mark;
    private boolean gender;
}
