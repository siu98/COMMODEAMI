package com.siuuuuu.commodeami.movie.command.aggregate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PopularMovieDTO {

    private Long id;
    private String title;

    @JsonProperty("release_date")
    private Date released_at;

    @JsonProperty("overview")
    private String plot;
}
