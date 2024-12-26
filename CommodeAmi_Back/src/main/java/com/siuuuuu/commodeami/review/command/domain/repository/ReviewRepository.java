package com.siuuuuu.commodeami.review.command.domain.repository;

import com.siuuuuu.commodeami.review.command.aggregate.dto.ReviewDTO;
import com.siuuuuu.commodeami.review.command.aggregate.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    ReviewDTO findReviewDTOByUserIdAndMovieId(Long userId, Long movieId);
}
