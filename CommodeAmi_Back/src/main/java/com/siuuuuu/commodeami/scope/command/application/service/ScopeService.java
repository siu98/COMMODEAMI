package com.siuuuuu.commodeami.scope.command.application.service;

import com.siuuuuu.commodeami.scope.command.aggregate.dto.ScopeDTO;

public interface ScopeService {
    ScopeDTO createOrUpdateScope(Long movieId, Long userId, ScopeDTO scopeDTO);

    ScopeDTO deleteScope(Long scopeId);

    ScopeDTO createOrUpdateWatchedAt(Long scopeId, ScopeDTO scopeDTO);

    ScopeDTO deleteWatchedAt(Long scopeId);
}
