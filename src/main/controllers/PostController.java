package main.controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Predicate;

import org.json.JSONArray;
import org.json.JSONObject;

import main.models.Post;

public class PostController {
    protected ArrayList<Post> posts;
    protected boolean readOnlyAproved;
    private int lastId;

    public PostController(boolean readOnlyAproved) {
        posts = new ArrayList<>();
        this.readOnlyAproved = readOnlyAproved;
        loadPosts();
    }

    public boolean canReadNotAproved() {
        return !readOnlyAproved;
    }

    public void loadPosts() {
        if (posts.size() != 0) {
            posts.clear();
        }
        JSONArray postsArray = new JSONArray(fileToString("posts.json"));

        for (int i = 0; i < postsArray.length(); i++) {
            JSONObject aux = postsArray.getJSONObject(i);
            Post post = new Post(
                    aux.getInt("id"),
                    aux.getString("Path"),
                    aux.getString("Title"),
                    aux.getString("Date"),
                    aux.getString("Place"),
                    aux.getString("Description"),
                    aux.getBoolean("Approved"));

            posts.add(post);
        }

        if(posts.size() == 0) {
            lastId = 0;
        } else {
            lastId = posts.get(posts.size() - 1).getId() + 1;
        }
    }

    public ArrayList<Post> getPosts() {
        Predicate<Post> notAproved = post -> post.isAproved() != readOnlyAproved;
        ArrayList<Post> filteredPosts = new ArrayList<>(posts);
        filteredPosts.removeIf(notAproved);
        return filteredPosts;
    }

    public void addPost(Post post) {
        post.setId(lastId);

        System.out.println(post.getTitle());
        posts.add(post);
    }

    public void savePosts() {
        JSONArray postsArray = new JSONArray();
        for (Post post : posts) {
            JSONObject jsonPost = new JSONObject();

            jsonPost.put("Title", post.getTitle());
            jsonPost.put("Place", post.getPlace());
            jsonPost.put("Date", post.getDate());
            jsonPost.put("Description", post.getDescription());
            jsonPost.put("Path", post.getPath());
            jsonPost.put("Approved", post.isAproved());
            jsonPost.put("id", post.getId());

            postsArray.put(jsonPost);
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("posts.json", false))) {
            bw.write(postsArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static String fileToString(String p) {

        // initializing the variable to
        // store the string
        String contents = "";

        // Instantiating the FileReader class
        try (FileReader f = new FileReader(p)) {

            // instantiating the BufferedReader class
            BufferedReader br = new BufferedReader(f);

            // to store the current line read by the
            // readLine () method
            String current = "";

            // looping till we find the null char
            while ((current = br.readLine()) != null)

                // storing the contents in string
                contents += current + "\n";
        }

        // catch block
        catch (IOException e) {

            // printing the error
            System.out.println("no conseguido");
            return "[]";
        }

        // returning the string
        return contents;
    }
}
