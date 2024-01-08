package com.example.studentmanagement.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.studentmanagement.dto.StudentDTO;
import com.example.studentmanagement.model.Major;
import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.repository.StudentRepository;
import com.example.studentmanagement.tool.StudentConverter;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {StudentService.class})
@ExtendWith(SpringExtension.class)
class StudentServiceDiffblueTest {
    @MockBean
    private StudentConverter studentConverter;

    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    /**
     * Method under test: {@link StudentService#GetStudentList()}
     */
    @Test
    void testGetStudentList() {
        // Arrange
        when(studentRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<StudentDTO> actualGetStudentListResult = studentService.GetStudentList();

        // Assert
        verify(studentRepository).findAll();
        assertTrue(actualGetStudentListResult.isEmpty());
    }

    /**
     * Method under test: {@link StudentService#GetStudentList()}
     */
    @Test
    void testGetStudentList2() {
        // Arrange
        StudentDTO buildResult = StudentDTO.builder()
                .age(1)
                .email("jane.doe@example.org")
                .major(Major.SE)
                .name("Name")
                .build();
        when(studentConverter.toDTO(Mockito.<Student>any())).thenReturn(buildResult);

        Student student = new Student();
        student.setAge(1);
        student.setEmail("jane.doe@example.org");
        student.setId(1);
        student.setMajor(Major.SE);
        student.setName("Name");

        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(student);
        when(studentRepository.findAll()).thenReturn(studentList);

        // Act
        List<StudentDTO> actualGetStudentListResult = studentService.GetStudentList();

        // Assert
        verify(studentConverter).toDTO(Mockito.<Student>any());
        verify(studentRepository).findAll();
        assertEquals(1, actualGetStudentListResult.size());
    }

    /**
     * Method under test: {@link StudentService#GetStudentList()}
     */
    @Test
    void testGetStudentList3() {
        // Arrange
        StudentDTO buildResult = StudentDTO.builder()
                .age(1)
                .email("jane.doe@example.org")
                .major(Major.SE)
                .name("Name")
                .build();
        when(studentConverter.toDTO(Mockito.<Student>any())).thenReturn(buildResult);

        Student student = new Student();
        student.setAge(1);
        student.setEmail("jane.doe@example.org");
        student.setId(1);
        student.setMajor(Major.SE);
        student.setName("Name");

        Student student2 = new Student();
        student2.setAge(0);
        student2.setEmail("john.smith@example.org");
        student2.setId(2);
        student2.setMajor(Major.SS);
        student2.setName("com.example.studentmanagement.model.Student");

        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(student2);
        studentList.add(student);
        when(studentRepository.findAll()).thenReturn(studentList);

        // Act
        List<StudentDTO> actualGetStudentListResult = studentService.GetStudentList();

        // Assert
        verify(studentConverter, atLeast(1)).toDTO(Mockito.<Student>any());
        verify(studentRepository).findAll();
        assertEquals(2, actualGetStudentListResult.size());
    }

    /**
     * Method under test: {@link StudentService#CreateStudent(StudentDTO)}
     */
    @Test
    void testCreateStudent() {
        // Arrange
        Student student = new Student();
        student.setAge(1);
        student.setEmail("jane.doe@example.org");
        student.setId(1);
        student.setMajor(Major.SE);
        student.setName("Name");
        StudentDTO buildResult = StudentDTO.builder()
                .age(1)
                .email("jane.doe@example.org")
                .major(Major.SE)
                .name("Name")
                .build();
        when(studentConverter.toDTO(Mockito.<Student>any())).thenReturn(buildResult);
        when(studentConverter.toEntity(Mockito.<StudentDTO>any())).thenReturn(student);

        Student student2 = new Student();
        student2.setAge(1);
        student2.setEmail("jane.doe@example.org");
        student2.setId(1);
        student2.setMajor(Major.SE);
        student2.setName("Name");
        when(studentRepository.save(Mockito.<Student>any())).thenReturn(student2);

        // Act
        studentService.CreateStudent(new StudentDTO("Name", Major.SE, 1, "jane.doe@example.org"));

        // Assert
        verify(studentConverter).toDTO(Mockito.<Student>any());
        verify(studentConverter).toEntity(Mockito.<StudentDTO>any());
        verify(studentRepository).save(Mockito.<Student>any());
    }
}
