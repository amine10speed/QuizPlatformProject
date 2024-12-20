package com.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHandleApiException() {
        // Mock the CustomException
        CustomException customException = new CustomException("Test exception message");

        // Call the method
        ResponseEntity<CustomExceptionResponse> responseEntity = globalExceptionHandler.handleApiException(customException);

        // Assert that the response is not null
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        // Assert the response body content
        CustomExceptionResponse response = responseEntity.getBody();
        assertEquals("=========================================================================================================================", response.getA());
        assertEquals("Test exception message", response.getMessage());
        assertEquals("=========================================================================================================================", response.getB());
    }
}
