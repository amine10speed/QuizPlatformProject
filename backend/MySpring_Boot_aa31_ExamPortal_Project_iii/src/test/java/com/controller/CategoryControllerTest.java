package com.controller;

import com.model.exam.Category;
import com.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.List;
import java.util.Set;

class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    private MockMvc mockMvc;

    private Category category;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
        category = new Category();
        category.setCatid(1);  // Assurez-vous d'utiliser "catid"
        category.setTitle("Test Category");  // Utilisez "title" au lieu de "name"
        category.setDescription("Description of test category");
    }

    @Test
    void addCategory_ShouldReturnCategory_WhenCategoryIsValid() {
        // Arrange
        when(categoryService.addCategory(any(Category.class))).thenReturn(category);

        // Act
        ResponseEntity<?> response = categoryController.addCategory(category);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Category createdCategory = (Category) response.getBody();
        assertNotNull(createdCategory);
        assertEquals(category.getCatid(), createdCategory.getCatid());  // Assurez-vous de comparer "catid"
        assertEquals(category.getTitle(), createdCategory.getTitle());  // Comparez "title"
    }

    @Test
    void getCategory_ShouldReturnCategory_WhenCategoryExists() {
        // Arrange
        when(categoryService.getCategory(1)).thenReturn(category);

        // Act
        Category fetchedCategory = categoryController.getCategory(1);

        // Assert
        assertNotNull(fetchedCategory);
        assertEquals(category.getCatid(), fetchedCategory.getCatid());  // Assurez-vous de comparer "catid"
        assertEquals(category.getTitle(), fetchedCategory.getTitle());  // Comparez "title"
    }


    @Test
    void updateCategory_ShouldReturnUpdatedCategory_WhenCategoryIsValid() {
        // Arrange
        category.setTitle("Updated Category");
        when(categoryService.updateCategory(any(Category.class))).thenReturn(category);

        // Act
        Category updatedCategory = categoryController.updateCategory(category);

        // Assert
        assertNotNull(updatedCategory);
        assertEquals("Updated Category", updatedCategory.getTitle());
    }



    @Test
    void deleteCategory_ShouldThrowException_WhenCategoryDoesNotExist() {
        // Arrange
        doThrow(new RuntimeException("Category not found")).when(categoryService).deleteCategory(999);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            categoryController.deleteCategory(999);
        });
        assertEquals("Category not found", exception.getMessage());
    }
}
