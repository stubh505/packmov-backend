package com.deloitte.packmov.services;

import com.deloitte.packmov.daos.ReviewDao;
import com.deloitte.packmov.exceptions.InvalidReviewException;
import com.deloitte.packmov.exceptions.NotFoundException;
import com.deloitte.packmov.models.documents.Review;
import com.deloitte.packmov.models.dtos.ReviewDTO;
import com.deloitte.packmov.utilities.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.stream.Stream;

@SpringBootTest
class ReviewServiceTest {

    @Mock
    private ReviewDao reviewDao;

    @InjectMocks
    private ReviewService reviewService = new ReviewService();

    private static final String VALID_NAME = "Sample Name";
    private static final String VALID_CONTACT = "7894561230";
    private static final String VALID_EMAIL = "samp.le@ema.il";
    private static final String VALID_DESC = "SAmple Description";
    private static final int VALID_RATE = 4;

    @Test
    void testGetReviewValid() {
        Mockito.when(reviewDao.getReview(Mockito.anyString())).thenReturn(new Review());
        Assertions.assertNotNull(reviewService.getReview("id"));
    }

    @Test
    void testGetReviewDoesNotExist() {
        Mockito.when(reviewDao.getReview(Mockito.anyString())).thenReturn(null);
        Assertions.assertThrows(NotFoundException.class, () -> reviewService.getReview("id"));
    }

    @Test
    void testGetAllReviewsValid() {
        Mockito.when(reviewDao.getAllReviews()).thenReturn(Collections.singletonList(new Review()));
        Assertions.assertNotNull(reviewService.getAllReviews());
    }

    @Test
    void testGetAllReviewsDoesNotExist() {
        Mockito.when(reviewDao.getAllReviews()).thenReturn(Collections.emptyList());
        Assertions.assertThrows(NotFoundException.class, () -> reviewService.getAllReviews());
    }

    @ParameterizedTest
    @MethodSource("createValidTests")
    void testAddReviewValid(ReviewDTO dto) {
        Mockito.when(reviewDao.addReview(Mockito.any(Review.class))).thenReturn("id");
        Assertions.assertNotNull(reviewService.addReview(dto));
    }

    @ParameterizedTest
    @MethodSource("createInvalidTests")
    void testAddReviewInvalid(ReviewDTO dto, String message) {
        Exception e = Assertions.assertThrows(InvalidReviewException.class, () -> reviewService.addReview(dto));
        Assertions.assertEquals("Invalid Review : " + message, e.getMessage());
    }

    private static Stream<Arguments> createValidTests() {
        return Stream.of(
                Arguments.of(createReview(VALID_NAME, VALID_EMAIL, VALID_DESC, VALID_CONTACT, VALID_RATE)),
                Arguments.of(createReview(VALID_NAME, VALID_EMAIL, VALID_DESC, "", VALID_RATE)),
                Arguments.of(createReview(VALID_NAME, VALID_EMAIL, VALID_DESC, null, VALID_RATE))
        );
    }

    private static Stream<Arguments> createInvalidTests() {
        return Stream.of(
                Arguments.of(createReview("", VALID_EMAIL, VALID_DESC, VALID_CONTACT, VALID_RATE), Validator.INVALID_NAME),
                Arguments.of(createReview(null, VALID_EMAIL, VALID_DESC, VALID_CONTACT, VALID_RATE), Validator.INVALID_NAME),
                Arguments.of(createReview(VALID_NAME, VALID_EMAIL, VALID_DESC, "1230456789", VALID_RATE), Validator.INVALID_CONTACT),
                Arguments.of(createReview(VALID_NAME, VALID_EMAIL, VALID_DESC, "12304567", VALID_RATE), Validator.INVALID_CONTACT),
                Arguments.of(createReview(VALID_NAME, null, VALID_DESC, VALID_CONTACT, VALID_RATE), Validator.INVALID_EMAIL),
                Arguments.of(createReview(VALID_NAME, "", VALID_DESC, VALID_CONTACT, VALID_RATE), Validator.INVALID_EMAIL),
                Arguments.of(createReview(VALID_NAME, VALID_EMAIL, null, VALID_CONTACT, VALID_RATE), Validator.INVALID_DESCRIPTION),
                Arguments.of(createReview(VALID_NAME, VALID_EMAIL, "", VALID_CONTACT, VALID_RATE), Validator.INVALID_DESCRIPTION),
                Arguments.of(createReview(VALID_NAME, VALID_EMAIL, VALID_DESC, VALID_CONTACT, 0), Validator.INVALID_RATING),
                Arguments.of(createReview(VALID_NAME, VALID_EMAIL, VALID_DESC, VALID_CONTACT, -1), Validator.INVALID_RATING),
                Arguments.of(createReview(VALID_NAME, VALID_EMAIL, VALID_DESC, VALID_CONTACT, 10), Validator.INVALID_RATING),
                Arguments.of(createReview(VALID_NAME, VALID_EMAIL, VALID_DESC, VALID_CONTACT, 6), Validator.INVALID_RATING),
                Arguments.of(createReview(VALID_NAME, VALID_EMAIL, VALID_DESC, VALID_CONTACT, null), Validator.INVALID_RATING)
        );
    }

    private static ReviewDTO createReview(String name, String email, String desc, String contact, Integer rate) {
        ReviewDTO dto = new ReviewDTO();
        dto.setName(name);
        dto.setEmail(email);
        dto.setContact(contact);
        dto.setDescription(desc);
        dto.setRating(rate);
        return dto;
    }
}
