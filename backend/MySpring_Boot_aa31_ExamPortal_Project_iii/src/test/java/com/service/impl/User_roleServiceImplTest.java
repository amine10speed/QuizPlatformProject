package com.service.impl;

import com.model.Role;
import com.model.User;
import com.model.User_role;
import com.repo.User_roleRepo;
import com.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class User_roleServiceImplTest {

    @Mock
    private User_roleRepo user_roleRepo;

    @Mock
    private UserService userService;

    @InjectMocks
    private User_roleServiceImpl userRoleService;

    private User user;
    private Role role;
    private User_role userRole;

    @BeforeEach
    public void setUp() {
        // Initialisation des objets nécessaires
        user = new User();
        user.setUid(1);
        user.setUsername("testuser");

        role = new Role();
        role.setRid(1);  // Utilisation de 'setRid' pour l'ID du rôle
        role.setRolename("ADMIN");  // Utilisation de 'setRolename' pour le nom du rôle

        userRole = new User_role();
        userRole.setUser(user);
        userRole.setRole(role);
    }

    @Test
    public void testGetUser_roleFromUserAndRole() throws Exception {
        // Arrange
        when(userService.getUserByUid(1)).thenReturn(user);
        when(user_roleRepo.getByUAndR(user, role)).thenReturn(userRole);

        // Act
        User_role result = userRoleService.getUser_roleFromUserAndRole(user, role);

        // Assert
        assertNotNull(result);
        assertEquals(user, result.getUser());
        assertEquals(role, result.getRole());
        verify(userService, times(1)).getUserByUid(1);
        verify(user_roleRepo, times(1)).getByUAndR(user, role);
    }


    @Test
    public void testGetUser_roleFromUserAndRole_RoleNotFound() throws Exception {
        // Arrange
        when(userService.getUserByUid(1)).thenReturn(user);
        when(user_roleRepo.getByUAndR(user, role)).thenReturn(null);

        // Act & Assert
        assertNull(userRoleService.getUser_roleFromUserAndRole(user, role));
        verify(user_roleRepo, times(1)).getByUAndR(user, role);
    }
}
