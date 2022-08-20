package com.eminencetechnologies.firebaseconnect;

public class Model {

    String title, image, description;

    public Model() {}

    //constructor
    public Model(String title, String image, String description){
        this.title = title;
        this.image = image;
        this.description = description;
    }

    //getter and setters press Alt+Insert

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
