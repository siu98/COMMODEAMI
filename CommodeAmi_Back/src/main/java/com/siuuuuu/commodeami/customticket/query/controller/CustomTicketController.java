package com.siuuuuu.commodeami.customticket.query.controller;

import com.siuuuuu.commodeami.common.ResponseDTO;
import com.siuuuuu.commodeami.customticket.query.service.CustomTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/customticket")
public class CustomTicketController {

    private CustomTicketService customTicketService;

    @Autowired
    public CustomTicketController(CustomTicketService customTicketService) {
        this.customTicketService = customTicketService;
    }

    // 모든 커스텀 티켓 조회
    @GetMapping("")
    public ResponseDTO<?> findAllCustomTickets() {return ResponseDTO.ok(customTicketService.getAllCustomTickets());}

    // 해당 유저의 커스텀 티켓 조회
    @GetMapping("/{userId}")
    public ResponseDTO<?> findCustomTicketsByUserId(@PathVariable("userId") Long userId) {
        return ResponseDTO.ok(customTicketService.getCustomTicketByUserId(userId));
    }
}
