package com.eminencetechnologies.firebaseconnectconsole;

public class PostModel {
    public String title;
    public String image;
    public String description;
    public PostModel(){}

    public PostModel(String postTitle, String url, String postDesc) {
        this.title = postTitle;
        this.image = url;
        this.description = postDesc;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }
}
