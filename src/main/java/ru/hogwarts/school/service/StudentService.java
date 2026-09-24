package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import java.util.stream.Collectors;

import java.util.Collection;
import java.util.List;

@Service
public class StudentService {

    // 1. Объявляем логгер
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        logger.info("Was invoked method for create student");
        logger.debug("Adding student with name: {}", student.getName());
        return studentRepository.save(student);
    }

    public Student getStudent(Long id) {
        logger.info("Was invoked method for get student by id");
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            logger.error("There is not student with id = " + id); // Критерий: logger.error перед throw/null
        }
        return student;
    }

    public Student editStudent(Student student) {
        logger.info("Was invoked method for edit student");
        if (!studentRepository.existsById(student.getId())) {
            logger.warn("Attempt to edit non-existent student with id = " + student.getId());
        }
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        logger.info("Was invoked method for delete student");
        if (!studentRepository.existsById(id)) {
            logger.warn("Attempt to delete non-existent student with id = " + id);
        }
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudents() {
        logger.info("Was invoked method for get all students");
        return studentRepository.findAll();
    }

    public Collection<Student> findByAge(int age) {
        logger.info("Was invoked method for find students by age");
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        logger.info("Was invoked method for find students by age between");
        return studentRepository.findByAgeBetween(min, max);
    }

    public Faculty getStudentFaculty(Long studentId) {
        logger.info("Was invoked method for get student faculty");
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student == null) {
            logger.error("There is not student with id = " + studentId);
            return null;
        }
        return student.getFaculty();
    }

    public long countAllStudents() {
        logger.info("Was invoked method for count all students");
        return studentRepository.countAllStudents();
    }


    public List<Student> findLastFiveStudents() {
        logger.info("Was invoked method for find last five students");
        return studentRepository.findLastFiveStudents();
    }
    public List<String> getNamesStartingWithA() {
        return studentRepository.findAll().stream()
                .map(Student::getName)                    // Получаем только имена
                .map(String::toUpperCase)                 // Переводим в верхний регистр
                .filter(name -> name.startsWith("А"))     // Фильтруем по букве "А"
                .sorted()                                 // Сортируем по алфавиту
                .collect(Collectors.toList());            // Собираем в список
    }
    public double getAverageAge() {
        return studentRepository.findAll().stream()
                .mapToInt(Student::getAge)                // Преобразуем в IntStream
                .average()                                // Вычисляем среднее
                .orElse(0);                               // Если студентов нет — возвращаем 0
    }
}