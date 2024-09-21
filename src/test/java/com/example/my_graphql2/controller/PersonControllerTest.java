package com.example.my_graphql2.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

@AutoConfigureGraphQlTester
@SpringBootTest
class PersonControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    void personById1() {
        graphQlTester.documentName("PersonDetails")
                .variable("id", "1")
                .execute()
                .path("personById")
                .matchesJson("""
                        {
                          "id": "1",
                          "name": "John",
                          "birthday": "1990-06-15",
                          "height": 1.8,
                          "father": null,
                          "mother": null,
                          "children": [
                            {
                              "id": "3",
                              "name": "Luca"
                            },
                            {
                              "id": "4",
                              "name": "Jenny"
                            }
                          ]
                        }
                        """);
    }

    @Test
    void personById3() {
        graphQlTester.documentName("PersonDetails")
                .variable("id", "3")
                .execute()
                .path("personById")
                .matchesJson("""
                        {
                          "id": "3",
                          "name": "Luca",
                          "birthday": "2015-01-21",
                          "height": 1.2,
                          "father": {
                            "id": "1",
                            "name": "John"
                          },
                          "mother": {
                            "id": "2",
                            "name": "Maria"
                          },
                          "children": []
                        }
                        """);
    }

    @Test
    void personById9() {
        graphQlTester.documentName("PersonDetails")
                .variable("id", "9")
                .execute()
                .errors()
                .filter(error -> error.getMessage() != null && error.getMessage().contains("not found"))
                .verify();
    }
}