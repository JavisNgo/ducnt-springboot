package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Major;
import com.example.studentmanagement.model.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void StudentRepository_Save_ReturnSavedStudent() {
        //Arrange
        Student student = Student.builder()
                .name("Jackson")
                .major(Major.SE)
                .build();
        //Act
        Student savedStudent = studentRepository.save(student);
        //Assert
        Assertions.assertThat(savedStudent).isNotNull();
        Assertions.assertThat(savedStudent.getId()).isGreaterThan(0);
    }

    @Test
    public void StudentRepository_GetAll_ReturnMoreThanOneStudent()
    {
        Student student1 = Student.builder()
                .name("Jackson")
                .major(Major.SE)
                .build();
        Student student2 = Student.builder()
                .name("Michael")
                .major(Major.SS)
                .build();

        studentRepository.save(student1);
        studentRepository.save(student2);

        List<Student> studentList = studentRepository.findAll();

        Assertions.assertThat(studentList).isNotNull();
        Assertions.assertThat(studentList.size()).isEqualTo(2);
    }

    @Test
    public void StudentRepository_FindByID_ReturnStudent() {
        Student student = Student.builder()
                .name("Jackson")
                .major(Major.SA)
                .build();

        studentRepository.save(student);
        Student savedStudent = studentRepository.findById(student.getId()).get();

        Assertions.assertThat(savedStudent).isNotNull();
    }

    @Test
    public void StudentRepository_RemoveByID_ReturnStudentListEqualZero() {
        Student student = Student.builder()
                .name("Hella")
                .major(Major.SS)
                .build();
        studentRepository.save(student);

        studentRepository.deleteById(student.getId());

        List<Student> studentList = studentRepository.findAll();

        Assertions.assertThat(studentList.size()).isEqualTo(0);
    }
}
