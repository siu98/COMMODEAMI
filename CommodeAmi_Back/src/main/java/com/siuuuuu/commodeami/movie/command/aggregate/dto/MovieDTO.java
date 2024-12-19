package com.siuuuuu.commodeami.movie.command.aggregate.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MovieDTO {

    private Long id;
    private String title;
    private String overview;
    private Date released_at;
    private String poster_url;
    private String original_title;
    private List<String> genres;
    private Integer runtime;
}
