package com.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RoleTest {

    private Role role;

    @BeforeEach
    public void setUp() {
        role = new Role();
    }

    @Test
    public void testRoleDefaultConstructor() {
        assertNotNull(role);
        assertEquals(0, role.getRid());
        assertEquals(null, role.getRolename());
        assertNotNull(role.getUser_roles());
        assertEquals(0, role.getUser_roles().size());
    }

    @Test
    public void testRoleParameterizedConstructor() {
        Set<User_role> userRoles = new HashSet<>();
        Role roleWithParams = new Role(1, "Admin", userRoles);

        assertEquals(1, roleWithParams.getRid());
        assertEquals("Admin", roleWithParams.getRolename());
        assertEquals(userRoles, roleWithParams.getUser_roles());
    }

    @Test
    public void testSetAndGetRid() {
        role.setRid(10);
        assertEquals(10, role.getRid());
    }

    @Test
    public void testSetAndGetRolename() {
        role.setRolename("Manager");
        assertEquals("Manager", role.getRolename());
    }

    @Test
    public void testSetAndGetUserRoles() {
        Set<User_role> userRoles = new HashSet<>();
        role.setUser_roles(userRoles);
        assertEquals(userRoles, role.getUser_roles());
    }

    @Test
    public void testToString() {
        role.setRid(5);
        role.setRolename("User");
        String expectedToString = "Role(rid=5, rolename=User, user_roles=[])";
        assertEquals(expectedToString, role.toString());
    }
}
