package com.siuuuuu.commodeami.review.query.repository;

import com.siuuuuu.commodeami.review.query.aggregate.Review;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface ReviewMapper {

    List<Review> selectAllReviews();

    Review selectReviewByUserId(@Param("userId") Long userId);
}
