package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findByAge(int age);

    Collection<Student> findByAgeBetween(int min, int max);

    // 1. Количество всех студентов
    @Query("SELECT COUNT(s) FROM Student s")
    long countAllStudents();

    // 2. Средний возраст студентов
    @Query("SELECT AVG(s.age) FROM Student s")
    Double getAverageAge();

    // 3. Пять последних студентов (по убыванию id)
    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT 5", nativeQuery = true)
    List<Student> findLastFiveStudents();
}