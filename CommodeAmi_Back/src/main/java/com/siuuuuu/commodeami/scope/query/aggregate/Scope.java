package com.siuuuuu.commodeami.scope.query.aggregate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Scope {

    Long scopeId;
    Double scope;
    LocalDateTime createdAt;
    Date watchedAt;
    Long movieId;
    Long userId;
    Long reviewId;
}
