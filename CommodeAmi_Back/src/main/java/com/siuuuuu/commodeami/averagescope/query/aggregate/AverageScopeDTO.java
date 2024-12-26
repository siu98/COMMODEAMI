package com.siuuuuu.commodeami.averagescope.query.aggregate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AverageScopeDTO {

    @JsonProperty("average_scope_id")
    private Long averageScopeId;

    @JsonProperty("average_scope")
    private Double averageScope;

    @JsonProperty("number_of_people")
    private Integer numberOfPeople;

    @JsonProperty("movie_id")
    private Long movieId;
}
