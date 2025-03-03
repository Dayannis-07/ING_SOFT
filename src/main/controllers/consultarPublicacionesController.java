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

public class consultarPublicacionesController {
    private ArrayList<Post> posts;
    private boolean readOnlyAproved;

    public consultarPublicacionesController(boolean readOnlyAproved) {
        posts = new ArrayList<>();
        this.readOnlyAproved = readOnlyAproved;
        loadPosts(readOnlyAproved);
    }

    public boolean canReadNotAproved(){
        return !readOnlyAproved;
    }

    public void loadPosts(boolean onlyAproved) {
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

            if(onlyAproved && !post.isAproved()){
                continue;
            }
            posts.add(post);
        }
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public ArrayList<Post> filterByBoth(String dateFilter, String titleFilter) {
        Predicate<Post> equalDateAndContainsTitle = post -> post.getDate() == dateFilter & post.getTitle().contains(titleFilter);
        ArrayList<Post> filteredPosts = new ArrayList<>(posts);

        filteredPosts.removeIf(equalDateAndContainsTitle);
        return filteredPosts;
    }

    public ArrayList<Post> filterByDate(String dateFilter) {
        Predicate<Post> equalDate = post -> post.getDate() == dateFilter;
        ArrayList<Post> filteredPosts = new ArrayList<>(posts);

        filteredPosts.removeIf(equalDate);

        return filteredPosts;
    }

    public ArrayList<Post> filterByTitle(String titleFilter) {
        Predicate<Post> notContainsTitle = post -> !post.getTitle().contains(titleFilter);
        ArrayList<Post> filteredPosts = new ArrayList<>(posts);

        filteredPosts.removeIf(notContainsTitle);

        return filteredPosts;
    }

    public static void checkPost(String title) {
        JSONArray postsArray = new JSONArray(fileToString("posts.json"));

        for (int i = 0; i < postsArray.length(); i++) {
            JSONObject post = postsArray.getJSONObject(i);

            if (!title.equals(post.getString("Title")))
                continue;

            post.put("Approved", true);

            postsArray.put(i, post);
            break;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("posts.json", false))) {
            bw.write(postsArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void denyPost(String title) {
        JSONArray postsArray = new JSONArray(fileToString("posts.json"));

        for (int i = 0; i < postsArray.length(); i++) {
            JSONObject post = postsArray.getJSONObject(i);

            if (!title.equals(post.getString("Title")))
                continue;

            postsArray.remove(i);
            break;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("posts.json", false))) {
            bw.write(postsArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String fileToString(String p) {

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