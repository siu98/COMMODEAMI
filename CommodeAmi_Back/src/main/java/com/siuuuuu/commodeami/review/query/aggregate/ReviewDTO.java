package com.siuuuuu.commodeami.review.query.aggregate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

    @JsonProperty("review_id")
    private Long reviewId;

    @JsonProperty("review")
    private String review;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("movie_id")
    private Long movieId;

    @JsonProperty("user_id")
    private Long userId;
}
