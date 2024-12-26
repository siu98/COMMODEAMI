package com.siuuuuu.commodeami.scope.query.service;


import com.siuuuuu.commodeami.scope.query.aggregate.Scope;
import com.siuuuuu.commodeami.scope.query.aggregate.ScopeDTO;
import com.siuuuuu.commodeami.scope.query.repository.ScopeMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScopeServiceImpl implements ScopeService {

    private final ScopeMapper scopeMapper;
    private final ModelMapper modelMapper;

    @Autowired
    public ScopeServiceImpl(ScopeMapper scopeMapper,
                            ModelMapper modelMapper) {
        this.scopeMapper = scopeMapper;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ScopeDTO> getAllScopes() {
        List<Scope> scopes = scopeMapper.selectAllScopes();
        List<ScopeDTO> scopeDTOs =
                scopes.stream().map(scope -> modelMapper.map(scope, ScopeDTO.class)).collect(Collectors.toList());
        return scopeDTOs;
    }

    @Override
    public ScopeDTO getScopeByUserId(Long userId) {
        // Mapper를 사용해 사용자가 매긴 별점 조회
        Scope scope = scopeMapper.selectScopesByUserId(userId);

        // 정보가 없을 시 예외처리
        if (scope == null) {
            throw new IllegalArgumentException("별점을 찾을 수 없습니다.");
        }

        // entity -> DTO 변환
        return modelMapper.map(scope, ScopeDTO.class);
    }

    @Override
    public ScopeDTO getScopeByMovieId(Long movieId) {
        // Mapper를 사용해 영화 별점 조회
        Scope scope = scopeMapper.selectScopesByMovieId(movieId);

        if (scope == null) {
            throw new IllegalArgumentException("별점을 찾을 수 없습니다.");
        }

        return modelMapper.map(scope, ScopeDTO.class);
    }
}
