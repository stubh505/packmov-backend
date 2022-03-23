package com.deloitte.packmov.models.dtos;

import com.deloitte.packmov.models.documents.Quote;

import java.time.LocalDate;

public class QuoteDTO {
    private String id;
    private Quote.Location location;
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

    public Quote.Location getLocation() {
        return location;
    }

    public void setLocation(Quote.Location location) {
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
