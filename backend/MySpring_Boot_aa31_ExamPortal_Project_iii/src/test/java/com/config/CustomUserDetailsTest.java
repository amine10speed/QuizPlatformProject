package com.config;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import com.model.Role;
import com.model.User;
import com.model.User_role;

public class CustomUserDetailsTest {

    private User user;
    private CustomUserDetails customUserDetails;

    @BeforeEach
    public void setup() {
        // Create mock Role
        Role role = new Role();
        role.setRolename("ROLE_USER");

        // Create mock User_role
        User_role userRole = new User_role();
        userRole.setRole(role);

        Set<User_role> userRoles = new HashSet<>();
        userRoles.add(userRole);

        // Create mock User
        user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
        user.setEnabled(true);
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setEmail("john.doe@example.com");
        user.setPhone("1234567890");
        user.setProfile("profile.jpg");
        user.setUser_roles(userRoles);

        customUserDetails = new CustomUserDetails(user);
    }

    @Test
    public void testGetAuthorities() {
        Collection<? extends GrantedAuthority> authorities = customUserDetails.getAuthorities();
        assertNotNull(authorities);
        assertEquals(1, authorities.size());
        assertTrue(authorities.stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_USER")));
    }

    @Test
    public void testGetPassword() {
        assertEquals("testPassword", customUserDetails.getPassword());
    }

    @Test
    public void testGetUsername() {
        assertEquals("testUser", customUserDetails.getUsername());
    }

    @Test
    public void testIsAccountNonExpired() {
        assertTrue(customUserDetails.isAccountNonExpired());
    }

    @Test
    public void testIsAccountNonLocked() {
        assertTrue(customUserDetails.isAccountNonLocked());
    }

    @Test
    public void testIsCredentialsNonExpired() {
        assertTrue(customUserDetails.isCredentialsNonExpired());
    }

    @Test
    public void testIsEnabled() {
        assertTrue(customUserDetails.isEnabled());
    }

    @Test
    public void testGetCustomFields() {
        assertEquals("John", customUserDetails.getFirstname());
        assertEquals("Doe", customUserDetails.getLastname());
        assertEquals("john.doe@example.com", customUserDetails.getEmail());
        assertEquals("1234567890", customUserDetails.getPhone());
        assertEquals("profile.jpg", customUserDetails.getProfile());
    }
}
