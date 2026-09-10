package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.*;

@Service
public class StudentService {

    private final Map<Long, Student> students = new HashMap<>();
    private Long currentId = 1L;

    public Long addStudent(Student student) {
        student.setId(currentId);
        students.put(currentId, student);
        currentId++;
        return student.getId();
    }

    public Student getStudent(Long id) {
        return students.get(id);
    }

    public Student editStudent(Student student) {
        if (students.containsKey(student.getId())) {
            students.put(student.getId(), student);
            return student;
        }
        return null;
    }

    public void deleteStudent(Long id) {
        students.remove(id);
    }

    public Collection<Student> getAllStudents() {
        return students.values();
    }

    public Collection<Student> findByAge(int age) {
        List<Student> result = new ArrayList<>();
        for (Student student : students.values()) {
            if (student.getAge() == age) {
                result.add(student);
            }
        }
        return result;
    }
}