package com.siuuuuu.commodeami.movieactor.command.domain.repository;

import com.siuuuuu_o3o.commodeami.movieactor.command.aggregate.entity.MovieActor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MovieActorRepository extends JpaRepository<MovieActor, Long> {
}
