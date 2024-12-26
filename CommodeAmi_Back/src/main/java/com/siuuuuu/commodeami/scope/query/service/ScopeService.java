package com.siuuuuu.commodeami.scope.query.service;

import com.siuuuuu.commodeami.scope.query.aggregate.ScopeDTO;

import java.util.List;

public interface ScopeService {
    List<ScopeDTO> getAllScopes();

    ScopeDTO getScopeByUserId(Long userId);

    ScopeDTO getScopeByMovieId(Long movieId);
}
