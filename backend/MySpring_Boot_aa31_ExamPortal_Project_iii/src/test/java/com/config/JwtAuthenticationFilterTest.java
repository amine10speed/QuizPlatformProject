package com.config;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.helper.JwtUtil;

public class JwtAuthenticationFilterTest {

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Mock
    private UserDetails userDetails;

    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.clearContext();
    }

    @Test
    public void testDoFilterInternal_ValidToken() throws ServletException, IOException {
        // Mock request header with a valid token
        when(request.getHeader("Authorization")).thenReturn("Bearer validToken");
        when(jwtUtil.extractUsername("validToken")).thenReturn("testUser");
        when(userDetailsServiceImpl.loadUserByUsername("testUser")).thenReturn(userDetails);
        when(jwtUtil.validateToken("validToken", userDetails)).thenReturn(true);

        // Execute the filter
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Verify that the SecurityContext is updated
        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
        assertTrue(SecurityContextHolder.getContext().getAuthentication() instanceof UsernamePasswordAuthenticationToken);

        // Verify that the filter chain is called
        verify(filterChain).doFilter(request, response);
    }





    @Test
    public void testDoFilterInternal_NoAuthorizationHeader() throws ServletException, IOException {
        // Mock request with no Authorization header
        when(request.getHeader("Authorization")).thenReturn(null);

        // Execute the filter
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Verify that SecurityContext is not updated
        assertNull(SecurityContextHolder.getContext().getAuthentication());

        // Verify that the filter chain is called
        verify(filterChain).doFilter(request, response);
    }

    @Test
    public void testDoFilterInternal_TokenNotBearer() throws ServletException, IOException {
        // Mock request with an improperly formatted token
        when(request.getHeader("Authorization")).thenReturn("InvalidTokenFormat");

        // Execute the filter
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Verify that SecurityContext is not updated
        assertNull(SecurityContextHolder.getContext().getAuthentication());

        // Verify that the filter chain is called
        verify(filterChain).doFilter(request, response);
    }
}
