package main.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import main.controllers.signUpController;


public class SignUpControllerTest {
    
    @Test
    public void testValidateEmail_ValidEmail() {
        assertTrue(signUpController.validateEmail("test@example.com"));
        assertTrue(signUpController.validateEmail("user@sub.example.com"));
    }

    @Test
    public void testValidateEmail_InvalidEmail() {
        assertFalse(signUpController.validateEmail("plainaddress")); // Missing @
        assertFalse(signUpController.validateEmail("@no-local-part.com")); // Missing local part
        assertFalse(signUpController.validateEmail("user@.com")); // Missing domain
        assertFalse(signUpController.validateEmail("user@com")); // Missing top-level domain
        assertFalse(signUpController.validateEmail("user@example.com.")); // Trailing dot
    }

    @Test
    public void testValidatePassword_ValidPassword() {
        assertTrue(signUpController.validatePassword("Password1!")); // Contains uppercase, digit, and special character
        assertTrue(signUpController.validatePassword("P@ssw0rd")); // Contains uppercase, digit, and special character
        assertTrue(signUpController.validatePassword("1Qaz@wsx")); // Contains uppercase, digit, and special character
        assertTrue(signUpController.validatePassword("A1b2C3d4@")); // Contains uppercase, digit, and special character
    }

    @Test
    public void testValidatePassword_InvalidPassword() {
        assertFalse(signUpController.validatePassword("password")); // No uppercase, digit, or special character
        assertFalse(signUpController.validatePassword("PASSWORD1")); // No special character
        assertFalse(signUpController.validatePassword("Password!")); // No digit
        assertFalse(signUpController.validatePassword("Pass1")); // Too short (less than 8 characters)
        assertFalse(signUpController.validatePassword("12345678")); // No uppercase or special character
        assertFalse(signUpController.validatePassword("P@ss")); // Too short (less than 8 characters)
    }
}
