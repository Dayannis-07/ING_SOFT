package main.tests;

import static org.junit.Assert.*;

import javax.swing.*;
import org.junit.Test;
import main.controllers.createPostController;
import main.models.Post;

public class CreateEventControllerTest {
    @Test
    public void testIsValidDate_ValidDate() {
        assertTrue(createPostController.isValidDate("01/01/2025"));
    }

    @Test
    public void testIsValidDate_InvalidDate() {
        assertFalse(createPostController.isValidDate("32/01/2025"));
    }

    @Test
    public void testIsValidDate_InvalidFormat() {
        assertFalse(createPostController.isValidDate("2025/01/01"));
    }
}