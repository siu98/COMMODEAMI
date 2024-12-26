package com.siuuuuu.commodeami.scope.command.application.controller;

import com.siuuuuu.commodeami.common.ResponseDTO;
import com.siuuuuu.commodeami.scope.command.aggregate.dto.ScopeDTO;
import com.siuuuuu.commodeami.scope.command.application.service.ScopeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/scope")
public class ScopeController {

    private final ScopeService scopeService;

    @Autowired
    public ScopeController(ScopeService scopeService) {
        this.scopeService = scopeService;
    }

    // 별점 추가 및 수정
    @PostMapping("/{movieId}/{userId}")
    public ResponseDTO<?> createOrUpdateScope(@PathVariable("movieId") Long movieId,
                                              @PathVariable("userId") Long userId,
                                              @RequestBody ScopeDTO scopeDTO) {

        return ResponseDTO.ok(scopeService.createOrUpdateScope(movieId, userId, scopeDTO));
    }

    // 별점 삭제
    @DeleteMapping("/{scopeId}")
    public ResponseDTO<?> deleteScope(@PathVariable("scopeId") Long scopeId) {
        return ResponseDTO.ok(scopeService.deleteScope(scopeId));
    }
}
