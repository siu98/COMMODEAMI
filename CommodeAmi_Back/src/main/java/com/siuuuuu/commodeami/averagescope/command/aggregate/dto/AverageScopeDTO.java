package com.siuuuuu.commodeami.averagescope.command.aggregate.dto;

import com.siuuuuu.commodeami.movie.command.aggregate.entity.Movie;
import lombok.Data;

@Data
public class AverageScopeDTO {

    private Long averageScopeId;
    private String averageScope;
    private Integer numberOfPeople;
    private Movie movie;
}
