package com.siuuuuu.commodeami.review.command.application.service;

import com.siuuuuu.commodeami.movie.command.domain.repository.MovieRepository;
import com.siuuuuu.commodeami.review.command.aggregate.dto.ReviewDTO;
import com.siuuuuu.commodeami.review.command.aggregate.entity.Review;
import com.siuuuuu.commodeami.review.command.domain.repository.ReviewRepository;
import com.siuuuuu.commodeami.scope.command.aggregate.dto.ScopeDTO;
import com.siuuuuu.commodeami.scope.command.aggregate.entity.Scope;
import com.siuuuuu.commodeami.scope.command.domain.repository.ScopeRepository;
import com.siuuuuu.commodeami.user.command.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AppReviewServiceImpl implements AppReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final ScopeRepository scopeRepository;

    @Autowired
    public AppReviewServiceImpl(ReviewRepository reviewRepository,
                                UserRepository userRepository,
                                MovieRepository movieRepository,
                                ScopeRepository scopeRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.scopeRepository = scopeRepository;
    }

    @Override
    public ReviewDTO createReview(Long userId, Long movieId, ReviewDTO reviewDTO) {

        // 1, 사용자가 있는지 검증
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다: userId=" + userId);
        }
        // 2. 영화가 있는지 검증
        if (!movieRepository.existsById(movieId)) {
            throw new IllegalArgumentException("영화를 찾을 수 없습니다: movieId=" + movieId);
        }
        // 3. 별점이 있는지 검증
        Scope existingScope = scopeRepository.findScopeByUserIdAndMovieId(userId, movieId);
        if (existingScope == null) {
            throw new IllegalArgumentException("별점이 존재하지 않습니다. 리뷰를 추가할 수 없습니다.");
        }

        // 4. 리뷰 확인
        Review existingReview = reviewRepository.findReviewByUserIdAndMovieId(userId, movieId);

        if (existingReview != null) {
            // 기존 리뷰 수정
            existingReview.setReview(reviewDTO.getReview());
            Review updatedReview = reviewRepository.save(existingReview);

            return new ReviewDTO(
                    updatedReview.getReviewId(),
                    updatedReview.getReview(),
                    updatedReview.getCreatedAt(),
                    updatedReview.getUser().getUserId(),
                    updatedReview.getMovie().getMovieId(),
//                    updatedReview.getScope() != null ? updatedReview.getScope().getScopeId() : null
                    updatedReview.getScope().getScopeId()

            );
        } else {
            // 새로운 리뷰 추가
            Review newReview = new Review();
            newReview.setReview(reviewDTO.getReview());
            newReview.setCreatedAt(LocalDateTime.now());
            newReview.setUser(userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: userId=" + userId)));
            newReview.setMovie(movieRepository.findById(movieId)
                    .orElseThrow(() -> new IllegalArgumentException("영화를 찾을 수 없습니다: movieId=" + movieId)));
            newReview.setScope(existingScope);

            Review savedReview = reviewRepository.save(newReview);

            return new ReviewDTO(
                    savedReview.getReviewId(),
                    savedReview.getReview(),
                    savedReview.getCreatedAt(),
                    savedReview.getUser().getUserId(),
                    savedReview.getMovie().getMovieId(),
//                    savedReview.getScope() != null ? savedReview.getScope().getScopeId() : null
                    savedReview.getScope().getScopeId()
            );
        }

        // 4-1. 별점이 있을 때
//        if (existingScope != null) {
//            Review existingReview = reviewRepository.findReviewByUserIdAndMovieId(userId, movieId);
//            if (existingReview != null) {
//                // 4-1-1. 기존 리뷰가 있는 경우 수정
//                existingReview.setReview(reviewDTO.getReview());
//
//
//                Review updatedReview = new Review();
//                updatedReview.setReviewId(existingReview.getReviewId());
//                updatedReview.setReview(existingReview.getReview());
//                updatedReview.setCreatedAt(existingReview.getCreatedAt());
//                updatedReview.setUser(userRepository.findById(userId)
//                        .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: userId=" + userId)));
//                updatedReview.setMovie(movieRepository.findById(movieId)
//                        .orElseThrow(() -> new IllegalArgumentException("영화를 찾을 수 없습니다: movieId=" + movieId)));
//
////                reviewRepository.save(updatedReview);
//                reviewRepository.save(updatedReview);
////                return existingReview;
//                return new ReviewDTO(
//                        updatedReview.getReviewId(),
//                        updatedReview.getReview(),
//                        updatedReview.getCreatedAt(),
//                        updatedReview.getUser().getUserId(),
//                        updatedReview.getMovie().getMovieId(),
//                        updatedReview.getScope().getScopeId()
//                );
//            } else {
//                // 4-1-2. 기존 리뷰가 없는 경우 추가
//                Review newReview = new Review();
//                newReview.setReview(reviewDTO.getReview());
//                newReview.setCreatedAt(LocalDateTime.now());
//                newReview.setUser(userRepository.findById(userId)
//                        .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: userId=" + userId)));
//                newReview.setMovie(movieRepository.findById(movieId)
//                        .orElseThrow(() -> new IllegalArgumentException("영화를 찾을 수 없습니다: movieId=" + movieId)));
//                newReview.setScope(existingScope);
//                Review savedReview = reviewRepository.save(newReview);
//
//                return new ReviewDTO(
//                        savedReview.getReviewId(),
//                        savedReview.getReview(),
//                        savedReview.getCreatedAt(),
//                        savedReview.getUser().getUserId(),
//                        savedReview.getMovie().getMovieId(),
//                        savedReview.getScope().getScopeId()
//                );
//            }
//        }
//        // 4-1-1. 리뷰 있는 지 확인 -> 리뷰 없으면 추가 / 있으면 수정???
//
//        // 4-2. 별점이 없을 때 -> 리뷰 추가 불가
//
//        throw new IllegalArgumentException("별점이 존재하지 않습니다. 리뷰를 추가할 수 없습니다.");
    }

    @Override
    public ReviewDTO deleteReview(Long reviewId) {
        // 1. 리뷰 존재 여부 확인
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("리뷰를 찾을 수 없습니다: reviewId=" + reviewId));

        // 2. 리뷰 삭제
        reviewRepository.deleteById(reviewId);

        // 3. 삭제된 리뷰 정보를 DTO로 반환
        return new ReviewDTO(
                review.getReviewId(),
                review.getReview(),
                review.getCreatedAt(),
                review.getUser().getUserId(),
                review.getMovie().getMovieId(),
                review.getScope().getScopeId()
        );
    }
}
