package com.controller;

import com.exception.CustomException;
import com.model.outcome.Result;
import com.service.ResultService;
import com.service.QuizService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

public class ResultControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ResultController resultController;

    @Mock
    private ResultService resultService;

    @Mock
    private QuizService quizService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(resultController).build();
    }

    @Test
    public void testGetCurrentResult() throws Exception {
        // Given
        Result result = new Result();
        result.setUid(1);
        result.setQuizid(101);
        result.setNumofquestions(10);
        // Simuler le comportement de resultService.getCurrentResult()
        when(resultService.getCurrentResult()).thenReturn(result);

        // When & Then
        mockMvc.perform(get("/result/current"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uid").value(1))
                .andExpect(jsonPath("$.quizid").value(101))
                .andExpect(jsonPath("$.numofquestions").value(10));

        // Vérifier que la méthode getCurrentResult a bien été appelée une fois
        verify(resultService, times(1)).getCurrentResult();
    }

    @Test
    public void testGetAllResultsOfUser() throws Exception {
        // Given
        Result result1 = new Result();
        result1.setUid(1);
        result1.setQuizid(101);
        Result result2 = new Result();
        result2.setUid(1);
        result2.setQuizid(102);
        List<Result> resultList = Arrays.asList(result1, result2);
        // Simuler le comportement de resultService.getResultsofUser()
        when(resultService.getResultsofUser(1)).thenReturn(resultList);

        // When & Then
        mockMvc.perform(get("/result/all/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].uid").value(1))
                .andExpect(jsonPath("$[0].quizid").value(101))
                .andExpect(jsonPath("$[1].uid").value(1))
                .andExpect(jsonPath("$[1].quizid").value(102));

        // Vérifier que la méthode getResultsofUser a bien été appelée une fois avec l'uid 1
        verify(resultService, times(1)).getResultsofUser(1);
    }

    @Test
    public void testDeleteAllResultsOfUser() throws Exception {
        // Given
        doNothing().when(resultService).deleteAllResultOfUser(1);

        // When & Then
        mockMvc.perform(delete("/result/deleteall/1"))
                .andExpect(status().isOk());

        // Vérifier que la méthode deleteAllResultOfUser a bien été appelée une fois avec l'uid 1
        verify(resultService, times(1)).deleteAllResultOfUser(1);
    }

    @Test
    public void testDeleteResult() throws Exception {
        // Given
        doNothing().when(resultService).deleteResult(101);

        // When & Then
        mockMvc.perform(delete("/result/delete/101"))
                .andExpect(status().isOk());

        // Vérifier que la méthode deleteResult a bien été appelée une fois avec l'ID du résultat 101
        verify(resultService, times(1)).deleteResult(101);
    }
}
