package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudent(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Collection<Student> findByAge(int age) {
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }

    public Faculty getStudentFaculty(Long studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student != null) {
            return student.getFaculty();
        }
        return null;
    }
    public long countAllStudents() {
        return studentRepository.countAllStudents();
    }

    public Double getAverageAge() {
        Double average = studentRepository.getAverageAge();
        // Если студентов нет — AVG вернёт null, возвращаем 0.0
        return average != null ? average : 0.0;
    }

    public List<Student> findLastFiveStudents() {
        return studentRepository.findLastFiveStudents();
    }
}