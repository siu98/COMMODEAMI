package com.siuuuuu.commodeami.movie.command.aggregate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDetailDTO {

    private Long id;
    private String original_title;
    private String overview;
    private String poster_path;
    private Date releasedDate;
    private List<GenreDTO> genres;
    private Integer runtime;

//    private List<String> genres;
}
