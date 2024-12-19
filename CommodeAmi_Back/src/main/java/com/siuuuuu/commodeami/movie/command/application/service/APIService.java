package com.siuuuuu.commodeami.movie.command.application.service;


import com.siuuuuu_o3o.commodeami.movie.command.aggregate.dto.MovieDetailDTO;
import com.siuuuuu_o3o.commodeami.movie.command.aggregate.dto.PopularMovieDTO;


import java.util.List;

public interface APIService {
    List<PopularMovieDTO> fetchPopularMovies();

    MovieDetailDTO fetchMovieDetails(Long movieId);
}
