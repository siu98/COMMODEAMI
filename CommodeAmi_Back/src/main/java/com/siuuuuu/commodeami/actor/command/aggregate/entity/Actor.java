package com.siuuuuu.commodeami.actor.command.aggregate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="TBL_ACTOR")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="actor_id")
    private Long actorId;

    @Column(name="name")
    private String name;

    @Column(name="gender")
    private String gender;

    @Column(name="profile_image")
    private String profileImage;

    @Column(name="original_name")
    private String originalName;
}
