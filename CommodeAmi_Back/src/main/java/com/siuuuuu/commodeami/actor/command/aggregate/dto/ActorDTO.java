package com.siuuuuu.commodeami.actor.command.aggregate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActorDTO {

    @JsonProperty("id")
    private Long actorId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("profile_-path")
    private String profileImage;

    @JsonProperty("known_for_department")
    private String knownForDepartment;

    @JsonProperty("original_name")
    private String originalName;
}
