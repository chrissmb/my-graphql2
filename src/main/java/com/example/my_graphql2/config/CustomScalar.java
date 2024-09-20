package com.example.my_graphql2.config;

import graphql.GraphQLContext;
import graphql.execution.CoercedVariables;
import graphql.language.StringValue;
import graphql.language.Value;
import graphql.schema.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class CustomScalar {

    public static final GraphQLScalarType LOCAL_DATE = GraphQLScalarType.newScalar()
            .name("LocalDate")
            .description("A custom scalar that handles LocalDate")
            .coercing(new Coercing<LocalDate, String>() {
                @Override
                public String serialize(Object dataFetcherResult, GraphQLContext graphQLContext, Locale locale) throws CoercingSerializeException {
                    try {
                        LocalDate localDate = (LocalDate) dataFetcherResult;
                        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE.withZone(ZoneId.systemDefault());
                        return formatter.format(localDate);
                    } catch (Exception e) {
                        throw new CoercingSerializeException("Invalid input: " + e.getMessage());
                    }
                }

                @Override
                public LocalDate parseValue(Object input, GraphQLContext graphQLContext, Locale locale) throws CoercingParseValueException {
                    try {
                        String stringValue = (String) input;
                        return LocalDate.parse(stringValue);
                    } catch (Exception e) {
                        throw new CoercingParseValueException("Invalid input: " + e.getMessage());
                    }
                }

                @Override
                public LocalDate parseLiteral(Value<?> input, CoercedVariables variables, GraphQLContext graphQLContext, Locale locale) throws CoercingParseLiteralException {
                    try {
                        String stringValue = ((StringValue) input).getValue();
                        return LocalDate.parse(stringValue);
                    } catch (Exception e) {
                        throw new CoercingParseLiteralException("Invalid input: " + e.getMessage());
                    }
                }
            })
            .build();

    public static final GraphQLScalarType LONG = GraphQLScalarType.newScalar()
            .name("Long")
            .description("A custom scalar that handles Long")
            .coercing(new Coercing<Long, String>() {
                @Override
                public String serialize(Object dataFetcherResult, GraphQLContext graphQLContext, Locale locale) throws CoercingSerializeException {
                    try {
                        Long value = (Long) dataFetcherResult;
                        return value.toString();
                    } catch (Exception e) {
                        throw new CoercingSerializeException("Invalid input: " + e.getMessage());
                    }
                }

                @Override
                public Long parseValue(Object input, GraphQLContext graphQLContext, Locale locale) throws CoercingParseValueException {
                    try {
                        String stringValue = (String) input;
                        return Long.valueOf(stringValue);
                    } catch (Exception e) {
                        throw new CoercingParseValueException("Invalid input: " + e.getMessage());
                    }
                }

                @Override
                public Long parseLiteral(Value<?> input, CoercedVariables variables, GraphQLContext graphQLContext, Locale locale) throws CoercingParseLiteralException {
                    try {
                        String stringValue = ((StringValue) input).getValue();
                        return Long.valueOf(stringValue);
                    } catch (Exception e) {
                        throw new CoercingParseLiteralException("Invalid input: " + e.getMessage());
                    }
                }
            })
            .build();
}
