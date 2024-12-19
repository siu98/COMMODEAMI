package com.siuuuuu.commodeami.movie.command.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siuuuuu_o3o.commodeami.movie.command.aggregate.dto.MovieDetailDTO;
import com.siuuuuu_o3o.commodeami.movie.command.aggregate.dto.PopularMovieDTO;


import com.siuuuuu_o3o.commodeami.movie.command.aggregate.entity.Movie;
import com.siuuuuu_o3o.commodeami.movie.command.domain.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class APIServiceImpl implements APIService {

    @Value("${tmdb.api.url}")
    private String tmdbApiUrl;

    @Value("${tmdb.api.key}")
    private String tmdbApiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final MovieRepository movieRepository;

    public APIServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Scheduled(cron = "0 0 1 * * ?")
    @Scheduled(fixedRate = 10000000)
    @Override
    public List<PopularMovieDTO> fetchPopularMovies() {
        log.info("Fetching popular movies from TMDB API...");
        String url = tmdbApiUrl + "/popular?api_key=" + tmdbApiKey + "&language=ko-KR";
        log.info("Requesting TMDB API: {}", url);

        try {
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);

            if (response != null && response.containsKey("results")) {
                List<?> results = (List<?>) response.get("results");
                log.info("Fetched {} movies from TMDB", results.size());

                // API에서 받은 데이터를 DTO로 변환
                List<PopularMovieDTO> popularMovies = results.stream()
                        .map(result -> {
                            try {
                                return objectMapper.convertValue(result, PopularMovieDTO.class);
                            } catch (Exception e) {
                                log.error("Failed to map result: {}", result, e);
                                return null;
                            }
                        })
                        .filter(dto -> dto != null)
                        .collect(Collectors.toList());

                // DB에 저장 또는 업데이트 (api_id 기준으로 매칭)
                for (PopularMovieDTO dto : popularMovies) {
                    movieRepository.findByApiId(dto.getId()).ifPresentOrElse(
                            existingMovie -> log.info("Movie already exists: {}", existingMovie.getTitle()),
                            () -> {
                                Movie newMovie = new Movie();
                                newMovie.setApiId(dto.getId()); // API의 id를 별도 필드에 저장
                                newMovie.setTitle(dto.getTitle());
                                newMovie.setReleasedAt(dto.getReleasedDate());
                                newMovie.setPlot(dto.getOverview());
//                                newMovie.setTitle(dto.getTitle());
                                movieRepository.save(newMovie);
                            }
                    );
                }


                return popularMovies;
            }
        } catch (Exception e) {
            log.error("Error fetching popular movies: {}", e.getMessage(), e);
        }

        return List.of();

    }

//    @Scheduled(cron = "0 0 1 * * ?")
//    @Scheduled(fixedRate = 10000000)
@Override
public MovieDetailDTO fetchMovieDetails(Long apiId) {
    log.info("Fetching movie details...");
    log.info("Fetching movie details for apiId: {}", apiId);
    List<Movie> movies = movieRepository.findAll(); // 저장된 영화 가져오기

    for (Movie movie : movies) {
        if (movie.getApiId() == null) {
            log.warn("Skipping movie with null apiId: {}", movie);
            continue;
        }

        String url = String.format("%s/%d?api_key=%s&language=ko-KR", tmdbApiUrl, movie.getApiId(), tmdbApiKey);
        log.info("Requesting details for movie: {}, URL: {}", movie.getTitle(), url);

        try {
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);
            log.info("API Response for {}: {}", movie.getTitle(), response);

            if (response != null) {
                MovieDetailDTO movieDetail = objectMapper.convertValue(response, MovieDetailDTO.class);

                // 상세 정보 업데이트
                movie.setPlot(movieDetail.getOverview());
                movie.setReleasedAt(movieDetail.getReleasedDate());
                movie.setGenre(movieDetail.getGenres().stream()
                        .map(genre -> genre.getName())
                        .collect(Collectors.joining(", ")));
                movieRepository.save(movie);
                log.info("Updated movie: {}", movie);
            }
        } catch (Exception e) {
            log.error("Error fetching details for movie {}: {}", movie.getTitle(), e.getMessage(), e);
        }
    }
    return null;
}
}
