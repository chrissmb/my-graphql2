package com.example.my_graphql2.entity;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public record Person(Long id, String name, LocalDate birthday, Double height, Long fatherId, Long motherId) {

    public static List<Person> persons = Arrays.asList(
            new Person(1L, "John", LocalDate.of(1990, Month.JUNE, 15), 1.8, null, null),
            new Person(2L, "Maria", LocalDate.of(2000, 3, 2), 1.66, null, null),
            new Person(3L, "Luca", LocalDate.of(2015, 1, 21), 1.2, 1L, 2L),
            new Person(4L, "Jenny", LocalDate.of(2017, 4, 7), 1.2, 1L, 2L)
    );

    public static Person getById(Long id) {
        return persons.stream()
                .filter(person -> person.id().equals(id))
                .findFirst()
                .orElse(null);
    }
}
