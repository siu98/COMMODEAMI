package com.siuuuuu.commodeami.movie.command.aggregate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PopularMovieDTO {

    private Long id;
    private String title;
    private Date releasedDate;
    private String overview;
}
