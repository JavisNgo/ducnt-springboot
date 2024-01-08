package com.example.studentmanagement.service.impl;

import com.example.studentmanagement.dto.StudentDTO;
import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.repository.StudentRepository;
import com.example.studentmanagement.service.IStudentService;
import com.example.studentmanagement.tool.StudentConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements IStudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentConverter studentConverter;

    @Override
    public List<StudentDTO> GetStudentList() {
        List<Student> studentList = studentRepository.findAll();
        List<StudentDTO> studentDTOList = studentList.stream()
                .map(student -> studentConverter.toDTO(student))
                .toList();
        return studentDTOList;
    }

    @Override
    public StudentDTO CreateStudent(StudentDTO studentDTO) {
        Student student = studentConverter.toEntity(studentDTO);
        Student savedStudent = studentRepository.save(student);
        return studentConverter.toDTO(savedStudent);
    }
}
