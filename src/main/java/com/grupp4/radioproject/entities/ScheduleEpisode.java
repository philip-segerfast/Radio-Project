package com.grupp4.radioproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@JsonIgnoreProperties(value = {"episodeId", "title", "description", "starttimeutc"}, allowGetters = true)
public class ScheduleEpisode {

    @Id
    private long episodeId;
    private String title;
    private String description;
    private String starttimeutc;

    @ManyToOne
    @JsonIgnoreProperties({"programs", "tagline"})
    Channel channel;

    @ManyToOne
    @JsonIgnoreProperties({"channel", "description", "programCategory"})
    Program program;

    public ScheduleEpisode() { }

    public ScheduleEpisode(long episodeId, String title, String description, String starttimeutc, Channel channel, Program program) {
        this.episodeId = episodeId;
        this.title = title;
        this.description = description;
        this.starttimeutc = starttimeutc;
        this.channel = channel;
        this.program = program;
    }

    public long getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(long episodeId) {
        this.episodeId = episodeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStarttimeutc() {
        return starttimeutc;
    }

    public void setStarttimeutc(String starttimeutc) {
        this.starttimeutc = starttimeutc;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "ScheduleEpisode{" +
                "episodeId=" + episodeId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", starttimeutc='" + starttimeutc + '\'' +
                ", channel=" + channel +
                ", program=" + program +
                '}';
    }
}
