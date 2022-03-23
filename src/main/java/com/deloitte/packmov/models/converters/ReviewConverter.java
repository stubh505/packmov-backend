package com.deloitte.packmov.models.converters;

import com.deloitte.packmov.models.documents.Review;
import com.deloitte.packmov.models.dtos.ReviewDTO;

public class ReviewConverter {

    private ReviewConverter() {}

    public static Review convertToDoc(ReviewDTO dto) {
        Review review = new Review();
        review.setName(dto.getName());
        review.setContact(dto.getContact());
        review.setEmail(dto.getEmail());
        review.setDescription(dto.getDescription());
        review.setTitle(dto.getTitle());
        review.setImage(dto.getImage());
        review.setOrderId(dto.getOrderId());
        review.setRating(dto.getRating());
        review.setId(dto.getId());
        return review;
    }

    public static ReviewDTO convertToDto(Review review) {
        ReviewDTO dto = new ReviewDTO();
        dto.setName(review.getName());
        dto.setContact(review.getContact());
        dto.setEmail(review.getEmail());
        dto.setDescription(review.getDescription());
        dto.setTitle(review.getTitle());
        dto.setImage(review.getImage());
        dto.setOrderId(review.getOrderId());
        dto.setRating(review.getRating());
        dto.setId(review.getId());
        return dto;
    }
}
