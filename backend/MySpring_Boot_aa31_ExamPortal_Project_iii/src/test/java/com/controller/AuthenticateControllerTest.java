package com.controller;

import com.config.CustomUserDetails;
import com.config.UserDetailsServiceImpl;
import com.exception.CustomException;
import com.helper.JwtUtil;
import com.model.JwtRequest;
import com.model.JwtResponse;
import com.model.User;
import com.repo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthenticateControllerTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticateController authenticateController;

    private JwtRequest jwtRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        jwtRequest = new JwtRequest();
        jwtRequest.setUsername("testuser");
        jwtRequest.setPassword("testpassword");
    }

    @Test
    void generateToken_ShouldReturnToken_WhenCredentialsAreValid() throws Exception {
        // Arrange
        String token = "test.jwt.token";
        UserDetails userDetails = mock(CustomUserDetails.class);
        when(userDetailsServiceImpl.loadUserByUsername("testuser")).thenReturn(userDetails);
        when(jwtUtil.generateToken(userDetails)).thenReturn(token);
        when(authenticationManager.authenticate(any())).thenReturn(null);  // Assume valid authentication

        // Act
        ResponseEntity<?> response = authenticateController.generateToken(jwtRequest);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        JwtResponse jwtResponse = (JwtResponse) response.getBody();
        assertEquals(token, jwtResponse.getToken());
    }



    @Test
    void getCurrentUser_ShouldReturnUserDetails_WhenAuthenticated() {
        // Arrange
        String username = "testuser";
        Principal principal = () -> username;
        UserDetails userDetails = mock(CustomUserDetails.class);
        when(userDetailsServiceImpl.loadUserByUsername(username)).thenReturn(userDetails);

        // Act
        UserDetails currentUser = authenticateController.getCurrentUser(principal);

        // Assert
        assertNotNull(currentUser);
        assertEquals(userDetails, currentUser);
    }

    @Test
    void getLoggedInUser_ShouldReturnUser_WhenAuthenticated() {
        // Arrange
        String username = "testuser";
        Principal principal = () -> username;
        User user = new User();
        user.setUsername(username);
        when(userRepo.findByUsername(username)).thenReturn(user);

        // Act
        User loggedInUser = authenticateController.getLoggedInUser(principal);

        // Assert
        assertNotNull(loggedInUser);
        assertEquals(username, loggedInUser.getUsername());
    }
}
