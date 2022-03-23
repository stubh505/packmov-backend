package com.deloitte.packmov.services;

import com.deloitte.packmov.daos.ReviewDao;
import com.deloitte.packmov.exceptions.NotFoundException;
import com.deloitte.packmov.models.converters.ReviewConverter;
import com.deloitte.packmov.models.documents.Review;
import com.deloitte.packmov.models.dtos.ReviewDTO;
import com.deloitte.packmov.utilities.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for retrieving and adding {@linkplain Review}s
 */
@Service
public class ReviewService {
    @Autowired
    private ReviewDao reviewDao;

    /**
     * Retrieve {@linkplain Review} for provided ID
     * @param id the ID
     * @return the {@linkplain Review}
     * @throws NotFoundException if no item found
     */
    public ReviewDTO getReview (String id) {
        Review result = reviewDao.getReview(id);

        if (result == null) {
            throw new NotFoundException("Review not found");
        }
        return ReviewConverter.convertToDto(result);
    }

    /**
     * Add new {@linkplain Review} and validate the request body
     * @param dto the {@linkplain Review}
     * @return the persisted ID
     */
    public String addReview (ReviewDTO dto) {
        Review review = ReviewConverter.convertToDoc(dto);
        Validator.validate(review);
        return reviewDao.addReview(review);
    }

    /**
     * Retrieve all {@linkplain Review}s
     * @return the List of all {@linkplain Review}s
     * @throws NotFoundException if no item found
     */
    public List<ReviewDTO> getAllReviews () {
        List<Review> result = reviewDao.getAllReviews();
        if (result == null || result.isEmpty()) {
            throw new NotFoundException("Review not found");
        }

        List<ReviewDTO> reviews = new ArrayList<>(result.size());
        for (Review review : result) {
            reviews.add(ReviewConverter.convertToDto(review));
        }
        return reviews;
    }
}
