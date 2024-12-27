package com.controller;

import com.exception.CustomException;
import com.model.exam.Quiz;
import com.service.CategoryService;
import com.service.QuizService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class QuizControllerTest {

    private MockMvc mockMvc;

    @Mock
    private QuizService quizService;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private QuizController quizController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(quizController).build();
    }

    @Test
    void testAddQuiz() throws Exception {
        String title = "Sample Quiz";
        String description = "Sample Description";
        String maxMarks = "100";
        String numOfQuestions = "10";
        String categoryId = "1";
        MockMultipartFile file = new MockMultipartFile("imagefile", "test.jpg", "image/jpeg", new byte[]{});

        Quiz mockQuiz = new Quiz();
        mockQuiz.setTitle(title);
        mockQuiz.setDescription(description);

        when(quizService.addQuiz(Mockito.any(Quiz.class))).thenReturn(mockQuiz);

        mockMvc.perform(post("/quiz/create")
                        .param("title", title)
                        .param("description", description)
                        .param("maxmarks", maxMarks)
                        .param("numofquestions", numOfQuestions)
                        .param("catid", categoryId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.description").value(description));

        verify(quizService, times(1)).addQuiz(Mockito.any(Quiz.class));
    }

    @Test
    void testUpdateQuiz() throws Exception {
        String quizId = "1";
        String title = "Updated Quiz";
        String description = "Updated Description";
        String maxMarks = "120";
        String numOfQuestions = "15";
        String categoryId = "2";
        MockMultipartFile file = new MockMultipartFile("imagefile", "updated.jpg", "image/jpeg", new byte[]{});

        Quiz mockQuiz = new Quiz();
        mockQuiz.setQuizid(1);
        mockQuiz.setTitle(title);
        mockQuiz.setDescription(description);

        when(quizService.getQuiz(1)).thenReturn(mockQuiz);
        when(quizService.addQuiz(Mockito.any(Quiz.class))).thenReturn(mockQuiz);

        mockMvc.perform(put("/quiz/update")
                        .param("quizid", quizId)
                        .param("title", title)
                        .param("description", description)
                        .param("maxmarks", maxMarks)
                        .param("numofquestions", numOfQuestions)
                        .param("catid", categoryId)
                        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.description").value(description));

        verify(quizService, times(1)).addQuiz(Mockito.any(Quiz.class));
    }




    @Test
    void testDeleteQuiz() throws Exception {
        int quizId = 1;
        Quiz mockQuiz = new Quiz();
        mockQuiz.setQuizid(quizId);
        mockQuiz.setImage("sample.jpg");

        when(quizService.getQuiz(quizId)).thenReturn(mockQuiz);
        doNothing().when(quizService).deleteQuiz(quizId);

        mockMvc.perform(delete("/quiz/delete/{quizId}", quizId))
                .andExpect(status().isOk());

        verify(quizService, times(1)).deleteQuiz(quizId);
    }

    @Test
    void testGetQuizzesOfCategory() throws Exception {
        int categoryId = 1;
        Set<Quiz> quizzes = new HashSet<>();
        quizzes.add(new Quiz());

        when(quizService.getQuizzesOfCategory(categoryId)).thenReturn(quizzes);

        mockMvc.perform(get("/quiz/quizzes/{categoryId}", categoryId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        verify(quizService, times(1)).getQuizzesOfCategory(categoryId);
    }

    @Test
    void testGetAllActiveQuizzes() throws Exception {
        Set<Quiz> quizzes = new HashSet<>();
        quizzes.add(new Quiz());

        when(quizService.getActiveQuizzes(true)).thenReturn(quizzes);

        mockMvc.perform(get("/quiz/allactive"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        verify(quizService, times(1)).getActiveQuizzes(true);
    }

    @Test
    void testGetActiveQuizzesOfCategory() throws Exception {
        int categoryId = 1;
        Set<Quiz> quizzes = new HashSet<>();
        quizzes.add(new Quiz());

        when(quizService.getActiveQuizzesOfCategory(categoryId, true)).thenReturn(quizzes);

        mockMvc.perform(get("/quiz/quizzesactive/{categoryId}", categoryId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        verify(quizService, times(1)).getActiveQuizzesOfCategory(categoryId, true);
    }

}
