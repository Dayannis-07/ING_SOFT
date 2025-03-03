package main.controllers;

import java.util.function.Predicate;

import main.models.Post;

public class verifyPostController extends PostController {
    public verifyPostController(boolean readOnlyAproved) {
        super(readOnlyAproved);
    }

    public void checkPost(int id) {
        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).getId() == id) {
                posts.get(i).setAproved(true);
            }
        }
        savePosts();
    }

    public void denyPost(int id) {
        Predicate<Post> isId = post -> post.getId() == id;
        posts.removeIf(isId);
        savePosts();
    }
}
