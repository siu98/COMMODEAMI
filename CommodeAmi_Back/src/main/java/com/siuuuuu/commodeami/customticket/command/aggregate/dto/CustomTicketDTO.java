package com.siuuuuu.commodeami.customticket.command.aggregate.dto;

import com.siuuuuu.commodeami.user.command.aggregate.entity.User;
import lombok.Data;

@Data
public class CustomTicketDTO {

    private Long customTicketId;
    private String ticketImage;
    private String hologramColor1;
    private String hologramColor2;
    private String comment;
    private User user;
}
