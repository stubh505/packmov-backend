package com.deloitte.packmov.models.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

/**
 * This is a model and document class for Quote requests
 */
@Document(collection = "quote-request")
public class Quote {
    public enum Location {
        DELHI, KOLKATA, HYDERABAD, MUMBAI, BANGALORE, CHENNAI
    }

    @Id
    private String id;
    private Location location;
    private String from;
    private String to;
    private LocalDate on;
    private String name;
    private String contact;
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public LocalDate getOn() {
        return on;
    }

    public void setOn(LocalDate on) {
        this.on = on;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("Quote : {id : %s, name : %s, contact : %s, from : %s, to : %s}",
                id, name, contact, from, to);
    }
}
