package com.siuuuuu.commodeami.customticket.command.aggregate.entity;

import com.siuuuuu.commodeami.user.command.aggregate.entity.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="TBL_CUSTOM_TICKET")
public class CustomTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="custom_ticket_id")
    private Long customTicketId;

    @Column(name="ticket_image")
    private String ticketImage;

    @Column(name="hologram_color1")
    private String hologramColor1;

    @Column(name="hologram_color2")
    private String hologramColor2;

    @Column(name="comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

}
