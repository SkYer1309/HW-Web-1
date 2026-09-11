package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Collection<Faculty> findByName(String name);

    Collection<Faculty> findByColor(String color);

    // Поиск по имени ИЛИ цвету, игнорируя регистр
    Collection<Faculty> findByNameContainingIgnoreCaseOrColorContainingIgnoreCase(
            String name, String color);
}