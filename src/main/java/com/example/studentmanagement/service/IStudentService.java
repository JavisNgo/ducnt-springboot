package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.StudentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IStudentService {

    public List<StudentDTO> GetStudentList();

    public StudentDTO CreateStudent(StudentDTO studentDTO);
}
