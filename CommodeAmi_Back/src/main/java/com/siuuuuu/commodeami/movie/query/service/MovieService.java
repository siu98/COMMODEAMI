package com.siuuuuu.commodeami.movie.query.service;

import com.siuuuuu.commodeami.movie.query.aggregate.MovieDTO;

public interface MovieService {

    MovieDTO findMovieById(Long movieId);


}
