package com.siuuuuu.commodeami.scope.query.aggregate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScopeDTO {

    @JsonProperty("scope_id")
    private Long scopeId;

    @JsonProperty("scope")
    private Double scope;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("watched_at")
    private Date watchedAt;

    @JsonProperty("movie_id")
    private Long movieId;

    @JsonProperty("user_id")
    private Long userId;
}
