package com.deloitte.packmov.api;

import com.deloitte.packmov.models.dtos.ReviewDTO;
import com.deloitte.packmov.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for {@linkplain ReviewDTO}s
 */
@CrossOrigin
@RestController
@RequestMapping("/review")
public class ReviewApi {
    @Autowired
    private ReviewService reviewService;

    /**
     * Retrieve {@linkplain ReviewDTO} for provided ID
     * @param id the ID
     * @return the {@linkplain ReviewDTO}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getReview(@PathVariable String id) {
        return new ResponseEntity<>(reviewService.getReview(id), HttpStatus.OK);
    }

    /**
     * Add new {@linkplain ReviewDTO}
     * @param review the {@linkplain ReviewDTO}
     * @return the persisted ID
     */
    @PostMapping("/")
    public ResponseEntity<char[]> addReview(@RequestBody ReviewDTO review) {
        return new ResponseEntity<>(reviewService.addReview(review).toCharArray(), HttpStatus.CREATED);
    }

    /**
     * Retrieve all {@linkplain ReviewDTO}s
     * @return the List of all {@linkplain ReviewDTO}s
     */
    @GetMapping("/")
    public ResponseEntity<List<ReviewDTO>> getAllReviews() {
        return new ResponseEntity<>(reviewService.getAllReviews(), HttpStatus.OK);
    }
}