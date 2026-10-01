package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;
import java.util.List;

import java.util.stream.Collectors;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Long createStudent(@RequestBody Student student) {
        return studentService.addStudent(student).getId();
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    @GetMapping
    public Collection<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PutMapping
    public Student updateStudent(@RequestBody Student student) {
        return studentService.editStudent(student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/filter/age")
    public Collection<Student> getStudentsByAge(@RequestParam int age) {
        return studentService.findByAge(age);
    }

    // Новый эндпоинт: фильтрация по диапазону возрастов
    @GetMapping("/filter/ageBetween")
    public Collection<Student> getStudentsByAgeBetween(
            @RequestParam int min, @RequestParam int max) {
        return studentService.findByAgeBetween(min, max);
    }

    // Новый эндпоинт: получить факультет студента
    @GetMapping("/{id}/faculty")
    public Faculty getStudentFaculty(@PathVariable Long id) {
        return studentService.getStudentFaculty(id);
    }


    // Количество всех студентов
    @GetMapping("/count")
    public long countAllStudents() {
        return studentService.countAllStudents();
    }

    // Пять последних студентов
    @GetMapping("/lastFive")
    public List<Student> getLastFiveStudents() {
        return studentService.findLastFiveStudents();
    }
    @GetMapping("/names/startsWithA")
    public List<String> getNamesStartingWithA() {
        return studentService.getNamesStartingWithA();
    }
    @GetMapping("/averageAge")
    public double getAverageAge() {
        return studentService.getAverageAge();
    }
        @GetMapping("/students/print-parallel")
        public void printStudentsParallel() {
            List<Student> students = studentService.getAllStudents().stream()
                    .limit(6)
                    .collect(Collectors.toList());

            // Вывод первых двух имен в основном потоке
            if (students.size() > 0) {
                System.out.println(Thread.currentThread().getName() + ": " + students.get(0).getName());
            }
            if (students.size() > 1) {
                System.out.println(Thread.currentThread().getName() + ": " + students.get(1).getName());
            }

            // Вывод третьего и четвертого имени в параллельном потоке
            if (students.size() > 2) {
                Thread thread1 = new Thread(() -> {
                    if (students.size() > 2) {
                        System.out.println(Thread.currentThread().getName() + ": " + students.get(2).getName());
                    }
                    if (students.size() > 3) {
                        System.out.println(Thread.currentThread().getName() + ": " + students.get(3).getName());
                    }
                });
                thread1.start();
            }

            // Вывод пятого и шестого имени в еще одном параллельном потоке
            if (students.size() > 4) {
                Thread thread2 = new Thread(() -> {
                    if (students.size() > 4) {
                        System.out.println(Thread.currentThread().getName() + ": " + students.get(4).getName());
                    }
                    if (students.size() > 5) {
                        System.out.println(Thread.currentThread().getName() + ": " + students.get(5).getName());
                    }
                });
                thread2.start();
            }
        }
        // Синхронизированный метод для вывода имени
        private synchronized void printName(String name) {
            System.out.println(Thread.currentThread().getName() + ": " + name);
        }
        @GetMapping("/students/print-synchronized")
        public void printStudentsSynchronized() {
            List<Student> students = studentService.getAllStudents().stream()
                    .limit(6)
                    .collect(Collectors.toList());

            // Вывод первых двух имен в основном потоке
            if (students.size() > 0) {
                printName(students.get(0).getName());
            }
            if (students.size() > 1) {
                printName(students.get(1).getName());
            }

            // Вывод третьего и четвертого имени в параллельном потоке
            if (students.size() > 2) {
                Thread thread1 = new Thread(() -> {
                    if (students.size() > 2) {
                        printName(students.get(2).getName());
                    }
                    if (students.size() > 3) {
                        printName(students.get(3).getName());
                    }
                });
                thread1.start();
            }

            // Вывод пятого и шестого имени в еще одном параллельном потоке
            if (students.size() > 4) {
                Thread thread2 = new Thread(() -> {
                    if (students.size() > 4) {
                        printName(students.get(4).getName());
                    }
                    if (students.size() > 5) {
                        printName(students.get(5).getName());
                    }
                });
                thread2.start();
            }
        }

    }
