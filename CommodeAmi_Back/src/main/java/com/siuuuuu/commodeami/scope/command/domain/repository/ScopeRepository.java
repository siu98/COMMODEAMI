package com.siuuuuu.commodeami.scope.command.domain.repository;

import com.siuuuuu.commodeami.scope.command.aggregate.dto.ScopeDTO;
import com.siuuuuu.commodeami.scope.command.aggregate.entity.Scope;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScopeRepository extends JpaRepository<Scope, Long> {
    ScopeDTO findScopeDTOByUserIdAndMovieId(Long userId, Long movieId);
}
