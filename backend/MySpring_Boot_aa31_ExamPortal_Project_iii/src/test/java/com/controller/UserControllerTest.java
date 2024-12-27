package com.controller;

import com.model.User;
import com.service.UserService;
import com.service.User_roleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

public class UserControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private User_roleService userRoleService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testCreateUser() throws Exception {
        // Given
        User user = new User();
        user.setUid(1);
        user.setUsername("yashmore");
        user.setPassword("1234");
        user.setEmail("yash@gmail.com");
        user.setPhone("1234567891");
        user.setProfile("default.png");

        // Simuler le comportement de userService.createUser()
        when(userService.createUser(any(User.class), anySet())).thenReturn(user);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        // When & Then
        mockMvc.perform(post("/user/create")
                        .contentType("application/json")
                        .content("{\"username\":\"yashmore\", \"firstname\":\"Yash\", \"lastname\":\"More\", \"password\":\"1234\", \"email\":\"yash@gmail.com\", \"phone\":\"1234567891\", \"profile\":\"default.png\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("yashmore"))
                .andExpect(jsonPath("$.email").value("yash@gmail.com"));

        // Vérifier que la méthode createUser a bien été appelée une fois
        verify(userService, times(1)).createUser(any(User.class), anySet());
    }

    @Test
    public void testGetUserByUid() throws Exception {
        // Given
        User user = new User();
        user.setUid(1);
        user.setUsername("yashmore");

        // Simuler le comportement de userService.getUserByUid()
        when(userService.getUserByUid(1)).thenReturn(user);

        // When & Then
        mockMvc.perform(get("/user/uid/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("yashmore"));

        // Vérifier que la méthode getUserByUid a bien été appelée une fois avec l'uid 1
        verify(userService, times(1)).getUserByUid(1);
    }

    @Test
    public void testGetUserByUsername() throws Exception {
        // Given
        User user = new User();
        user.setUid(1);
        user.setUsername("yashmore");

        // Simuler le comportement de userService.getUser()
        when(userService.getUser("yashmore")).thenReturn(user);

        // When & Then
        mockMvc.perform(get("/user/username/yashmore"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("yashmore"));

        // Vérifier que la méthode getUser a bien été appelée une fois avec le username "yashmore"
        verify(userService, times(1)).getUser("yashmore");
    }

    @Test
    public void testGetAllUsers() throws Exception {
        // Given
        User user1 = new User();
        user1.setUid(1);
        user1.setUsername("yashmore");
        User user2 = new User();
        user2.setUid(2);
        user2.setUsername("johndoe");
        List<User> userList = Arrays.asList(user1, user2);

        // Simuler le comportement de userService.getAllUsers()
        when(userService.getAllUsers()).thenReturn(userList);

        // When & Then
        mockMvc.perform(get("/user/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("yashmore"))
                .andExpect(jsonPath("$[1].username").value("johndoe"));

        // Vérifier que la méthode getAllUsers a bien été appelée une fois
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    public void testDeleteUser() throws Exception {
        // Given
        doNothing().when(userService).deleteUser(1);

        // When & Then
        mockMvc.perform(delete("/user/delete/1"))
                .andExpect(status().isOk());

        // Vérifier que la méthode deleteUser a bien été appelée une fois avec l'uid 1
        verify(userService, times(1)).deleteUser(1);
    }


}
