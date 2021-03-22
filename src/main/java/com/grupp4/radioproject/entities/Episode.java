package com.grupp4.radioproject.entities;

import javax.persistence.*;

@Entity
@Table(name = "episodes")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long episodeId;
    private String name;

    private Episode() {}

    public Episode(long id, String name) {
        this.episodeId = id;
        this.name = name;
    }

    public long getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(long episodeId) {
        this.episodeId = episodeId;
    }

    @Override
    public String toString() {
        return "Episode{" +
                "episodeId=" + episodeId +
                ", name='" + name + '\'' +
                '}';
    }
}
