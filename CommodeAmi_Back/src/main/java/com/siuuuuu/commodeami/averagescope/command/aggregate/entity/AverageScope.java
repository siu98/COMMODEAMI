package com.siuuuuu.commodeami.averagescope.command.aggregate.entity;

import com.siuuuuu.commodeami.movie.command.aggregate.entity.Movie;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name="TBL_AVERAGE_SCOPE")
public class AverageScope {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="average_scope_id")
    private Long averageScopeId;

    @Column(name="average_scope")
    private Double averageScope;

    @Column(name="number_of_people")
    private Integer numberOfPeople;

    @ManyToOne
    @JoinColumn(name="movie_id")
    private Movie movie;
}
