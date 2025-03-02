package Controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONObject;

public class consultarPublicacionesController {
    public static ArrayList<String[]> getPosts(boolean Approved){
        JSONArray postsArray = new JSONArray(fileToString("posts.json"));
        ArrayList<String[]> posts = new ArrayList<>();

        for (int i = 0; i < postsArray.length(); i++){
            JSONObject aux = postsArray.getJSONObject(i);
            if (aux.getBoolean("Approved") != Approved) continue;
            String[] s = {aux.getString("Title"), aux.getString("Date"), aux.getString("Place")};
            posts.add(s);
        }

        return posts;
    }

    public static void checkPost(String title){
        JSONArray postsArray = new JSONArray(fileToString("posts.json"));
        
        for (int i = 0; i < postsArray.length(); i++){
            JSONObject post = postsArray.getJSONObject(i);

            if (!title.equals(post.getString("Title"))) continue;

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

    public static void denyPost(String title){
        JSONArray postsArray = new JSONArray(fileToString("posts.json"));

        for (int i = 0; i < postsArray.length(); i++){
            JSONObject post = postsArray.getJSONObject(i);
            
            if (!title.equals(post.getString("Title"))) continue;

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
            e.printStackTrace(); 
        } 
  
        // returning the string 
        return contents; 
    }
}