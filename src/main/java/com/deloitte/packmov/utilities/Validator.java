package com.deloitte.packmov.utilities;

import com.deloitte.packmov.exceptions.InvalidQuoteException;
import com.deloitte.packmov.exceptions.InvalidReviewException;
import com.deloitte.packmov.exceptions.InvalidTicketException;
import com.deloitte.packmov.models.documents.Quote;
import com.deloitte.packmov.models.documents.Review;
import com.deloitte.packmov.models.documents.Ticket;

import java.time.LocalDate;

/**
 * Validator class for validating request bodies
 */
public class Validator {
    public static final String INVALID_NAME = "Invalid name. Should be non-empty and less than 255 chars";
    public static final String INVALID_CONTACT = "Invalid contact. Should be non-empty and 10 digits starting with 6-9";
    public static final String INVALID_EMAIL = "Invalid email. Should be non-empty and valid";
    public static final String INVALID_DATE = "Invalid date. Should be non-empty and in future within a year";
    public static final String INVALID_DESCRIPTION = "Invalid description. Should be non-empty";
    public static final String INVALID_RATING = "Invalid rating selected. Should be between 1 to 5";

    private Validator() {}

    /**
     * Validates the new {@linkplain Review} request
     * @param review the request body
     * @throws InvalidReviewException if request is invalid
     */
    public static void validate(Review review) {
        if (isNameInvalid(review.getName())) {
            throw new InvalidReviewException(INVALID_NAME);
        }
        if (!(review.getContact() == null || review.getContact().isEmpty()) && isContactInvalid(review.getContact())) {
            throw new InvalidReviewException(INVALID_CONTACT);
        }
        if (isEmailInvalid(review.getEmail())) {
            throw new InvalidReviewException(INVALID_EMAIL);
        }
        if (review.getDescription() == null || review.getDescription().isEmpty()) {
            throw new InvalidReviewException(INVALID_DESCRIPTION);
        }
        if (review.getRating() == null || review.getRating() < 1 || review.getRating() > 5) {
            throw new InvalidReviewException(INVALID_RATING);
        }
    }

    /**
     * Validates the new {@linkplain Ticket} request
     * @param ticket the request body
     * @throws InvalidTicketException if request is invalid
     */
    public static void validate(Ticket ticket) {
        if (isNameInvalid(ticket.getName())) {
            throw new InvalidTicketException(INVALID_NAME);
        }
        if (isContactInvalid(ticket.getContact())) {
            throw new InvalidTicketException(INVALID_CONTACT);
        }
        if (isEmailInvalid(ticket.getEmail())) {
            throw new InvalidTicketException(INVALID_EMAIL);
        }
        if (ticket.getDescription() == null || ticket.getDescription().isEmpty()) {
            throw new InvalidTicketException(INVALID_DESCRIPTION);
        }
    }

    /**
     * Validates the new {@linkplain Quote} request
     * @param quote the request body
     * @throws InvalidQuoteException if request is invalid
     */
    public static void validate(Quote quote) {
        if (isNameInvalid(quote.getName())) {
            throw new InvalidQuoteException(INVALID_NAME);
        }
        if (isContactInvalid(quote.getContact())) {
            throw new InvalidQuoteException(INVALID_CONTACT);
        }
        if (!(quote.getEmail() == null || quote.getEmail().isEmpty()) && isEmailInvalid(quote.getEmail())) {
            throw new InvalidQuoteException(INVALID_EMAIL);
        }
        if (isDateInvalid(quote.getOn())) {
            throw new InvalidQuoteException(INVALID_DATE);
        }
    }

    private static boolean isNameInvalid(String name) {
        return name == null || name.isEmpty() || name.length() > 255;
    }

    private static boolean isContactInvalid(String contact) {
        return contact == null || !contact.matches("^[6-9][0-9]{9}$");
    }

    private static boolean isEmailInvalid(String email) {
        return email == null || !email.matches("^\\S+@\\S+\\.\\S+$");
    }

    private static boolean isDateInvalid(LocalDate date) {
        return date == null || date.isBefore(LocalDate.now().plusDays(1)) || date.isAfter(LocalDate.now().plusYears(1));
    }
}
