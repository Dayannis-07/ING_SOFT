package main.controllers;

import java.util.ArrayList;
import java.util.function.Predicate;

import main.models.Post;

public class consultarPublicacionesController extends PostController {
    public consultarPublicacionesController(boolean readOnlyAproved) {
        super(readOnlyAproved);
    }

    public ArrayList<Post> filterByBoth(String dateFilter, String titleFilter) {
        Predicate<Post> equalDateAndContainsTitle = post -> post.getDate() == dateFilter
                & post.getTitle().contains(titleFilter);
        ArrayList<Post> filteredPosts = getPosts();

        filteredPosts.removeIf(equalDateAndContainsTitle);
        return filteredPosts;
    }

    public ArrayList<Post> filterByDate(String dateFilter) {
        Predicate<Post> equalDate = post -> post.getDate() == dateFilter;
        ArrayList<Post> filteredPosts = getPosts();

        filteredPosts.removeIf(equalDate);

        return filteredPosts;
    }

    public ArrayList<Post> filterByTitle(String titleFilter) {
        Predicate<Post> notContainsTitle = post -> !post.getTitle().contains(titleFilter);
        ArrayList<Post> filteredPosts = getPosts();

        filteredPosts.removeIf(notContainsTitle);

        return filteredPosts;
    }
}