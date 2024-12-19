package com.siuuuuu.commodeami.review.command.aggregate.entity;

import com.siuuuuu_o3o.commodeami.movie.command.aggregate.entity.Movie;
import com.siuuuuu_o3o.commodeami.user.command.aggregate.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name="TBL_REVIEW")
public class Review {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="review_id")
    private Long reviewId;

    // 별점
    @Column(name="scope")
    private Long scope;

    // 생성일
    @CreationTimestamp
    @Column(name="created_at")
    private LocalDateTime createdAt;

    // 관람일자
    @Column(name="watched_at")
    private Date watchedAt;

    // 리뷰
    @Column(name="review")
    private String review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="movie_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
}
