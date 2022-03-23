package com.deloitte.packmov.daos;

import com.deloitte.packmov.models.documents.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository class for {@linkplain Review}s
 */
@Repository
public class ReviewDao {
    @Autowired
    private MongoOperations ops;

    /**
     * Retrieve {@linkplain Review} for provided ID
     * @param id the ID
     * @return the {@linkplain Review}
     */
    public Review getReview (String id) {
        return ops.findOne(Query.query(Criteria.where("id").is(id)), Review.class);
    }

    /**
     * Add new {@linkplain Review}
     * @param review the {@linkplain Review}
     * @return the persisted ID
     */
    public String addReview (Review review) {
        return ops.insert(review).getId();
    }

    /**
     * Retrieve all {@linkplain Review}s
     * @return the List of all {@linkplain Review}s
     */
    public List<Review> getAllReviews () {
        return ops.findAll(Review.class);
    }
}
