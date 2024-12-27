package com.service.impl;

import com.exception.CustomException;
import com.model.Role;
import com.model.User;
import com.model.User_role;
import com.repo.RoleRepo;
import com.repo.UserRepo;
import com.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private RoleRepo roleRepo;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private Role role;
    private Set<User_role> userRoles;

    @BeforeEach
    public void setUp() {
        // Initialisation des objets nécessaires
        user = new User();
        user.setUid(1);
        user.setUsername("testuser");

        role = new Role();
        role.setRid(1);
        role.setRolename("ADMIN");

        User_role userRole = new User_role();
        userRole.setUser(user);
        userRole.setRole(role);

        userRoles = new HashSet<>();
        userRoles.add(userRole);
    }

    @Test
    public void testCreateUser_Success() throws Exception {
        // Arrange
        when(userRepo.findByUsername("testuser")).thenReturn(null);  // Simuler qu'aucun utilisateur n'existe avec ce nom
        when(userRepo.save(any(User.class))).thenReturn(user);  // Simuler la sauvegarde de l'utilisateur
        when(roleRepo.save(any(Role.class))).thenReturn(role);  // Simuler la sauvegarde du rôle

        // Act
        User createdUser = userService.createUser(user, userRoles);

        // Assert
        assertNotNull(createdUser);
        assertEquals("testuser", createdUser.getUsername());
        verify(userRepo, times(1)).save(any(User.class));
        verify(roleRepo, times(1)).save(any(Role.class));
    }

    @Test
    public void testCreateUser_UserAlreadyExists() throws Exception {
        // Arrange
        when(userRepo.findByUsername("testuser")).thenReturn(user);  // Simuler que l'utilisateur existe déjà

        // Act & Assert
        assertThrows(CustomException.class, () -> userService.createUser(user, userRoles));
        verify(userRepo, times(1)).findByUsername("testuser");
        verify(userRepo, times(0)).save(any(User.class));  // Vérifier que la sauvegarde n'est pas appelée
    }

    @Test
    public void testGetUserByUid_Success() throws Exception {
        // Arrange
        when(userRepo.findById(1)).thenReturn(java.util.Optional.of(user));  // Simuler la récupération de l'utilisateur par UID

        // Act
        User fetchedUser = userService.getUserByUid(1);

        // Assert
        assertNotNull(fetchedUser);
        assertEquals(1, fetchedUser.getUid());
        verify(userRepo, times(1)).findById(1);
    }

    @Test
    public void testGetUserByUid_UserNotFound() throws Exception {
        // Arrange
        when(userRepo.findById(999)).thenReturn(java.util.Optional.empty());  // Simuler un utilisateur inexistant

        // Act & Assert
        assertThrows(CustomException.class, () -> userService.getUserByUid(999));
        verify(userRepo, times(1)).findById(999);
    }
}