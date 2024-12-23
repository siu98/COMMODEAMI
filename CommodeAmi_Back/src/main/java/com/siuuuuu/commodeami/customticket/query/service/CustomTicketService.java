package com.siuuuuu.commodeami.customticket.query.service;

import com.siuuuuu.commodeami.customticket.query.aggregate.CustomTicketDTO;

import java.util.List;

public interface CustomTicketService {
    List<CustomTicketDTO> getAllCustomTickets();

    CustomTicketDTO getCustomTicketByUserId(Long userId);
}
