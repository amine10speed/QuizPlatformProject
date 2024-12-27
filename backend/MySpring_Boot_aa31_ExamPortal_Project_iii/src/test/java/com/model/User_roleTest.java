package com.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class User_roleTest {

    private User_role userRole;

    @BeforeEach
    public void setUp() {
        userRole = new User_role();
    }

    @Test
    public void testUserRoleDefaultConstructor() {
        assertNotNull(userRole);
        assertEquals(0, userRole.getUrid());
        assertEquals(null, userRole.getUser());
        assertEquals(null, userRole.getRole());
    }

    @Test
    public void testUserRoleParameterizedConstructor() {
        User user = new User();
        Role role = new Role();
        User_role userRoleWithParams = new User_role(1, user, role);

        assertEquals(1, userRoleWithParams.getUrid());
        assertEquals(user, userRoleWithParams.getUser());
        assertEquals(role, userRoleWithParams.getRole());
    }

    @Test
    public void testSetAndGetUrid() {
        userRole.setUrid(10);
        assertEquals(10, userRole.getUrid());
    }

    @Test
    public void testSetAndGetUser() {
        User user = new User();
        userRole.setUser(user);
        assertEquals(user, userRole.getUser());
    }

    @Test
    public void testSetAndGetRole() {
        Role role = new Role();
        userRole.setRole(role);
        assertEquals(role, userRole.getRole());
    }

    @Test
    public void testToString() {
        userRole.setUrid(1);
        String expectedToString = "User_role(urid=1, user=null, role=null)";
        assertEquals(expectedToString, userRole.toString());
    }
}
