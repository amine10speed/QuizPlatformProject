package com.model;

import static org.junit.jupiter.api.Assertions.*;

import com.model.exam.Category;
import com.model.exam.Question;
import com.model.exam.Quiz;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class QuizTest {

    @Test
    public void testQuizSettersAndGetters() {
        Quiz quiz = new Quiz();

        // Test Setters
        quiz.setQuizid(1);
        quiz.setTitle("Sample Quiz");
        quiz.setDescription("Sample Description");
        quiz.setMaxmarks(100);
        quiz.setNumofquestions(10);
        quiz.setImage("sample.jpg");
        quiz.setActive(true);

        Category category = new Category();
        quiz.setCategory(category);

        Set<Question> questions = new HashSet<>();
        quiz.setQuestions(questions);

        quiz.setImagefile("imagefile.jpg");

        // Test Getters
        assertEquals(1, quiz.getQuizid());
        assertEquals("Sample Quiz", quiz.getTitle());
        assertEquals("Sample Description", quiz.getDescription());
        assertEquals(100, quiz.getMaxmarks());
        assertEquals(10, quiz.getNumofquestions());
        assertEquals("sample.jpg", quiz.getImage());
        assertTrue(quiz.isActive());
        assertEquals(category, quiz.getCategory());
        assertEquals(questions, quiz.getQuestions());
        assertEquals("imagefile.jpg", quiz.getImagefile());
    }

    @Test
    public void testQuizConstructor() {
        Category category = new Category();
        Set<Question> questions = new HashSet<>();

        Quiz quiz = new Quiz(1, "Sample Quiz", "Sample Description", 100, 10, "sample.jpg", true, category, questions, "imagefile.jpg");

        assertEquals(1, quiz.getQuizid());
        assertEquals("Sample Quiz", quiz.getTitle());
        assertEquals("Sample Description", quiz.getDescription());
        assertEquals(100, quiz.getMaxmarks());
        assertEquals(10, quiz.getNumofquestions());
        assertEquals("sample.jpg", quiz.getImage());
        assertTrue(quiz.isActive());
        assertEquals(category, quiz.getCategory());
        assertEquals(questions, quiz.getQuestions());
        assertEquals("imagefile.jpg", quiz.getImagefile());
    }
}
