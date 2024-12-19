package com.siuuuuu.commodeami.movieactor.command.domain.repository;


import com.siuuuuu.commodeami.movieactor.command.aggregate.entity.MovieActor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieActorRepository extends JpaRepository<MovieActor, Long> {

    @Query("SELECT ma FROM MovieActor ma WHERE ma.movie.movieId = :movieId")
    List<MovieActor> findByMovieId(@Param("movieId") Long movieId);
}
