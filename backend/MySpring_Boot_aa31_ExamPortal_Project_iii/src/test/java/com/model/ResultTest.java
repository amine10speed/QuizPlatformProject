package com.model;

import com.model.outcome.Result;
import com.model.outcome.Userqna;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ResultTest {

    @Test
    public void testNoArgsConstructor() {
        // Testing no-args constructor
        Result result = new Result();
        assertNotNull(result);
    }

    @Test
    public void testAllArgsConstructor() {
        // Testing all-args constructor
        List<Userqna> userqnas = new ArrayList<>();
        Result result = new Result(
                1, // resultid
                101, // uid
                202, // quizid
                10, // numofquestions
                8, // correctanswers
                40, // marksgot
                9, // attempted
                "2024-12-29", // date
                "Sample Quiz", // title (transient)
                "image.png", // imagefile (transient)
                userqnas // userqnas list
        );

        assertEquals(1, result.getResultid());
        assertEquals(101, result.getUid());
        assertEquals(202, result.getQuizid());
        assertEquals(10, result.getNumofquestions());
        assertEquals(8, result.getCorrectanswers());
        assertEquals(40, result.getMarksgot());
        assertEquals(9, result.getAttempted());
        assertEquals("2024-12-29", result.getDate());
        assertEquals("Sample Quiz", result.getTitle());
        assertEquals("image.png", result.getImagefile());
        assertEquals(userqnas, result.getUserqnas());
    }

    @Test
    public void testSettersAndGetters() {
        // Testing setters and getters
        Result result = new Result();
        List<Userqna> userqnas = new ArrayList<>();

        result.setResultid(2);
        result.setUid(102);
        result.setQuizid(203);
        result.setNumofquestions(15);
        result.setCorrectanswers(12);
        result.setMarksgot(60);
        result.setAttempted(14);
        result.setDate("2024-12-30");
        result.setTitle("Another Quiz");
        result.setImagefile("image2.png");
        result.setUserqnas(userqnas);

        assertEquals(2, result.getResultid());
        assertEquals(102, result.getUid());
        assertEquals(203, result.getQuizid());
        assertEquals(15, result.getNumofquestions());
        assertEquals(12, result.getCorrectanswers());
        assertEquals(60, result.getMarksgot());
        assertEquals(14, result.getAttempted());
        assertEquals("2024-12-30", result.getDate());
        assertEquals("Another Quiz", result.getTitle());
        assertEquals("image2.png", result.getImagefile());
        assertEquals(userqnas, result.getUserqnas());
    }

    @Test
    public void testAddUserqnas() {
        // Testing addition of userqnas
        Result result = new Result();
        List<Userqna> userqnas = new ArrayList<>();
        Userqna userqna1 = new Userqna();
        Userqna userqna2 = new Userqna();
        userqnas.add(userqna1);
        userqnas.add(userqna2);
        result.setUserqnas(userqnas);

        assertEquals(2, result.getUserqnas().size());
        assertTrue(result.getUserqnas().contains(userqna1));
        assertTrue(result.getUserqnas().contains(userqna2));
    }

    @Test
    public void testTransientFields() {
        // Testing transient fields
        Result result = new Result();
        result.setTitle("Quiz Title");
        result.setImagefile("quiz_image.png");

        assertEquals("Quiz Title", result.getTitle());
        assertEquals("quiz_image.png", result.getImagefile());
    }
}
