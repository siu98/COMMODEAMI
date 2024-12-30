package com.siuuuuu.commodeami.movie.query.repository;

import com.siuuuuu.commodeami.movie.query.aggregate.Movie;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MovieMapper {

    Movie selectMovieById(Long movieId);
}
