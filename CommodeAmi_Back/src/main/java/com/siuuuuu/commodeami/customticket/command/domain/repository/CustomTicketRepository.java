package com.siuuuuu.commodeami.customticket.command.domain.repository;

import com.siuuuuu.commodeami.customticket.command.aggregate.entity.CustomTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomTicketRepository extends JpaRepository<CustomTicket, Long> {


}
