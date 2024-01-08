package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.StudentDTO;
import com.example.studentmanagement.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    IStudentService studentService;

    @GetMapping("")
    public List<StudentDTO> GetStudents() {
        return studentService.GetStudentList();
    }

    @PostMapping("")
    public StudentDTO CreateStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.CreateStudent(studentDTO);
    }
}
