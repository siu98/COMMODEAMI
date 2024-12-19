package com.siuuuuu.commodeami.movieactor.command.aggregate.dto;

import com.siuuuuu_o3o.commodeami.actor.command.aggregate.entity.Actor;
import com.siuuuuu_o3o.commodeami.movie.command.aggregate.entity.Movie;
import lombok.Data;

@Data
public class MovieActorDTO {

    private Long movieActorId;
    private String role;
    private Actor actor;
    private Movie movie;
}
