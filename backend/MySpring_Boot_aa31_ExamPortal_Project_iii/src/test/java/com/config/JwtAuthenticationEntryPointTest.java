package com.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;

import static org.mockito.Mockito.*;

public class JwtAuthenticationEntryPointTest {

    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        jwtAuthenticationEntryPoint = new JwtAuthenticationEntryPoint();
    }

    @Test
    public void testCommence() throws Exception {
        // Mock ServletOutputStream
        ServletOutputStream outputStream = mock(ServletOutputStream.class);
        when(response.getOutputStream()).thenReturn(outputStream);

        // Mock AuthenticationException
        AuthenticationException authException = mock(AuthenticationException.class);

        // Execute the method
        jwtAuthenticationEntryPoint.commence(request, response, authException);

        // Verify response setup
        verify(response).setContentType("application/json");
        verify(response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        verify(outputStream).println(
                "{\"a\":\"=================================================================================================\"," +
                        " \"Exception\": \"401 Unauthorized Request\" ," +
                        "\"b\":\"==============================================================================================================\"}"
        );
    }

}
