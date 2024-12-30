package com.siuuuuu.commodeami.customticket.command.application.service;

import com.siuuuuu.commodeami.customticket.command.aggregate.dto.CustomTicketDTO;

public interface AppCustomTicketService {

    CustomTicketDTO createCustomTicket(CustomTicketDTO customTicketDTO, Long userId);

    CustomTicketDTO updateCustomTicket(Long CustomTicketId, CustomTicketDTO customTicketDTO, Long userId);

    void deleteCustomTicket(Long customTicketId, Long userId);
}
