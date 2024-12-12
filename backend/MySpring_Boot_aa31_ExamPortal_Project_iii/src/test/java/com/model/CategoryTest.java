package com.model;

import com.model.exam.Category;
import com.model.exam.Quiz;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashSet;
import java.util.Set;

public class CategoryTest {

    @Test
    public void testNoArgsConstructor() {
        // Testing no-args constructor
        Category category = new Category();
        assertNotNull(category);
    }

    @Test
    public void testAllArgsConstructor() {
        // Testing all-args constructor
        Set<Quiz> quizzes = new LinkedHashSet<>();
        quizzes.add(new Quiz());
        Category category = new Category(1, "Science", "Science quizzes", quizzes);

        assertEquals(1, category.getCatid());
        assertEquals("Science", category.getTitle());
        assertEquals("Science quizzes", category.getDescription());
        assertEquals(1, category.getQuizzes().size());
    }

    @Test
    public void testSettersAndGetters() {
        // Testing setters and getters
        Category category = new Category();
        category.setCatid(2);
        category.setTitle("Math");
        category.setDescription("Math quizzes");

        Quiz quiz = new Quiz();
        Set<Quiz> quizzes = new LinkedHashSet<>();
        quizzes.add(quiz);

        category.setQuizzes(quizzes);

        assertEquals(2, category.getCatid());
        assertEquals("Math", category.getTitle());
        assertEquals("Math quizzes", category.getDescription());
        assertEquals(1, category.getQuizzes().size());
        assertTrue(category.getQuizzes().contains(quiz));
    }

    @Test
    public void testAddQuiz() {
        // Testing adding quizzes to the set
        Category category = new Category();
        Quiz quiz1 = new Quiz();
        Quiz quiz2 = new Quiz();

        Set<Quiz> quizzes = category.getQuizzes();
        quizzes.add(quiz1);
        quizzes.add(quiz2);

        category.setQuizzes(quizzes);

        assertEquals(2, category.getQuizzes().size());
        assertTrue(category.getQuizzes().contains(quiz1));
        assertTrue(category.getQuizzes().contains(quiz2));
    }

    @Test
    public void testRemoveQuiz() {
        // Testing removing quizzes from the set
        Category category = new Category();
        Quiz quiz = new Quiz();

        Set<Quiz> quizzes = category.getQuizzes();
        quizzes.add(quiz);
        category.setQuizzes(quizzes);

        assertEquals(1, category.getQuizzes().size());

        category.getQuizzes().remove(quiz);
        assertEquals(0, category.getQuizzes().size());
    }
}
