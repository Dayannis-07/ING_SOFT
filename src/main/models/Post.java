package main.models;

public class Post {
    private int id;
    private String path;
    private String title;
    private String date;
    private String place;
    private String description;
    private boolean aproved;

    public Post(int id, String path, String title, String date, String place, String description, boolean aproved) {
        this.path = path;
        this.title = title;
        this.date = date;
        this.place = place;
        this.description = description;
        this.aproved = aproved;
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public String getPath() {
        return path;
    }
    public String getTitle() {
        return title;
    }
    public String getDate() {
        return date;
    }
    public String getPlace() {
        return place;
    }
    public String getDescription() {
        return description;
    }
    public boolean isAproved() {
        return aproved;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setPlace(String place) {
        this.place = place;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setAproved(boolean aproved) {
        this.aproved = aproved;
    }
}