package com.siuuuuu.commodeami.customticket.command.aggregate.dto;

import com.siuuuuu.commodeami.user.command.aggregate.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomTicketDTO {

    private Long customTicketId;
    private String ticketImage;
    private String hologramColor1;
    private String hologramColor2;
    private String comment;
    private User user;
    private Long userId;

    public CustomTicketDTO(Long customTicketId, String ticketImage, String hologramColor1, String hologramColor2, String comment, Long userId) {
        this.customTicketId = customTicketId;
        this.ticketImage = ticketImage;
        this.hologramColor1 = hologramColor1;
        this.hologramColor2 = hologramColor2;
        this.comment = comment;
        this.userId = userId;
    }

}
