package com.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CustomExceptionResponseTest {

    @Test
    public void testNoArgsConstructor() {
        // Test the no-args constructor
        CustomExceptionResponse response = new CustomExceptionResponse();
        assertNotNull(response);
    }

    @Test
    public void testAllArgsConstructor() {
        // Test the all-args constructor
        CustomExceptionResponse response = new CustomExceptionResponse("CodeA", "This is a message", "CodeB");
        assertNotNull(response);
        assertEquals("CodeA", response.getA());
        assertEquals("This is a message", response.getMessage());
        assertEquals("CodeB", response.getB());
    }

    @Test
    public void testSettersAndGetters() {
        // Test the setters and getters
        CustomExceptionResponse response = new CustomExceptionResponse();
        response.setA("CodeA");
        response.setMessage("This is a message");
        response.setB("CodeB");

        assertEquals("CodeA", response.getA());
        assertEquals("This is a message", response.getMessage());
        assertEquals("CodeB", response.getB());
    }
}
