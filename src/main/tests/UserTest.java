package main.tests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import main.models.User;

public class UserTest {
    @Test
    public void userConstructorTest(){
        User u = new User("name", "lastname", "email@example.com", "aA123456?", "Estudiante", false);
        
        assertEquals("name", u.getName());
        assertEquals("lastname", u.getLastName());
        assertEquals("email@example.com", u.getEmail());
        assertEquals("aA123456?", u.getPassword());
        assertEquals("Estudiante", u.getUserType());
        assertEquals(false, u.getIsAdmin());
    }
}
