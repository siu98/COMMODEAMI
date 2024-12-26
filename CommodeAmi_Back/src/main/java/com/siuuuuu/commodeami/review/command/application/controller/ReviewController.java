package com.siuuuuu.commodeami.review.command.application.controller;

import com.siuuuuu.commodeami.common.ResponseDTO;
import com.siuuuuu.commodeami.review.command.aggregate.dto.ReviewDTO;
import com.siuuuuu.commodeami.review.command.application.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/review")
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // 리뷰 추가
    @PostMapping("{movieId}/{userId}")
    public ResponseDTO<?> createReview(@PathVariable("movieId") Long movieId,
                                               @PathVariable("userId") Long userId,
                                               @RequestBody ReviewDTO reviewDTO) {
        return ResponseDTO.ok(reviewService.createReview(movieId, userId, reviewDTO));
    }

    // 리뷰 수정

    // 리뷰 삭제
    @DeleteMapping("/{reviewId}")
    public ResponseDTO<?> deleteReview(@PathVariable("reviewId") Long reviewId) {
        return ResponseDTO.ok(reviewService.deleteReview(reviewId));
    }
}
