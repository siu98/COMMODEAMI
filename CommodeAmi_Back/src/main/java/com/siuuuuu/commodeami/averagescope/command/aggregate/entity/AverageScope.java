package com.siuuuuu.commodeami.averagescope.command.aggregate.entity;

import com.siuuuuu_o3o.commodeami.movie.command.aggregate.entity.Movie;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name="TBL_AVERAGE_SCOPE")
public class AverageScope {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="average_scope_id")
    private Long averageScopeId;

    @Column(name="average_scope")
    private String averageScope;

    @Column(name="number_of_people")
    private Integer numberOfPeople;

    @ManyToOne
    @JoinColumn(name="movie_id")
    private Movie movie;
}
