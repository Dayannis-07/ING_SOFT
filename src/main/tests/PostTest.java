package main.tests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import main.models.Post;

public class PostTest {
    @Test
    public void postConstructorTest(){
        Post p = new Post(0, "path", "title", "dd/mm/yyyy", "place", "description", true);

        assertEquals(0, p.getId());
        assertEquals("path", p.getPath());
        assertEquals("title", p.getTitle());
        assertEquals("dd/mm/yyyy", p.getDate());
        assertEquals("place", p.getPlace());
        assertEquals("description", p.getDescription());
        assertEquals(true, p.isAproved());
    }
}