package com.model;

import com.model.exam.Question;
import com.model.exam.Quiz;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionTest {

    @Test
    public void testNoArgsConstructor() {
        // Testing no-args constructor
        Question question = new Question();
        assertNotNull(question);
    }

    @Test
    public void testAllArgsConstructor() {
        // Testing all-args constructor
        Quiz quiz = new Quiz();
        Question question = new Question(
                1,
                "What is Java?",
                "image.png",
                "Programming Language",
                "Game",
                "Country",
                "Animal",
                "Programming Language",
                quiz
        );

        assertEquals(1, question.getQuesid());
        assertEquals("What is Java?", question.getContent());
        assertEquals("image.png", question.getImage());
        assertEquals("Programming Language", question.getOption1());
        assertEquals("Game", question.getOption2());
        assertEquals("Country", question.getOption3());
        assertEquals("Animal", question.getOption4());
        assertEquals("Programming Language", question.getAnswer());
        assertEquals(quiz, question.getQuiz());
    }

    @Test
    public void testSettersAndGetters() {
        // Testing setters and getters
        Question question = new Question();
        question.setQuesid(2);
        question.setContent("What is Python?");
        question.setImage("image2.png");
        question.setOption1("Programming Language");
        question.setOption2("Game");
        question.setOption3("Country");
        question.setOption4("Animal");
        question.setAnswer("Programming Language");

        Quiz quiz = new Quiz();
        question.setQuiz(quiz);

        assertEquals(2, question.getQuesid());
        assertEquals("What is Python?", question.getContent());
        assertEquals("image2.png", question.getImage());
        assertEquals("Programming Language", question.getOption1());
        assertEquals("Game", question.getOption2());
        assertEquals("Country", question.getOption3());
        assertEquals("Animal", question.getOption4());
        assertEquals("Programming Language", question.getAnswer());
        assertEquals(quiz, question.getQuiz());
    }

    @Test
    public void testSetQuiz() {
        // Testing setting and getting quiz
        Question question = new Question();
        Quiz quiz = new Quiz();
        question.setQuiz(quiz);

        assertEquals(quiz, question.getQuiz());
    }

    @Test
    public void testSetAnswer() {
        // Testing setting and getting answer
        Question question = new Question();
        question.setAnswer("Answer");

        assertEquals("Answer", question.getAnswer());
    }

    @Test
    public void testSetContent() {
        // Testing setting and getting content
        Question question = new Question();
        question.setContent("Sample question");

        assertEquals("Sample question", question.getContent());
    }
}
