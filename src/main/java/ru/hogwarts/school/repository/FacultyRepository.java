package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    // Поиск по названию
    Collection<Faculty> findByName(String name);

    // Поиск по цвету
    Collection<Faculty> findByColor(String color);
}