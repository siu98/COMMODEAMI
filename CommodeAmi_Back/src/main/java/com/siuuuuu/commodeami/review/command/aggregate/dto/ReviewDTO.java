package com.siuuuuu.commodeami.review.command.aggregate.dto;

import com.siuuuuu.commodeami.movie.command.aggregate.entity.Movie;
import com.siuuuuu.commodeami.user.command.aggregate.entity.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ReviewDTO {

    private Long reviewId;
    private Long scope;
    private LocalDateTime createdAt;
    private Date watchedAt;
    private String review;
    private Movie movie;
    private User user;
}
