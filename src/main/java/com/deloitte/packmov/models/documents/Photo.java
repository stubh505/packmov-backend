package com.deloitte.packmov.models.documents;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "photos")
public class Photo {
    @Id
    private String id;
    private String title;
    private String type;
    private Binary image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Binary getImage() {
        return image;
    }

    public void setImage(Binary image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return String.format("Photo : {id : %s, type : %s, title : %s}", id, type, title);
    }
}