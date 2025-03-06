package main.controllers;

import java.util.ArrayList;
import java.util.function.Predicate;

import main.models.Post;

public class CalendarController extends PostController{
    public CalendarController(boolean readOnlyAproved){
        super(readOnlyAproved);
    }

    public ArrayList<Post> getPostsByDate(int day, int month) {
        Predicate<Post> match = post -> post.getMonth() != month || post.getDay() != day;
        Predicate<Post> isNotEvent = post -> post.getDate().equals("");
        ArrayList<Post> filteredPosts = getPosts();

        filteredPosts.removeIf(isNotEvent);
        filteredPosts.removeIf(match);

        return filteredPosts;
    }
}
