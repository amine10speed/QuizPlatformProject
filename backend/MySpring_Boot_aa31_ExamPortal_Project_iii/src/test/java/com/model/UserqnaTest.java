package com.model;

import com.model.outcome.Result;
import com.model.outcome.Userqna;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserqnaTest {

    @Test
    public void testNoArgsConstructor() {
        // Testing no-args constructor
        Userqna userqna = new Userqna();
        assertNotNull(userqna);
    }

    @Test
    public void testAllArgsConstructor() {
        // Testing all-args constructor
        Result result = new Result();
        Userqna userqna = new Userqna(
                1, // uqna
                101, // quesid
                "Answer 1", // answer
                result // result
        );

        assertEquals(1, userqna.getUqna());
        assertEquals(101, userqna.getQuesid());
        assertEquals("Answer 1", userqna.getAnswer());
        assertEquals(result, userqna.getResult());
    }

    @Test
    public void testSettersAndGetters() {
        // Testing setters and getters
        Userqna userqna = new Userqna();
        Result result = new Result();

        userqna.setUqna(2);
        userqna.setQuesid(202);
        userqna.setAnswer("Answer 2");
        userqna.setResult(result);

        assertEquals(2, userqna.getUqna());
        assertEquals(202, userqna.getQuesid());
        assertEquals("Answer 2", userqna.getAnswer());
        assertEquals(result, userqna.getResult());
    }

    @Test
    public void testResultRelationship() {
        // Testing the relationship with Result
        Result result = new Result();
        Userqna userqna = new Userqna();
        userqna.setResult(result);

        assertNotNull(userqna.getResult());
        assertEquals(result, userqna.getResult());
    }

    @Test
    public void testJsonBackReference() {
        // Testing JsonBackReference behavior
        Result result = new Result();
        Userqna userqna = new Userqna();
        userqna.setResult(result);

        assertNotNull(userqna.getResult());
        assertEquals(result, userqna.getResult());
    }
}
