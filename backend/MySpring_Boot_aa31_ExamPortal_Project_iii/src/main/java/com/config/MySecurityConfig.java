package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private AuthenticationEntryPoint entryPoint;

    @Autowired
    private JwtAuthenticationFilter filter;

    // Bean to encode passwords (you can use NoOpPasswordEncoder for testing, but use BCryptPasswordEncoder in production)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Disable CSRF and configure CORS for stateless authentication
        http.csrf().disable().cors().disable()
                .authorizeRequests()
                // Allow access to Swagger UI and OpenAPI docs
                .antMatchers("/swagger-ui/**", "/v2/api-docs/**", "/swagger-ui/index.html","/swagger-ui.html", "/favicon.ico","/").permitAll()
                // Allow access to public endpoints (token, user creation)
                .antMatchers("/token", "/user/create").permitAll()
                // Allow OPTIONS requests (CORS pre-flight)
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                // All other requests must be authenticated
                .anyRequest().authenticated()
                .and()
                // Handle authentication errors
                .exceptionHandling().authenticationEntryPoint(entryPoint)
                .and()
                // Set session management to stateless for JWT
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Add the JWT filter before UsernamePasswordAuthenticationFilter
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }

    // Expose AuthenticationManager as a bean
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
