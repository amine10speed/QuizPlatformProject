package com.service.impl;

import com.model.exam.Question;
import com.model.exam.Quiz;
import com.model.outcome.Result;
import com.model.outcome.Userqna;
import com.repo.ResultRepo;
import com.service.QuestionService;
import com.service.QuizService;
import com.exception.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ResultServiceImplTest {

    @Mock
    private ResultRepo resultRepo;

    @Mock
    private QuestionService questionService;

    @Mock
    private QuizService quizService;

    @InjectMocks
    private ResultServiceImpl resultService;

    private Result result;
    private Quiz quiz;
    private Question question;
    private Userqna userqna;

    @BeforeEach
    public void setUp() {
        // Initialisation des objets nécessaires
        quiz = new Quiz();
        quiz.setQuizid(1);
        quiz.setTitle("Test Quiz");

        question = new Question();
        question.setQuesid(1);
        question.setAnswer("Test Answer");

        userqna = new Userqna();
        userqna.setQuesid(1);
        userqna.setAnswer("Test Answer");

        result = new Result();
        result.setQuizid(1);
        result.setUid(1);
        result.setNumofquestions(5);
        result.setUserqnas(new ArrayList<>());
        result.getUserqnas().add(userqna);
    }


    @Test
    public void testGetResults() {
        // Arrange
        List<Result> results = new ArrayList<>();
        results.add(result);
        when(resultRepo.findAll()).thenReturn(results);

        // Act
        List<Result> resultList = resultService.getResults();

        // Assert
        assertNotNull(resultList);
        assertEquals(1, resultList.size());
        assertTrue(resultList.contains(result));
    }

    @Test
    public void testGetResult() {
        // Arrange
        when(resultRepo.findById(1)).thenReturn(Optional.of(result));

        // Act
        Result resultFetched = resultService.getResult(1);

        // Assert
        assertNotNull(resultFetched);
        assertEquals(1, resultFetched.getQuizid());
    }

    @Test
    public void testGetCurrentResult() throws CustomException {
        // Arrange
        when(resultRepo.findCurrentResult()).thenReturn(result);
        when(quizService.getQuiz(1)).thenReturn(quiz);

        // Act
        Result currentResult = resultService.getCurrentResult();

        // Assert
        assertNotNull(currentResult);
        assertEquals("Test Quiz", currentResult.getTitle());
    }

    @Test
    public void testDeleteResult() {
        // Arrange
        doNothing().when(resultRepo).deleteById(1);

        // Act
        resultService.deleteResult(1);

        // Assert
        verify(resultRepo, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteAllResults() {
        // Arrange
        doNothing().when(resultRepo).deleteAll();

        // Act
        resultService.deleteAllResult();

        // Assert
        verify(resultRepo, times(1)).deleteAll();
    }

    @Test
    public void testDeleteAllResultsOfUser() {
        // Arrange
        doNothing().when(resultRepo).deleteByUid(1);

        // Act
        resultService.deleteAllResultOfUser(1);

        // Assert
        verify(resultRepo, times(1)).deleteByUid(1);
    }

    @Test
    public void testGetResultsofUser() throws CustomException {
        // Arrange
        List<Result> results = new ArrayList<>();
        results.add(result);
        when(resultRepo.findByUid(1)).thenReturn(results);
        when(quizService.getQuiz(1)).thenReturn(quiz);

        // Act
        List<Result> userResults = resultService.getResultsofUser(1);

        // Assert
        assertNotNull(userResults);
        assertEquals(1, userResults.size());
        assertEquals("Test Quiz", userResults.get(0).getTitle());
    }


}
