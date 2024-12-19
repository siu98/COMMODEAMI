package com.siuuuuu.commodeami.actor.command.domain.repository;

import com.siuuuuu_o3o.commodeami.actor.command.aggregate.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    Optional<Actor> findByName(String name);
}
