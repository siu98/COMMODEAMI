package com.siuuuuu.commodeami.movie.command.application.service;//package com.siuuuuu_o3o.commodeami.movie.command.application.service;
//
//import com.siuuuuu_o3o.commodeami.movie.command.aggregate.dto.MovieDetailDTO;
//import com.siuuuuu_o3o.commodeami.movie.command.aggregate.dto.PopularMovieDTO;
//import com.siuuuuu_o3o.commodeami.movie.command.aggregate.entity.Movie;
//import com.siuuuuu_o3o.commodeami.movie.command.domain.repository.MovieRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@Slf4j
//public class MovieServiceImpl implements MovieService {
//
//    private final APIService apiService; // APIService 의존성 주입
//    private final MovieRepository movieRepository;
//    private final ModelMapper modelMapper;
//
//    public MovieServiceImpl(APIService apiService, MovieRepository movieRepository,
//                            ModelMapper modelMapper) {
//        this.apiService = apiService;
//        this.movieRepository = movieRepository;
//        this.modelMapper = modelMapper;
//        log.info("MovieServiceImpl initialized successfully");
//    }
//
////    @Override
////    public List<PopularMovieDTO> getPopularMovies() {
////        // APIService를 호출해 인기 영화 목록 가져오기
////        log.info("getPopularMovies 확");
////        return apiService.fetchPopularMovies();
////    }
////
////    @Override
////    public MovieDetailDTO getMovieDetails(Long movieId) {
////        // APIService를 호출해 영화 상세 정보 가져오기
////        return apiService.fetchMovieDetails(movieId);
////    }
//
////    @Override
////    @Transactional
////    public void savePopularMovies() {
////        log.info("savePopularMovies() called");
////
////        List<PopularMovieDTO> popularMovies = apiService.fetchPopularMovies();
////        log.info("Fetched {} popular movies", popularMovies.size());
////
////        for (PopularMovieDTO dto : popularMovies) {
////            if (!movieRepository.existsByTitle(dto.getTitle())) {
////                Movie movie = new Movie();
////                movie.setMovieId(dto.getId());
////                movie.setTitle(dto.getTitle());
////                movie.setPlot(dto.getOverview());
////                movieRepository.save(movie);
////                log.info("Saved popular movie: {}", dto.getTitle());
////            }
////        }
////    }
////@Override
////@Transactional
////public void savePopularMovies() {
////    log.info("savePopularMovies() called");
////
////    List<PopularMovieDTO> popularMovies = apiService.fetchPopularMovies();
////    log.info("Fetched {} popular movies", popularMovies.size());
////
////    ModelMapper modelMapper = new ModelMapper();
////
////    for (PopularMovieDTO dto : popularMovies) {
////        if (!movieRepository.existsByTitle(dto.getTitle())) {
////            // DTO → Entity 매핑
////            Movie movie = modelMapper.map(dto, Movie.class);
////            movieRepository.save(movie);
////            log.info("Saved popular movie: {}", dto.getTitle());
////        }
////    }
////
////}
//@Override
//@Transactional
//public void savePopularMovies() {
//    log.info("savePopularMovies() 호출됨");
//
//    // Step 1: Fetch movies from API
//    List<PopularMovieDTO> popularMovies = apiService.fetchPopularMovies(); // API에서 데이터 가져오기
//    if (popularMovies.isEmpty()) {
//        log.warn("No popular movies fetched from API.");
//        return;
//    }
//
//    log.info("Fetched {} popular movies from API", popularMovies.size());
//
//    // Step 2: Process and save movies
//    popularMovies.forEach(dto -> {
//        log.info("Processing movie: {}", dto.getTitle());
//        if (!movieRepository.existsByTitle(dto.getTitle())) {
//            Movie movie = new Movie();
//            movie.setTitle(dto.getTitle());
//            movie.setPlot(dto.getOverview());
//            movieRepository.save(movie);
//            log.info("Saved movie: {}", dto.getTitle());
//        } else {
//            log.info("Movie already exists: {}", dto.getTitle());
//        }
//    });
//}
//
//
////    @Override
////    @Transactional
////    public void saveMovieDetails(Long movieId) {
////        MovieDetailDTO details = apiService.fetchMovieDetails(movieId);
////
////        if (details != null) {
////            Movie movie = movieRepository.findById(details.getId()).orElse(new Movie());
////            movie.setRunningTime(details.getRuntime());
////            movie.setPosterUrl(details.getPoster_path());
////            movie.setOriginalTitle(details.getOriginal_title());
////
////            String genreNames = details.getGenres().stream()
////                    .map(genre -> genre.getName())
////                    .collect(Collectors.joining(", "));
////            movie.setGenre(genreNames);
////
////            movieRepository.save(movie);
////            log.info("Saved movie details for ID: {}", movieId);
////        }
////    }
//
//    @Override
//    @Transactional
//    public void saveMovieDetails(Long movieId) {
//        MovieDetailDTO details = apiService.fetchMovieDetails(movieId);
//
//        if (details != null) {
//            ModelMapper modelMapper = new ModelMapper();
//
//            // 추가 매핑 설정: DTO의 poster_path → Entity의 posterUrl
//            modelMapper.typeMap(MovieDetailDTO.class, Movie.class)
//                    .addMapping(MovieDetailDTO::getPoster_path, Movie::setPosterUrl);
//
//            Movie movie = movieRepository.findById(details.getId())
//                    .orElse(new Movie());
//
//            // DTO → Entity 매핑
//            modelMapper.map(details, movie);
//
//            // 저장
//            movieRepository.save(movie);
//            log.info("Saved movie details for ID: {}", movieId);
//        }
//    }
//
//}
