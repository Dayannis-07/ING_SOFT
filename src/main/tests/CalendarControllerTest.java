package main.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import main.controllers.CalendarController;
import main.models.Post;

public class CalendarControllerTest {
    @Test
    public void FilterByDayMonthTest() {
        CalendarController controller = new CalendarController(true);
        controller.addPost(new Post(0, "", "", "01/02/2025", "", "", true));
        controller.addPost(new Post(1, "", "", "02/02/2025", "", "", true));
        controller.addPost(new Post(2, "", "", "20/03/2025", "", "", true));

        ArrayList<Post> result = controller.getPostsByDate(1, 2);
        assertEquals(1, result.size());
        assertEquals(0, result.get(0).getId());
    }
}
