package com.example.my_graphql2.controller;

import com.example.my_graphql2.entity.Person;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PersonController {

    @QueryMapping
    public Person personById(@Argument Long id) {
        return Person.getById(id);
    }

    @SchemaMapping
    public Person father(Person person) {
        if (person.fatherId() != null) {
            return Person.getById(person.fatherId());
        }
        return null;
    }

    @SchemaMapping
    public Person mother(Person person) {
        if (person.motherId() != null) {
            return Person.getById(person.motherId());
        }
        return null;
    }

    @SchemaMapping
    public List<Person> children(Person person) {
        return Person.persons.stream()
                .filter(p ->
                        (p.fatherId() != null && p.fatherId().equals(person.id()))
                        || (p.motherId() != null && p.motherId().equals(person.id()))
                )
                .collect(Collectors.toList());
    }
}
