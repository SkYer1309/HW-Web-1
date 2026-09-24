package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyService {

    private static final Logger logger = LoggerFactory.getLogger(FacultyService.class);

    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        logger.info("Was invoked method for create faculty");
        return facultyRepository.save(faculty);
    }

    public Faculty getFaculty(Long id) {
        logger.info("Was invoked method for get faculty by id");
        Faculty faculty = facultyRepository.findById(id).orElse(null);
        if (faculty == null) {
            logger.error("There is not faculty with id = " + id);
        }
        return faculty;
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.info("Was invoked method for edit faculty");
        if (!facultyRepository.existsById(faculty.getId())) {
            logger.warn("Attempt to edit non-existent faculty with id = " + faculty.getId());
        }
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long id) {
        logger.info("Was invoked method for delete faculty");
        if (!facultyRepository.existsById(id)) {
            logger.warn("Attempt to delete non-existent faculty with id = " + id);
        }
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getAllFaculties() {
        logger.info("Was invoked method for get all faculties");
        return facultyRepository.findAll();
    }

    public Collection<Faculty> findByName(String name) {
        logger.info("Was invoked method for find faculty by name");
        return facultyRepository.findByName(name);
    }

    public Collection<Faculty> findByColor(String color) {
        logger.info("Was invoked method for find faculty by color");
        return facultyRepository.findByColor(color);
    }

    public Collection<Faculty> findByText(String text) {
        logger.info("Was invoked method for find faculty by text");
        return facultyRepository.findByNameContainingIgnoreCaseOrColorContainingIgnoreCase(text, text);
    }

    public Collection<Student> getFacultyStudents(Long facultyId) {
        logger.info("Was invoked method for get faculty students");
        Faculty faculty = facultyRepository.findById(facultyId).orElse(null);
        if (faculty == null) {
            logger.error("There is not faculty with id = " + facultyId);
            return null;
        }
        return faculty.getStudents();
    }
}