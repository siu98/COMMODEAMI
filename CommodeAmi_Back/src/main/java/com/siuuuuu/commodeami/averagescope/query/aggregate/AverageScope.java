package com.siuuuuu.commodeami.averagescope.query.aggregate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AverageScope {

    Long averageScopeId;
    Double averageScope;
    Integer numberOfPeople;
    Long movieId;
}
