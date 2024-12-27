package com.service.impl;

import com.model.exam.Category;
import com.model.exam.Quiz;
import com.repo.CategoryRepo;
import com.repo.QuizRepo;
import com.exception.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class QuizServiceImplTest {

    @Mock
    private QuizRepo quizRepo;  // Simuler le repository de Quiz

    @Mock
    private CategoryRepo catRepo;  // Simuler le repository de Category

    @InjectMocks
    private QuizServiceImpl quizService;  // La classe à tester

    private Quiz quiz;
    private Category category;

    @BeforeEach
    public void setUp() {
        // Initialisation de la catégorie et du quiz pour les tests
        category = new Category();
        category.setCatid(1);
        category.setTitle("Test Category");

        quiz = new Quiz();
        quiz.setQuizid(1);
        quiz.setTitle("Test Quiz");
        quiz.setCategory(category);
        quiz.setActive(true);
    }

    @Test
    public void testAddQuiz() {
        // Arrange
        when(quizRepo.save(any(Quiz.class))).thenReturn(quiz);

        // Act
        Quiz result = quizService.addQuiz(quiz);

        // Assert
        assertNotNull(result);
        assertEquals(quiz.getQuizid(), result.getQuizid());
        assertEquals(quiz.getTitle(), result.getTitle());
        verify(quizRepo, times(1)).save(quiz);
    }

    @Test
    public void testUpdateQuiz() {
        // Arrange
        when(quizRepo.save(any(Quiz.class))).thenReturn(quiz);

        // Act
        Quiz result = quizService.updateQuiz(quiz);

        // Assert
        assertNotNull(result);
        assertEquals(quiz.getQuizid(), result.getQuizid());
        assertEquals(quiz.getTitle(), result.getTitle());
        verify(quizRepo, times(1)).save(quiz);
    }



    @Test
    public void testGetQuiz() {
        // Arrange
        when(quizRepo.findById(1)).thenReturn(Optional.of(quiz));

        // Act
        Quiz result = quizService.getQuiz(1);

        // Assert
        assertNotNull(result);
        assertEquals(quiz.getQuizid(), result.getQuizid());
        assertEquals(quiz.getTitle(), result.getTitle());
    }

    @Test
    public void testGetQuizNotFound() {
        // Arrange
        when(quizRepo.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> quizService.getQuiz(999));
    }

    @Test
    public void testDeleteQuiz() throws Exception {
        // Arrange
        when(quizRepo.findById(1)).thenReturn(Optional.of(quiz));

        // Act
        quizService.deleteQuiz(1);

        // Assert
        verify(quizRepo, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteQuizNotFound() throws Exception {
        // Arrange
        when(quizRepo.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CustomException.class, () -> quizService.deleteQuiz(999));
    }



    @Test
    public void testGetActiveQuizzes() {
        // Arrange
        Set<Quiz> quizzesSet = new HashSet<>();
        quizzesSet.add(quiz);
        when(quizRepo.findByActive(true)).thenReturn(quizzesSet);

        // Act
        Set<Quiz> result = quizService.getActiveQuizzes(true);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.contains(quiz));
    }

    @Test
    public void testGetActiveQuizzesOfCategory() {
        // Arrange
        Set<Quiz> quizzesSet = new HashSet<>();
        quizzesSet.add(quiz);
        when(catRepo.getById(1)).thenReturn(category);
        when(quizRepo.findByCategoryAndActive(category, true)).thenReturn(quizzesSet);

        // Act
        Set<Quiz> result = quizService.getActiveQuizzesOfCategory(1, true);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.contains(quiz));
    }
}
