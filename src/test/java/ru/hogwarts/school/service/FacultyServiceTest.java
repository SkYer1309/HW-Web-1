package ru.hogwarts.school.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FacultyServiceTest {

    @Mock
    private FacultyRepository facultyRepository;

    @InjectMocks
    private FacultyService facultyService;

    private Faculty testFaculty;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testFaculty = new Faculty(1L, "Gryffindor", "red");
    }

    @Test
    void testAddFaculty() {
        when(facultyRepository.save(testFaculty)).thenReturn(testFaculty);

        Faculty result = facultyService.addFaculty(testFaculty);

        assertNotNull(result);
        assertEquals("Gryffindor", result.getName());
        assertEquals("red", result.getColor());
        verify(facultyRepository, times(1)).save(testFaculty);
    }

    @Test
    void testGetFaculty() {
        when(facultyRepository.findById(1L)).thenReturn(Optional.of(testFaculty));

        Faculty result = facultyService.getFaculty(1L);

        assertNotNull(result);
        assertEquals("Gryffindor", result.getName());
        verify(facultyRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteFaculty() {
        facultyService.deleteFaculty(1L);
        verify(facultyRepository, times(1)).deleteById(1L);
    }
}