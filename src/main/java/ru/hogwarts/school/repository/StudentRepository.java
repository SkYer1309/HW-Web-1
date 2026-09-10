package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // Поиск по возрасту — Spring сам напишет SQL
    Collection<Student> findByAge(int age);

    // Поиск студентов в диапазоне возрастов
    Collection<Student> findByAgeBetween(int min, int max);
}