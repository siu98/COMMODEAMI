//package com.siuuuuu.commodeami.movie.command.application.controller;//package com.siuuuuu_o3o.commodeami.movie.command.application.controller;
//
//import com.siuuuuu.commodeami.common.ResponseDTO;
//import com.siuuuuu.commodeami.movie.command.application.service.MovieService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/movie")
//@Slf4j
//public class MovieController {
//
//    private final MovieService movieService;
//
//    @Autowired
//    public MovieController(MovieService movieService) {
//        this.movieService = movieService;
//        log.info("의존성 주입 확인");
//    }
//
//    @GetMapping("/save/popular")
//    public ResponseDTO<?> savePopularMovies() {
////        log.info("Controller: savePopularMovies endpoint called");
//        log.info("Controller: savePopularMovies endpoint called");
//        movieService.savePopularMovies();
//        return ResponseDTO.ok("Popular movies saved to database!");
//    }
//
//    @GetMapping("/save/{movieId}")
//    public ResponseDTO<?> saveMovieDetails(@PathVariable Long movieId) {
//        log.info("Controller: saveMovieDetails endpoint called, movieId: {}", movieId); // 여기 로그 추가
//        movieService.saveMovieDetails(movieId);
//        return ResponseDTO.ok("Movie details saved to database!");
//    }
//}
