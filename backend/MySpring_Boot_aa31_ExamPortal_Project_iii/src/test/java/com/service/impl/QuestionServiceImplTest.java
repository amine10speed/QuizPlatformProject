package com.service.impl;

import com.model.exam.Question;
import com.model.exam.Quiz;
import com.repo.QuestionRepo;
import com.repo.QuizRepo;
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
public class QuestionServiceImplTest {

    @Mock
    private QuestionRepo questionRepo;  // Simuler le repository de Question

    @Mock
    private QuizRepo quizRepo;  // Simuler le repository de Quiz

    @InjectMocks
    private QuestionServiceImpl questionService;  // La classe à tester

    private Question question;
    private Quiz quiz;

    @BeforeEach
    public void setUp() {
        // Initialisation de la question et du quiz pour les tests
        quiz = new Quiz();
        quiz.setQuizid(1);
        quiz.setTitle("Test Quiz");

        question = new Question();
        question.setQuesid(1);
        question.setContent("What is 2 + 2?");  // Remplacer "question" par "content"
        question.setAnswer("4");
        question.setQuiz(quiz);
    }

    @Test
    public void testAddQuestion() {
        // Arrange
        when(questionRepo.save(any(Question.class))).thenReturn(question);

        // Act
        Question result = questionService.addQuestion(question);

        // Assert
        assertNotNull(result);
        assertEquals(question.getQuesid(), result.getQuesid());
        assertEquals(question.getContent(), result.getContent());  // Utilisez "getContent()"
        verify(questionRepo, times(1)).save(question);
    }

    @Test
    public void testUpdateQuestion() {
        // Arrange
        when(questionRepo.save(any(Question.class))).thenReturn(question);

        // Act
        Question result = questionService.updateQuestion(question);

        // Assert
        assertNotNull(result);
        assertEquals(question.getQuesid(), result.getQuesid());
        assertEquals(question.getContent(), result.getContent());  // Utilisez "getContent()"
        verify(questionRepo, times(1)).save(question);
    }



    @Test
    public void testGetQuestion() {
        // Arrange
        when(questionRepo.findById(1)).thenReturn(Optional.of(question));

        // Act
        Question result = questionService.getQuestion(1);

        // Assert
        assertNotNull(result);
        assertEquals(question.getQuesid(), result.getQuesid());
        assertEquals(question.getContent(), result.getContent());  // Utilisez "getContent()"
    }

    @Test
    public void testGetQuestionNotFound() {
        // Arrange
        when(questionRepo.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> questionService.getQuestion(999));
    }

    @Test
    public void testDeleteQuestion() {
        // Act
        questionService.deleteQuestion(1);

        // Assert
        verify(questionRepo, times(1)).deleteById(1);
    }
}
