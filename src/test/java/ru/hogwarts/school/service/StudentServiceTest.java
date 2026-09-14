package ru.hogwarts.school.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private Student testStudent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testStudent = new Student(1L, "Harry Potter", 17);
    }

    @Test
    void testAddStudent() {
        when(studentRepository.save(testStudent)).thenReturn(testStudent);

        Student result = studentService.addStudent(testStudent);

        assertNotNull(result);
        assertEquals("Harry Potter", result.getName());
        assertEquals(17, result.getAge());
        verify(studentRepository, times(1)).save(testStudent);
    }

    @Test
    void testGetStudent() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(testStudent));

        Student result = studentService.getStudent(1L);

        assertNotNull(result);
        assertEquals("Harry Potter", result.getName());
        verify(studentRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteStudent() {
        studentService.deleteStudent(1L);
        verify(studentRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetStudentFaculty() {
        Faculty faculty = new Faculty(1L, "Gryffindor", "red");
        testStudent.setFaculty(faculty);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(testStudent));

        Faculty result = studentService.getStudentFaculty(1L);

        assertNotNull(result);
        assertEquals("Gryffindor", result.getName());
    }
}