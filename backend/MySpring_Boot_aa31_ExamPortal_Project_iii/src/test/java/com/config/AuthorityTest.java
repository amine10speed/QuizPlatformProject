package com.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AuthorityTest {

    @Test
    public void testConstructor() {
        // Test the constructor and ensure the authority is set correctly
        Authority authority = new Authority("ROLE_USER");
        assertNotNull(authority);
        assertEquals("ROLE_USER", authority.getAuthority());
    }

    @Test
    public void testGetAuthority() {
        // Test the getAuthority method
        Authority authority = new Authority("ROLE_ADMIN");
        assertEquals("ROLE_ADMIN", authority.getAuthority());
    }
}
