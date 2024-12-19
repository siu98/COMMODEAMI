package com.siuuuuu.commodeami.movie.command.domain.repository;

import com.siuuuuu_o3o.commodeami.movie.command.aggregate.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
//    boolean existsByTitle(String title); // 영화 제목으로 중복 체크
@Query("SELECT CASE WHEN COUNT(m) > 0 THEN TRUE ELSE FALSE END FROM Movie m WHERE m.title = :title")
boolean existsByTitle(@Param("title") String title);
    Optional<Movie> findByMovieId(Long movieId);

    Optional<Movie> findByApiId(Long apiId);
}
