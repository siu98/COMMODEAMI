package com.siuuuuu.commodeami.movie.command.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siuuuuu.commodeami.actor.command.aggregate.dto.ActorDTO;
import com.siuuuuu.commodeami.actor.command.aggregate.entity.Actor;
import com.siuuuuu.commodeami.actor.command.domain.repository.ActorRepository;
import com.siuuuuu.commodeami.movie.command.aggregate.dto.GenreDTO;
import com.siuuuuu.commodeami.movie.command.aggregate.dto.MovieDetailDTO;
import com.siuuuuu.commodeami.movie.command.aggregate.dto.PopularMovieDTO;
import com.siuuuuu.commodeami.movie.command.aggregate.entity.Movie;
import com.siuuuuu.commodeami.movie.command.domain.repository.MovieRepository;
import com.siuuuuu.commodeami.movieactor.command.aggregate.dto.MovieActorDTO;
import com.siuuuuu.commodeami.movieactor.command.aggregate.entity.MovieActor;
import com.siuuuuu.commodeami.movieactor.command.domain.repository.MovieActorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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
//    @Transactional
    public List<PopularMovieDTO> fetchPopularMovies() {
        log.info("Fetching popular movies from TMDB API...");
        int totalPagesToFetch = 10; // 가져올 페이지 수 설정 (필요에 따라 조정 가능)
        List<PopularMovieDTO> allPopularMovies = new ArrayList<>();

        try {
            for (int page = 1; page <= totalPagesToFetch; page++) {
                String url = String.format("%s/popular?api_key=%s&language=ko-KR&page=%d", tmdbApiUrl, tmdbApiKey, page);
                log.info("Requesting TMDB API (Page {}): {}", page, url);

                Map<String, Object> response = restTemplate.getForObject(url, Map.class);

                if (response != null && response.containsKey("results")) {
                    List<?> results = (List<?>) response.get("results");
                    log.info("Fetched {} movies from TMDB (Page {})", results.size(), page);

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

                    allPopularMovies.addAll(popularMovies);

                    // DB에 저장 또는 업데이트 (api_id 기준으로 매칭)
                    for (PopularMovieDTO dto : popularMovies) {
                        movieRepository.findByApiId(dto.getId()).ifPresentOrElse(
                                existingMovie -> log.info("Movie already exists: {}", existingMovie.getTitle()),
                                () -> {
                                    Movie newMovie = new Movie();
                                    newMovie.setApiId(dto.getId()); // API의 id를 별도 필드에 저장
                                    newMovie.setTitle(dto.getTitle());
                                    newMovie.setReleasedAt(dto.getReleased_at());
                                    newMovie.setPlot(dto.getPlot());
                                    movieRepository.save(newMovie);

                                    // 상세 정보 가져오기
                                    fetchMovieDetails(dto.getId());
                                }
                        );
                    }
                } else {
                    log.warn("No results found for page {}", page);
                    break; // 더 이상 데이터가 없으면 반복 종료
                }
            }
        } catch (Exception e) {
            log.error("Error fetching popular movies: {}", e.getMessage(), e);
        }

        return allPopularMovies;
    }


    @Override
    @Transactional
    public MovieDetailDTO fetchMovieDetails(Long apiId) {
        log.info("Fetching movie details for apiId: {}", apiId);

        // DB에서 특정 영화 조회
        Movie movie = movieRepository.findByApiId(apiId)
            .orElseThrow(() -> new IllegalArgumentException("Movie with apiId " + apiId + " not found"));

        String url = String.format("%s/%d?api_key=%s&language=ko-KR", tmdbApiUrl, apiId, tmdbApiKey);
        log.info("Requesting details for movie: {}, URL: {}", movie.getTitle(), url);

        try {
            // TMDB API 요청
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);
            log.info("API Response for {}: {}", movie.getTitle(), response);

            if (response != null) {
                // API 응답을 DTO로 매핑
                MovieDetailDTO movieDetail = objectMapper.convertValue(response, MovieDetailDTO.class);

                // Poster URL 생성
                String posterPath = (String) response.get("poster_path"); // API 응답에서 poster_path 추출
                String posterUrl = null;
                if (posterPath != null) {
                    posterUrl = "https://image.tmdb.org/t/p/original" + posterPath;
                }

                // 엔티티 업데이트
                movie.setRunningTime(movieDetail.getRuntime());
                movie.setGenre(movieDetail.getGenres().stream()
                    .map(GenreDTO::getName) // Genre 내부 필드에 접근
                    .collect(Collectors.joining(", ")));
                movie.setPosterUrl(posterUrl);
                movie.setOriginalTitle(movieDetail.getOriginal_title());
                movieRepository.save(movie);

                log.info("Updated movie: {}", movie);


                // 요청한 영화의 세부 정보를 반환
                return movieDetail;
            }
        } catch (Exception e) {
            log.error("Error fetching details for movie {}: {}", movie.getTitle(), e.getMessage(), e);
        }

        // API 요청 실패 시 null 반환 (필요에 따라 Optional로 감싸는 것도 고려)
        return null;
        }


//    @Override
//    @Transactional
//    public void updateMovieCast(Long apiId, Movie movie) {
//        String creditsUrl = String.format("%s/%d/credits?api_key=%s&language=ko-KR", tmdbApiUrl, apiId, tmdbApiKey);
//        log.info("Requesting credits for movie: {}, URL: {}", movie.getTitle(), creditsUrl);
//
//        try {
//            Map<String, Object> creditsResponse = restTemplate.getForObject(creditsUrl, Map.class);
//            if (creditsResponse != null && creditsResponse.containsKey("cast")) {
//                List<?> cast = (List<?>) creditsResponse.get("cast");
//                log.info("Fetched {} cast members for movie: {}", cast.size(), movie.getTitle());
//
//                for (Object obj : cast) {
//                    ActorDTO actorDTO = objectMapper.convertValue(obj, ActorDTO.class);
//
//                    // 필요한 배우만 처리
//                    if (!"Acting".equalsIgnoreCase(actorDTO.getKnownForDepartment()) &&
//                            !"Directing".equalsIgnoreCase(actorDTO.getKnownForDepartment())) {
//                        continue;
//                    }
//
//                    // 배우 정보 저장 또는 업데이트
//                    Actor actor = actorRepository.findById(actorDTO.getActorId()).orElseGet(() -> {
//                        Actor newActor = new Actor();
//                        newActor.setActorId(actorDTO.getActorId());
//                        newActor.setName(actorDTO.getName());
//                        newActor.setProfileImage(actorDTO.getProfileImage());
//                        newActor.setKnownForDepartment(actorDTO.getKnownForDepartment());
//                        actorRepository.save(newActor);
//                        return newActor;
//                    });
//
//                    // 영화-배우 관계 저장
//                    MovieActorDTO movieActorDTO = objectMapper.convertValue(obj, MovieActorDTO.class);
//                    MovieActor movieActor = new MovieActor();
//                    movieActor.setMovie(movie);
//                    movieActor.setActor(actor);
//                    movieActor.setRole(movieActorDTO.getRole());
//                    movieActor.setCastingOrder(movieActorDTO.getMovieActorId().intValue());
//                    movieActorRepository.save(movieActor);
//                }
//
//                log.info("Updated cast for movie: {}", movie.getTitle());
//            }
//        } catch (Exception e) {
//            log.error("Error updating cast for movie {}: {}", movie.getTitle(), e.getMessage(), e);
//        }
//    }


}
