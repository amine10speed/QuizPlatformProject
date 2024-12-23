package com.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
    }

    @Test
    public void testUserDefaultConstructor() {
        assertNotNull(user);
        assertEquals(0, user.getUid());
        assertEquals(null, user.getUsername());
        assertEquals(null, user.getPassword());
        assertEquals(null, user.getFirstname());
        assertEquals(null, user.getLastname());
        assertEquals(null, user.getEmail());
        assertEquals(null, user.getPhone());
        assertEquals(true, user.isEnabled());
        assertEquals(null, user.getProfile());
        assertNotNull(user.getUser_roles());
        assertEquals(0, user.getUser_roles().size());
    }

    @Test
    public void testUserParameterizedConstructor() {
        Set<User_role> userRoles = new HashSet<>();
        User userWithParams = new User(1, "testuser", "password123", "Test", "User", "test@example.com", "1234567890", true, "default.png", userRoles);

        assertEquals(1, userWithParams.getUid());
        assertEquals("testuser", userWithParams.getUsername());
        assertEquals("password123", userWithParams.getPassword());
        assertEquals("Test", userWithParams.getFirstname());
        assertEquals("User", userWithParams.getLastname());
        assertEquals("test@example.com", userWithParams.getEmail());
        assertEquals("1234567890", userWithParams.getPhone());
        assertEquals(true, userWithParams.isEnabled());
        assertEquals("default.png", userWithParams.getProfile());
        assertEquals(userRoles, userWithParams.getUser_roles());
    }

    @Test
    public void testSetAndGetUid() {
        user.setUid(10);
        assertEquals(10, user.getUid());
    }

    @Test
    public void testSetAndGetUsername() {
        user.setUsername("newuser");
        assertEquals("newuser", user.getUsername());
    }

    @Test
    public void testSetAndGetPassword() {
        user.setPassword("securepassword");
        assertEquals("securepassword", user.getPassword());
    }

    @Test
    public void testSetAndGetFirstname() {
        user.setFirstname("John");
        assertEquals("John", user.getFirstname());
    }

    @Test
    public void testSetAndGetLastname() {
        user.setLastname("Doe");
        assertEquals("Doe", user.getLastname());
    }

    @Test
    public void testSetAndGetEmail() {
        user.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", user.getEmail());
    }

    @Test
    public void testSetAndGetPhone() {
        user.setPhone("9876543210");
        assertEquals("9876543210", user.getPhone());
    }

    @Test
    public void testSetAndIsEnabled() {
        user.setEnabled(false);
        assertEquals(false, user.isEnabled());
    }

    @Test
    public void testSetAndGetProfile() {
        user.setProfile("profile.png");
        assertEquals("profile.png", user.getProfile());
    }

    @Test
    public void testSetAndGetUserRoles() {
        Set<User_role> userRoles = new HashSet<>();
        user.setUser_roles(userRoles);
        assertEquals(userRoles, user.getUser_roles());
    }

    @Test
    public void testToString() {
        user.setUid(1);
        user.setUsername("testuser");
        String expectedToString = "User(uid=1, username=testuser, password=null, firstname=null, lastname=null, email=null, phone=null, enabled=true, profile=null, user_roles=[])";
        assertEquals(expectedToString, user.toString());
    }
}
