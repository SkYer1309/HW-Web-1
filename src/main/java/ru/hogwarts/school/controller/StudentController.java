package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;
import java.util.List;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Long createStudent(@RequestBody Student student) {
        return studentService.addStudent(student).getId();
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    @GetMapping
    public Collection<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PutMapping
    public Student updateStudent(@RequestBody Student student) {
        return studentService.editStudent(student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/filter/age")
    public Collection<Student> getStudentsByAge(@RequestParam int age) {
        return studentService.findByAge(age);
    }

    // Новый эндпоинт: фильтрация по диапазону возрастов
    @GetMapping("/filter/ageBetween")
    public Collection<Student> getStudentsByAgeBetween(
            @RequestParam int min, @RequestParam int max) {
        return studentService.findByAgeBetween(min, max);
    }

    // Новый эндпоинт: получить факультет студента
    @GetMapping("/{id}/faculty")
    public Faculty getStudentFaculty(@PathVariable Long id) {
        return studentService.getStudentFaculty(id);
    }


    // Количество всех студентов
    @GetMapping("/count")
    public long countAllStudents() {
        return studentService.countAllStudents();
    }

    // Средний возраст студентов
    @GetMapping("/averageAge")
    public Double getAverageAge() {
        return studentService.getAverageAge();
    }

    // Пять последних студентов
    @GetMapping("/lastFive")
    public List<Student> getLastFiveStudents() {
        return studentService.findLastFiveStudents();
    }
}