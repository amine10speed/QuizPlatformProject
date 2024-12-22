package com.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JwtRequestTest {

    @Test
    public void testNoArgsConstructor() {
        // Testing no-args constructor
        JwtRequest jwtRequest = new JwtRequest();
        assertNotNull(jwtRequest);
        assertNull(jwtRequest.getUsername());
        assertNull(jwtRequest.getPassword());
    }

    @Test
    public void testAllArgsConstructor() {
        // Testing all-args constructor
        JwtRequest jwtRequest = new JwtRequest("testUser", "testPass");
        assertEquals("testUser", jwtRequest.getUsername());
        assertEquals("testPass", jwtRequest.getPassword());
    }

    @Test
    public void testSettersAndGetters() {
        // Testing setters and getters
        JwtRequest jwtRequest = new JwtRequest();

        jwtRequest.setUsername("newUser");
        jwtRequest.setPassword("newPass");

        assertEquals("newUser", jwtRequest.getUsername());
        assertEquals("newPass", jwtRequest.getPassword());
    }


}
