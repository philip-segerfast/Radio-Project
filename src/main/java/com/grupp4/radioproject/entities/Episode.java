package com.grupp4.radioproject.entities;

import javax.persistence.*;

@Entity
@Table(name = "episodes")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long episodeId;

    private Episode() {}

}
