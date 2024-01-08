package com.example.studentmanagement.dto;

import com.example.studentmanagement.model.Major;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDTO {
    private String name;
    private Major major;
    private int age;
    private String email;
}
