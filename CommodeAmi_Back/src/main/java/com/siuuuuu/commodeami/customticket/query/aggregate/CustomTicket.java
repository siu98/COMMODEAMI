package com.siuuuuu.commodeami.customticket.query.aggregate;

import com.siuuuuu.commodeami.user.command.aggregate.entity.User;
import lombok.Data;

@Data
public class CustomTicket {

    Long customTicketId;
    String ticketImage;
    String hologramColor1;
    String hologramColor2;
    String comment;
    Long userId;
}
