package com.example.studentmanagement.tool;

import com.example.studentmanagement.dto.StudentDTO;
import com.example.studentmanagement.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentConverter {
    public Student toEntity(StudentDTO studentDTO) {
        return Student.builder()
                .name(studentDTO.getName())
                .major(studentDTO.getMajor())
                .age(studentDTO.getAge())
                .email(studentDTO.getEmail())
                .build();
    }

    public StudentDTO toDTO(Student student) {
        return StudentDTO.builder()
                .name(student.getName())
                .major(student.getMajor())
                .age(student.getAge())
                .email(student.getEmail())
                .build();
    }

}
