package com.siuuuuu.commodeami.customticket.command.application.controller;

import com.siuuuuu.commodeami.common.ResponseDTO;
import com.siuuuuu.commodeami.customticket.command.aggregate.dto.CustomTicketDTO;
import com.siuuuuu.commodeami.customticket.command.application.service.AppCustomTicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customticket")
@Transactional
@Slf4j
public class AppCustomTicketController {

    private final AppCustomTicketService appCustomTicketService;

    public AppCustomTicketController(AppCustomTicketService appCustomTicketService) {
        this.appCustomTicketService = appCustomTicketService;
    }

    // 티켓 생성
    @PostMapping("")
    public ResponseDTO<?> createCustomTicket(@RequestBody CustomTicketDTO customTicketDTO) {
        // 로그인 된 사용자 아이디 전달
      log.info("UserId: " + customTicketDTO.getUserId());
      return ResponseDTO.ok(appCustomTicketService.createCustomTicket(customTicketDTO, customTicketDTO.getUserId()));
    }

    // 티켓 수정
    @PutMapping("{customTicketId}")
    public ResponseDTO<?> updateCustomTicket(@PathVariable Long customTicketId, @RequestBody CustomTicketDTO customTicketDTO) {
        log.info("UserId: " + customTicketDTO.getUserId());
        return ResponseDTO.ok(appCustomTicketService.updateCustomTicket(customTicketId, customTicketDTO, customTicketDTO.getUserId()));
    }

    // 티켓 삭제
    @DeleteMapping("{customTicketId}/{userId}")
    public ResponseDTO<?> deleteCustomTicket(@PathVariable Long customTicketId, @PathVariable Long userId) {
        log.info("UserId: " + userId);
        appCustomTicketService.deleteCustomTicket(customTicketId, userId);
        return ResponseDTO.ok(null);
    }

}
