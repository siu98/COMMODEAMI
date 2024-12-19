package com.siuuuuu.commodeami.actor.command.aggregate.dto;

import lombok.Data;

@Data
public class ActorDTO {

    private Long actorId;
    private String name;
    private String gender;
    private String profileImage;
    private String originalName;
}
