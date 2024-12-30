package com.siuuuuu.commodeami.review.command.application.service;

import com.siuuuuu.commodeami.review.command.aggregate.dto.ReviewDTO;

public interface AppReviewService {
    ReviewDTO createReview(Long movieId, Long userId, ReviewDTO reviewDTO);

    ReviewDTO deleteReview(Long reviewId);

}
