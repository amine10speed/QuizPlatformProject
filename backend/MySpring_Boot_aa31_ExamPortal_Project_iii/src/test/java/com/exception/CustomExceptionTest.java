package com.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CustomExceptionTest {

    @Test
    void testCustomExceptionMessage() {
        // Create a new CustomException with a test message
        String testMessage = "This is a custom exception message";
        CustomException customException = new CustomException(testMessage);

        // Assert that the message is correctly set
        assertEquals(testMessage, customException.getMessage());
    }
}
