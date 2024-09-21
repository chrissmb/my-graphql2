package com.example.my_graphql2.exception;

public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException(Long id) {
        super(String.format("Person id %s not found.", id));
    }
}
