package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
public class MathController {

    // Обычный (последовательный) стрим — медленный
    @GetMapping("/math/sequential")
    public long sequentialSum() {
        long start = System.currentTimeMillis();

        long sum = Stream.iterate(1L, a -> a + 1)
                .limit(1_000_000)
                .reduce(0L, Long::sum);

        long end = System.currentTimeMillis();
        System.out.println("Sequential time: " + (end - start) + " ms");

        return sum;
    }

    // Параллельный стрим — быстрый
    @GetMapping("/math/parallel")
    public long parallelSum() {
        long start = System.currentTimeMillis();

        long sum = Stream.iterate(1L, a -> a + 1)
                .limit(1_000_000)
                .parallel()                           // ← ключевое изменение!
                .reduce(0L, Long::sum);

        long end = System.currentTimeMillis();
        System.out.println("Parallel time: " + (end - start) + " ms");

        return sum;
    }
}