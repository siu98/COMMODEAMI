package com.siuuuuu.commodeami.scope.command.application.service;

import com.siuuuuu.commodeami.movie.command.domain.repository.MovieRepository;
import com.siuuuuu.commodeami.review.command.aggregate.entity.Review;
import com.siuuuuu.commodeami.scope.command.aggregate.dto.ScopeDTO;
import com.siuuuuu.commodeami.scope.command.aggregate.entity.Scope;
import com.siuuuuu.commodeami.scope.command.domain.repository.ScopeRepository;
import com.siuuuuu.commodeami.user.command.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScopeServiceImpl implements ScopeService {

    private final ScopeRepository scopeRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public ScopeServiceImpl(ScopeRepository scopeRepository,
                            UserRepository userRepository,
                            MovieRepository movieRepository) {
        this.scopeRepository = scopeRepository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public ScopeDTO createOrUpdateScope(Long userId, Long movieId, ScopeDTO scopeDTO) {
        // 1. 사용자가 있는지 검증
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다: userId=" + userId);
        }

        // 2. 영화가 있는지 검증
        if (!movieRepository.existsById(movieId)) {
            throw new IllegalArgumentException("영화를 찾을 수 없습니다: movieId=" + movieId);
        }

        // 3. 기존 별점 조회
//        Optional<ScopeDTO> existingScopeOpt = scopeRepository.findByUserIdAndMovieId(userId, movieId);
        ScopeDTO existingScopeDTO = scopeRepository.findScopeDTOByUserIdAndMovieId(userId, movieId);

        // 4-1. 기존 별점이 있을 때
        if (existingScopeDTO != null) {
            // 별점 수정
            existingScopeDTO.setScope(scopeDTO.getScope());
            existingScopeDTO.setCreatedAt(scopeDTO.getCreatedAt());

            // DTO -> entity 변환
            Scope updatedScope = new Scope();
            updatedScope.setScopeId(existingScopeDTO.getScopeId());
            updatedScope.setScope(existingScopeDTO.getScope());
            updatedScope.setCreatedAt(existingScopeDTO.getCreatedAt());
            updatedScope.setUser(userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다.")));
            updatedScope.setMovie(movieRepository.findById(movieId)
                    .orElseThrow(() -> new IllegalArgumentException("영화를 찾을 수 없습니다.")));
            updatedScope.setReview(existingScopeDTO.getReviewId() != null
                    ? reviewRepository.findById(existingScopeDTO.getReviewId()).orElse(null)
                    : null);


            // 저장
            scopeRepository.save(updatedScope);
//            return existingScopeDTO;
            return new ScopeDTO(
                    updatedScope.getScopeId(),
                    updatedScope.getScope(),
                    updatedScope.getCreatedAt(),
                    updatedScope.getUser().getUserId(),
                    updatedScope.getMovie().getMovieId(),
                    updatedScope.getReview() != null ? updatedScope.getReview().getReviewId() : null
            );
        } else {
            // 4-2. 기존 별점이 없을 때
            Scope newScope = new Scope();
            newScope.setScope(scopeDTO.getScope());
            newScope.setCreatedAt(scopeDTO.getCreatedAt());
            newScope.setUser(userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다.")));
            newScope.setMovie(movieRepository.findById(movieId)
                    .orElseThrow(() -> new IllegalArgumentException("영화를 찾을 수 없습니다.")));
            newScope.setReview(scopeDTO.getReviewId() != null
                    ? reviewRepository.findById(scopeDTO.getReviewId()).orElse(null)
                    : null);

            // 저장
            Scope savedScope = scopeRepository.save(newScope);

            // entity -> DTO 변환
            return new ScopeDTO(
                    savedScope.getScopeId(),
                    savedScope.getScope(),
                    savedScope.getCreatedAt(),
                    savedScope.getUser().getUserId(),
                    savedScope.getMovie().getMovieId(),
                    savedScope.getReview() != null ? savedScope.getReview().getReviewId() : null
//                    savedScope.getReview() != null ? savedScope.getReview().getReviewId() : null,


            );
        }


    }

    @Override
    public ScopeDTO deleteScope(Long scopeId) {
        // 1. Scope 존재 여부 확인
        Scope scope = scopeRepository.findById(scopeId)
                .orElseThrow(() -> new IllegalArgumentException("별점을 찾을 수 없습니다: scopeId=" + scopeId));

        // 2. Scope 삭제
        scopeRepository.deleteById(scopeId);

        // 3. 삭제된 Scope를 DTO로 반환
        return new ScopeDTO(
                scope.getScopeId(),
                scope.getScope(),
                scope.getCreatedAt(),
                scope.getUser().getUserId(),
                scope.getMovie().getMovieId(),
                scope.getReview() != null ? scope.getReview().getReviewId() : null // 리뷰 ID 포함
        );
    }

}
