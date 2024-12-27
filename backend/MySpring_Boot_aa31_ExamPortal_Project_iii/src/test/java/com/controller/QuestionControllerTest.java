package com.controller;

import com.model.exam.Question;
import com.model.exam.Quiz;
import com.service.QuestionService;
import com.service.QuizService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuestionControllerTest {

    @Mock
    private QuestionService questionService;

    @Mock
    private QuizService quizService;

    @InjectMocks
    private QuestionController questionController;

    private MockMvc mockMvc;

    private Question question;
    private Quiz quiz;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(questionController).build();

        // Initialiser un objet Question et Quiz pour les tests
        question = new Question();
        question.setQuesid(1);
        question.setContent("What is Java?");
        question.setOption1("Programming Language");
        question.setOption2("Coffee");
        question.setOption3("Island");
        question.setOption4("Animal");
        question.setAnswer("Programming Language");

        quiz = new Quiz();
        quiz.setQuizid(1);
        quiz.setTitle("Java Basics");
        quiz.setNumofquestions(5);

        // Lier la question au quiz
        question.setQuiz(quiz);

        // Ajouter la question à un ensemble de questions du quiz
        Set<Question> questions = new HashSet<>();
        questions.add(question);
        quiz.setQuestions(questions);
    }

    @Test
    void addQuestion_ShouldReturnQuestion_WhenQuestionIsValid() {
        // Arrange
        when(questionService.addQuestion(any(Question.class))).thenReturn(question);

        // Act
        ResponseEntity<?> response = questionController.addQuestion(question);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Question createdQuestion = (Question) response.getBody();
        assertNotNull(createdQuestion);
        assertEquals(question.getQuesid(), createdQuestion.getQuesid());
        assertEquals(question.getContent(), createdQuestion.getContent());
    }

    @Test
    void updateQuestion_ShouldReturnUpdatedQuestion_WhenQuestionIsValid() {
        // Arrange
        question.setContent("Updated Question");
        when(questionService.addQuestion(any(Question.class))).thenReturn(question); // Utiliser la bonne méthode

        // Act
        ResponseEntity<?> response = questionController.upadteQuestion(question);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Question updatedQuestion = (Question) response.getBody();
        assertNotNull(updatedQuestion);
        assertEquals("Updated Question", updatedQuestion.getContent());
    }

    @Test
    void getQuestionsOfQuiz_ShouldReturnShuffledQuestions_WhenQuizIdIsValid() {
        // Arrange
        when(quizService.getQuiz(1)).thenReturn(quiz);
        when(questionService.getQuestions()).thenReturn(Set.of(question));

        // Act
        ResponseEntity<?> response = questionController.getQuestionsOfQuiz(1);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Set<Question> questions = (Set<Question>) response.getBody();
        assertNotNull(questions);
        assertTrue(questions.contains(question)); // Vérifier que la question est présente
    }

    @Test
    void getAllQuestions_ShouldReturnAllQuestions() {
        // Arrange
        Set<Question> allQuestions = Set.of(question);
        when(questionService.getQuestions()).thenReturn(allQuestions);

        // Act
        ResponseEntity<?> response = questionController.getAllQuestions();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Set<Question> returnedQuestions = (Set<Question>) response.getBody();
        assertEquals(1, returnedQuestions.size());
    }

    @Test
    void deleteQuestion_ShouldInvokeDeleteService_WhenValidId() throws Exception {
        // Arrange
        doNothing().when(questionService).deleteQuestion(1);

        // Act
        questionController.delete(1);

        // Assert
        verify(questionService, times(1)).deleteQuestion(1);
    }


}
