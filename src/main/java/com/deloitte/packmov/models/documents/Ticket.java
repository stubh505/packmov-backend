package com.deloitte.packmov.models.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * This is the model and document class for a service ticket
 */
@Document(collection = "service-ticket")
public class Ticket {
    @Id
    private String id;
    private String name;
    private String contact;
    private String email;
    private String description;
    private String details;
    private String image;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    public String getDetails() {
        return details;
    }

    public String getImage() {
        return image;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return String.format("Ticket : {id : %s, name : %s, contact : %s, email : %s}",
                id, name, contact, email);
    }
}
