package com.grupp4.radioproject.entities;

import javax.persistence.*;

@Entity
@Table(name = "episodes")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long episodeId;
    private String name;
    private String description;
    private String publishdateutc;
    private String url;

    private Episode() {}

    public Episode(long id, String name) {
        this.episodeId = id;
        this.name = name;
    }

    public Episode(long episodeId, String name, String description, String publishdateutc, String url) {
        this.episodeId = episodeId;
        this.name = name;
        this.description = description;
        this.publishdateutc = publishdateutc;
        this.url = url;
    }

    public long getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(long episodeId) {
        this.episodeId = episodeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublishdateutc() {
        return publishdateutc;
    }

    public void setPublishdateutc(String publishdateutc) {
        this.publishdateutc = publishdateutc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Episode{" +
                "episodeId=" + episodeId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", publishdateutc='" + publishdateutc + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
