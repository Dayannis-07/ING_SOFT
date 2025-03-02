package main;

import org.json.JSONObject;

public class Main {
    public static void main(String args[]){
       JSONObject jo = new JSONObject("{ \"abc\" : \"def\" }");
       System.out.println(jo);
    }
}
