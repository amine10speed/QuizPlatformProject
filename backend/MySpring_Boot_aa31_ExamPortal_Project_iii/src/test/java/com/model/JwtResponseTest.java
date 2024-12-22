package com.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JwtResponseTest {

    @Test
    public void testNoArgsConstructor() {
        // Testing no-args constructor
        JwtResponse jwtResponse = new JwtResponse();
        assertNotNull(jwtResponse);
        assertNull(jwtResponse.getToken());
    }

    @Test
    public void testAllArgsConstructor() {
        // Testing all-args constructor
        JwtResponse jwtResponse = new JwtResponse("testToken");
        assertEquals("testToken", jwtResponse.getToken());
    }

    @Test
    public void testSettersAndGetters() {
        // Testing setters and getters
        JwtResponse jwtResponse = new JwtResponse();

        jwtResponse.setToken("newToken");

        assertEquals("newToken", jwtResponse.getToken());
    }


}
