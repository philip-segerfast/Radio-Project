package com.grupp4.radioproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "episodes")
public class Episode {
    @Id
    @Column(name = "episode_id")
    private long episodeId;
    @Transient
    private String name;
    @Transient
    private String description;
    @Transient
    private String publishdateutc;
    @Transient
    private String url;

    @ManyToOne
    @JoinColumn(name = "program_id")
    @JsonIgnoreProperties({"channel", "description", "programCategory"})
    private Program program;

    public Episode() { }

    public Episode(long id, String name) {
        this.episodeId = id;
        this.name = name;
    }

    public Episode(long episodeId, String name, String description, String publishdateutc, String url, Program program) {
        this.episodeId = episodeId;
        this.name = name;
        this.description = description;
        this.publishdateutc = publishdateutc;
        this.url = url;
        this.program = program;
    }

    @JsonProperty
    public Program getProgram() {
        return program;
    }
    @JsonProperty
    public void setProgram(Program program) {
        this.program = program;
    }

    @JsonProperty
    public long getEpisodeId() {
        return episodeId;
    }
    @JsonProperty
    public void setEpisodeId(long episodeId) {
        this.episodeId = episodeId;
    }

    @JsonProperty
    public String getName() {
        return name;
    }
    @JsonIgnore
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty
    public String getDescription() {
        return description;
    }
    @JsonIgnore
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty
    public String getPublishdateutc() {
        return publishdateutc;
    }
    @JsonIgnore
    public void setPublishdateutc(String publishdateutc) {
        this.publishdateutc = publishdateutc;
    }

    @JsonProperty
    public String getUrl() {
        return url;
    }
    @JsonIgnore
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
                ", program=" + program +
                '}';
    }
}
