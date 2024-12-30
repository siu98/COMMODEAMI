package com.siuuuuu.commodeami.scope.command.aggregate.entity;

import com.siuuuuu.commodeami.movie.command.aggregate.entity.Movie;
import com.siuuuuu.commodeami.review.command.aggregate.entity.Review;
import com.siuuuuu.commodeami.user.command.aggregate.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name="TBL_SCOPE")
@NoArgsConstructor
@AllArgsConstructor
public class Scope {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="scope_id")
    private Long scopeId;

    @Column(name="scope")
    private Double scope;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    // 관람일자
    @Column(name="watched_at")
    private Date watchedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="movie_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

//    @OneToOne
//    @JoinColumn(name="review_id")
//    private Review review;
}
