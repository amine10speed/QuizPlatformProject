package com.service.impl;

import com.model.exam.Category;
import com.repo.CategoryRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)  // Indiquer que Mockito est utilisé pour les tests
public class CategoryServiceImplTest {

    @Mock
    private CategoryRepo categoryRepo;  // Simuler le repository

    @InjectMocks
    private CategoryServiceImpl categoryService;  // La classe à tester

    private Category category;

    @BeforeEach
    public void setUp() {
        category = new Category();
        category.setCatid(1);
        category.setTitle("Test Category");  // Remplacez 'name' par 'title'
    }

    @Test
    public void testAddCategory() {
        // Arrange
        when(categoryRepo.save(any(Category.class))).thenReturn(category);

        // Act
        Category result = categoryService.addCategory(category);

        // Assert
        assertNotNull(result);
        assertEquals(category.getCatid(), result.getCatid());
        assertEquals(category.getTitle(), result.getTitle());  // Remplacez 'getName' par 'getTitle'
        verify(categoryRepo, times(1)).save(category);  // Vérifier que save() a bien été appelé
    }

    @Test
    public void testUpdateCategory() {
        // Arrange
        when(categoryRepo.save(any(Category.class))).thenReturn(category);

        // Act
        Category result = categoryService.updateCategory(category);

        // Assert
        assertNotNull(result);
        assertEquals(category.getCatid(), result.getCatid());
        assertEquals(category.getTitle(), result.getTitle());  // Remplacez 'getName' par 'getTitle'
        verify(categoryRepo, times(1)).save(category);
    }



    @Test
    public void testGetCategory() {
        // Arrange
        when(categoryRepo.findById(1)).thenReturn(Optional.of(category));

        // Act
        Category result = categoryService.getCategory(1);

        // Assert
        assertNotNull(result);
        assertEquals(category.getCatid(), result.getCatid());
        assertEquals(category.getTitle(), result.getTitle());  // Remplacez 'getName' par 'getTitle'
    }



    @Test
    public void testDeleteCategory() {
        // Act
        categoryService.deleteCategory(1);

        // Assert
        verify(categoryRepo, times(1)).deleteById(1);
    }
}
