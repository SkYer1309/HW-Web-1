package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }
    @PostMapping
    public Long createFaculty(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty).getId();
    }
    @GetMapping("/{id}")
    public Faculty getFaculty(@PathVariable Long id) {
        return facultyService.getFaculty(id);
    }
    @GetMapping
    public Collection<Faculty> getAllFaculties() {
        return facultyService.getAllFaculties();
    }
    @PutMapping
    public Faculty updateFaculty(@RequestBody Faculty faculty) {
        return facultyService.editFaculty(faculty);
    }
    @DeleteMapping("/{id}")
    public void deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
    }
    @GetMapping("/filter/color")
    public Collection<Faculty> getFacultiesByColor(@RequestParam String color) {
        return facultyService.findByColor(color);
    }
    @GetMapping("/filter/name")
    public Collection<Faculty> getFacultiesByName(@RequestParam String name) {
        return facultyService.findByName(name);
    }
    // Новый эндпоинт: поиск факультета по тексту (регистронезависимый)
    @GetMapping("/filter/text")
    public Collection<Faculty> getFacultiesByText(@RequestParam String text) {
        return facultyService.findByText(text);
    }
    // Новый эндпоинт: получить студентов факультета
    @GetMapping("/{id}/students")
    public Collection<Student> getFacultyStudents(@PathVariable Long id) {
        return facultyService.getFacultyStudents(id);
    }
}