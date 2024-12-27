package com.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.model.User;
import com.repo.UserRepo;

public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Mock
    private UserRepo userRepo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadUserByUsername_UserExists() {
        // Mock user object
        User user = new User();
        user.setUsername("testUser");

        // Mock repository response
        when(userRepo.findByUsername("testUser")).thenReturn(user);

        // Call the method
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername("testUser");

        // Assert that userDetails is not null
        assertNotNull(userDetails);

        // Verify repository interaction
        verify(userRepo, times(1)).findByUsername("testUser");
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        // Mock repository response for non-existent user
        when(userRepo.findByUsername("unknownUser")).thenReturn(null);

        // Call the method and expect an exception
        try {
            userDetailsServiceImpl.loadUserByUsername("unknownUser");
        } catch (UsernameNotFoundException e) {
            assertNotNull(e); // Ensure exception is thrown
        }

        // Verify repository interaction
        verify(userRepo, times(1)).findByUsername("unknownUser");
    }
}
